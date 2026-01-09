package com.example.kiosk.dto;

import com.example.kiosk.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponse {
    private Long id;
    private String menuName;
    private int quantity;
    private int price;

    public OrderItemResponse(OrderItem item) {
        this.id = item.getId();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        if (item.getMenu() != null) {
            this.menuName = item.getMenu().getName();
        }
    }
}
