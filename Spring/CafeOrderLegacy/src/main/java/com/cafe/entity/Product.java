package com.cafe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    private Integer code;
    private String name;
    private Integer price;
    private String picture;
    private String description;
}
