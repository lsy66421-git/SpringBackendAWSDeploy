package com.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "CAFE_PRODUCTS")
public class Product {

    @Id
    @Column(length = 20)
    private Integer code;

    @Column(length = 50)
    private String name;

    private Integer price;

    @Column(length = 50)
    private String picture;

    @Column(length = 200)
    private String description;
}
