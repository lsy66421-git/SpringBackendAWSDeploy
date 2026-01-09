package com.example.kiosk.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private List<OrderItemRequest> items;
    private Integer totalAmount;
}
