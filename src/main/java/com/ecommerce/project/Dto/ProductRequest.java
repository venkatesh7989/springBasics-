package com.ecommerce.project.Dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRequest {
    private  String name;
    private String description;
    private BigDecimal price;
    private String Category;
    private Integer stockquantity;
    private String imageUrl;

}
