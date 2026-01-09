package com.example.kiosk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Long categoryId;
}
