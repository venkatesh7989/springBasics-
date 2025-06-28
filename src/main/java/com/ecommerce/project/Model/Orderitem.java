package com.ecommerce.project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private  Integer quantity;
    private BigDecimal totalamount;
    private  BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_ id",nullable = false)
    private Order order;


    public Orderitem(Object o, Product product, Integer quantity, BigDecimal price, Order order) {
    }
}
