package com.request.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    private String title;
    private double price;
    private String description;
    private String imgUrl;
}
