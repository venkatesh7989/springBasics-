package com.ecommerce.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="products")

@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  long id;
   private  String name;
   private String description;
   private BigDecimal price;
   private String category;
   private Integer stockQuantity;
   private String imageUrl;
   private  boolean active = true;
   @CreationTimestamp
   private LocalDateTime createdAt;
   @UpdateTimestamp
   private  LocalDateTime updatedAt;
}
