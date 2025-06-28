package com.ecommerce.project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderitemDTO {
    private Long id;
    private Long product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

    public OrderitemDTO(Long id, Long productId, Integer quantity, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = price.multiply(BigDecimal.valueOf(quantity));
    }
}
