package com.example.kiosk.dto;

import com.example.kiosk.entity.Orders;
import com.example.kiosk.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Integer totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItemResponse> items;

    public OrderResponse(Orders order) {
        this.id = order.getId();
        this.totalAmount = order.getTotalAmount();
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
        this.items = order.getOrderItems().stream()
                .map(OrderItemResponse::new)
                .collect(Collectors.toList());
    }
}
