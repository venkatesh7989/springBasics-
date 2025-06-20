package com.ecommerce.project.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private  long id;
    private  String name;
    private String description;
    private BigDecimal price;
    private String Category;
    private Integer stockquantity;
    private String imageUrl;
    private  boolean Active;

}
