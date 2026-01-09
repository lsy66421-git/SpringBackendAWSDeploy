package com.example.kiosk.controller;

import com.example.kiosk.dto.OrderItemRequest;
import com.example.kiosk.dto.OrderRequest;
import com.example.kiosk.entity.*;
import com.example.kiosk.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    // Fixed duplicated injection in constructor by Lombok, but I need to be careful
    // with names.
    // I already have menuRepository.

    @GetMapping("/categories")
    public List<Category> getCategories(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null)
            return new ArrayList<>();
        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        // CSV Filtering
        List<Category> allCategories = categoryRepository.findAllByOrderByDisplayOrderAsc();
        String userIdStr = String.valueOf(member.getId());

        return allCategories.stream()
                .filter(cat -> {
                    if (cat.getMemberIds() == null)
                        return false;
                    // Check if userIdStr exists in comma separated list "1,2,3"
                    // Simple contains check is risky (e.g. 1 in 12), so split
                    String[] ids = cat.getMemberIds().split(",");
                    for (String id : ids) {
                        if (id.trim().equals(userIdStr))
                            return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/menus")
    public List<com.example.kiosk.dto.MenuResponse> getMenus(@RequestParam(required = false) Long categoryId,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null)
            return new ArrayList<>();
        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        String userIdStr = String.valueOf(member.getId());

        List<Menu> menus;
        if (categoryId == null || categoryId == 0) {
            // Fetch ALL menus and filter
            menus = menuRepository.findAll();
        } else {
            menus = menuRepository.findByCategoryId(categoryId);
        }

        // Filter by memberIds
        return menus.stream()
                .filter(menu -> {
                    if (menu.getMemberIds() == null)
                        return false;
                    String[] ids = menu.getMemberIds().split(",");
                    for (String id : ids) {
                        if (id.trim().equals(userIdStr))
                            return true;
                    }
                    return false;
                })
                .map(com.example.kiosk.dto.MenuResponse::new)
                .collect(java.util.stream.Collectors.toList());
    }

    @PostMapping("/orders") // User places order
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        Member member = null;
        if (userDetails != null) {
            member = memberRepository.findByUsername(userDetails.getUsername()).orElse(null);
        }

        Orders order = new Orders();
        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.COMPLETED); // Virtual payment always success
        order.setTotalAmount(request.getTotalAmount());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemReq : request.getItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId()).orElseThrow();
            OrderItem item = OrderItem.builder()
                    .order(order)
                    .menu(menu)
                    .quantity(itemReq.getQuantity())
                    .price(itemReq.getPrice())
                    .quantity(itemReq.getQuantity())
                    .price(itemReq.getPrice())
                    .menuName(menu.getName())
                    .build();
            orderItems.add(item);
        }
        order.setOrderItems(orderItems);

        orderRepository.save(order);

        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/orders") // User views history
    public List<com.example.kiosk.dto.OrderResponse> getMyOrders(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null)
            return List.of();
        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Orders> orders = orderRepository.findByMemberId(member.getId());
        return orders.stream().map(com.example.kiosk.dto.OrderResponse::new)
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/sales/monthly")
    public ResponseEntity<?> getMonthlySales(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }

        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (member == null)
            return ResponseEntity.status(401).build();

        List<Orders> orders;
        boolean isAdmin = member.getRole() == Role.ADMIN;

        if (isAdmin) {
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findByMemberId(member.getId());
        }

        // 3. Aggregate Data
        // If Admin: <MemberName, <YYYY-MM, TotalPrice>>
        // If User: <MenuName, <YYYY-MM, TotalPrice>>
        Map<String, Map<String, Integer>> salesMap = new HashMap<>();
        Set<String> allMonths = new TreeSet<>(Comparator.reverseOrder()); // YYYY-MM desc

        for (Orders order : orders) {
            String yearMonth;
            if (order.getOrderDate() != null) {
                yearMonth = order.getOrderDate().toString().substring(0, 7);
            } else if (order.getCreatedAt() != null) {
                yearMonth = order.getCreatedAt().toString().substring(0, 7);
            } else {
                continue; // Skip if date is unknown
            }

            allMonths.add(yearMonth);

            if (isAdmin) {
                // Aggregate by Member Name
                String memberName = (order.getMember() != null) ? order.getMember().getName() : "Unknown";
                salesMap.putIfAbsent(memberName, new HashMap<>());
                Map<String, Integer> monthlyMap = salesMap.get(memberName);
                monthlyMap.put(yearMonth, monthlyMap.getOrDefault(yearMonth, 0) + order.getTotalAmount());
            } else {
                // Aggregate by Menu Name (Existing Logic)
                for (OrderItem item : order.getOrderItems()) {
                    String menuName = item.getMenuName();
                    if (menuName == null && item.getMenu() != null) {
                        menuName = item.getMenu().getName();
                    }
                    if (menuName == null) {
                        continue;
                    }
                    if (menuName == null)
                        menuName = "Unknown";

                    int amount = (item.getPrice() != null ? item.getPrice() : 0)
                            * (item.getQuantity() != null ? item.getQuantity() : 0);

                    salesMap.putIfAbsent(menuName, new HashMap<>());
                    Map<String, Integer> monthlyMap = salesMap.get(menuName);
                    monthlyMap.put(yearMonth, monthlyMap.getOrDefault(yearMonth, 0) + amount);
                }
            }
        }

        // 4. Transform to Table Data
        Map<String, Integer> monthTotals = new HashMap<>();
        int grandTotal = 0;

        // Initialize month totals
        for (String month : allMonths) {
            monthTotals.put(month, 0);
        }

        List<Map<String, Object>> rows = new ArrayList<>();
        // key is MemberName or MenuName
        for (String rowKey : salesMap.keySet()) {
            Map<String, Integer> monthlySales = salesMap.get(rowKey);
            int rowTotal = 0;
            Map<String, Object> row = new HashMap<>();
            // Use "menuName" as the key for frontend compatibility, even if it's member
            // name
            row.put("menuName", rowKey);

            for (String month : allMonths) {
                int sales = monthlySales.getOrDefault(month, 0);
                row.put(month, sales);

                // Update totals
                rowTotal += sales;
                monthTotals.put(month, monthTotals.get(month) + sales);
                grandTotal += sales;
            }
            row.put("total", rowTotal);
            rows.add(row);
        }

        // Sort rows by name
        rows.sort((r1, r2) -> ((String) r1.get("menuName")).compareTo((String) r2.get("menuName")));

        Map<String, Object> response = new HashMap<>();
        response.put("months", new ArrayList<>(allMonths));
        response.put("rows", rows);
        response.put("monthTotals", monthTotals);
        response.put("grandTotal", grandTotal);
        response.put("type", isAdmin ? "MEMBER" : "MENU"); // Info flag

        return ResponseEntity.ok(response);
    }

    @GetMapping("/menus/all")
    public List<com.example.kiosk.dto.MenuResponse> getAllMenus() {
        // Return ALL menus so users can select from them
        return menuRepository.findAll().stream()
                .map(com.example.kiosk.dto.MenuResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/menus/subscribe")
    public ResponseEntity<String> subscribeMenus(@RequestBody List<Long> menuIds,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        Member member = memberRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (member == null)
            return ResponseEntity.status(401).build();

        String userIdStr = String.valueOf(member.getId());

        // 1. Update Menus
        List<Menu> allMenus = menuRepository.findAll();
        Set<Long> requiredCategoryIds = new java.util.HashSet<>();

        for (Menu menu : allMenus) {
            boolean isSelected = menuIds.contains(menu.getId());
            Set<String> ids = new java.util.HashSet<>();
            if (menu.getMemberIds() != null && !menu.getMemberIds().trim().isEmpty()) {
                java.util.Collections.addAll(ids, menu.getMemberIds().split(","));
            }

            if (isSelected) {
                ids.add(userIdStr);
                // Track category
                if (menu.getCategory() != null) {
                    requiredCategoryIds.add(menu.getCategory().getId());
                }
            } else {
                ids.remove(userIdStr);
            }

            menu.setMemberIds(String.join(",", ids));
        }
        menuRepository.saveAll(allMenus);

        // 2. Update Categories
        List<Category> allCategories = categoryRepository.findAll();
        for (Category cat : allCategories) {
            boolean isRequired = requiredCategoryIds.contains(cat.getId());
            Set<String> ids = new java.util.HashSet<>();
            if (cat.getMemberIds() != null && !cat.getMemberIds().trim().isEmpty()) {
                java.util.Collections.addAll(ids, cat.getMemberIds().split(","));
            }

            if (isRequired) {
                ids.add(userIdStr);
            } else {
                ids.remove(userIdStr);
            }

            cat.setMemberIds(String.join(",", ids));
        }
        categoryRepository.saveAll(allCategories);

        return ResponseEntity.ok("Menu subscription updated");
    }
}
