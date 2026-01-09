package com.example.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private Long menuId;
    private Integer quantity;
    private Integer price;
}
