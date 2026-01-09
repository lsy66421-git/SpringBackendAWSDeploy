package com.example.kiosk.controller;

import com.example.kiosk.dto.MenuRequest;
import com.example.kiosk.entity.Category;
import com.example.kiosk.entity.Menu;
import com.example.kiosk.repository.CategoryRepository;
import com.example.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final com.example.kiosk.repository.MemberRepository memberRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
    private final com.example.kiosk.repository.OrderItemRepository orderItemRepository;

    @GetMapping("/members/pending")
    public List<com.example.kiosk.entity.Member> getAllMembers() {
        // Return all members sorted by createdAt descending
        return memberRepository.findAll(org.springframework.data.domain.Sort
                .by(org.springframework.data.domain.Sort.Direction.DESC, "createdAt"));
    }

    @PostMapping("/members/{id}/approve")
    public ResponseEntity<?> approveMember(@PathVariable Long id) {
        com.example.kiosk.entity.Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setIsApproved(1);
        member.setApprovedAt(java.time.LocalDateTime.now());
        memberRepository.save(member);
        return ResponseEntity.ok("Approved");
    }

    @PostMapping("/members/{id}/hold")
    public ResponseEntity<?> holdMember(@PathVariable Long id) {
        // Just explicit check or logic if needed, currently just keeping as 0
        com.example.kiosk.entity.Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setIsApproved(0); // Ensure it's 0
        memberRepository.save(member);
        return ResponseEntity.ok("Held");
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return ResponseEntity.ok("Deleted (Cancelled)");
    }

    @PostMapping("/members/{id}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable Long id) {
        com.example.kiosk.entity.Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setPassword(passwordEncoder.encode("1234"));
        memberRepository.save(member);
        return ResponseEntity.ok("Password reset to 1234");
    }

    @GetMapping("/menu")
    public List<com.example.kiosk.dto.MenuResponse> getMenus(java.security.Principal principal) {
        com.example.kiosk.entity.Member member = memberRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        String userIdStr = String.valueOf(member.getId());

        List<Menu> menus;
        if (member.getRole() == com.example.kiosk.entity.Role.ADMIN) {
            menus = menuRepository.findAll();
        } else {
            // CSV Logic: Filter by memberIds
            List<Menu> allMenus = menuRepository.findAll();
            menus = allMenus.stream()
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
                    .collect(java.util.stream.Collectors.toList());
        }
        return menus.stream().map(com.example.kiosk.dto.MenuResponse::new)
                .collect(java.util.stream.Collectors.toList());
    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> addMenu(@RequestBody MenuRequest request, java.security.Principal principal) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        com.example.kiosk.entity.Member member = memberRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Menu menu = Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .category(category)
                .memberIds(String.valueOf(member.getId())) // Default to current user ID
                .build();
        return ResponseEntity.ok(menuRepository.save(menu));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));

        List<com.example.kiosk.entity.OrderItem> items = orderItemRepository.findByMenu(menu);
        for (com.example.kiosk.entity.OrderItem item : items) {
            if (item.getMenuName() == null) {
                item.setMenuName(menu.getName());
            }
            item.setMenu(null);
        }
        orderItemRepository.saveAll(items);

        menuRepository.delete(menu);
        return ResponseEntity.ok("Menu deleted");
    }
}
