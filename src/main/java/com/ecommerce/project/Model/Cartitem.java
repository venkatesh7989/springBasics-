package com.ecommerce.project.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data

public class Cartitem {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@ManyToOne
@JoinColumn(name= "user_id", nullable = false )
    private  User user;
@ManyToOne
@JoinColumn(name= "product_id", nullable = false)
    private  Product product;
    private BigDecimal price;
    private Integer quantity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

}
