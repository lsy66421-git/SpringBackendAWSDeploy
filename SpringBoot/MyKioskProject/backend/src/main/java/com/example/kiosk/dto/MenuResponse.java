package com.example.kiosk.dto;

import com.example.kiosk.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuResponse {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
        this.imageUrl = menu.getImageUrl();
        if (menu.getCategory() != null) {
            this.categoryId = menu.getCategory().getId();
            this.categoryName = menu.getCategory().getName();
        }
    }
}
