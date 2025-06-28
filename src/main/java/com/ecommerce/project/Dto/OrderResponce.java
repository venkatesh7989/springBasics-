package com.ecommerce.project.Dto;

import com.ecommerce.project.Model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponce {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderitemDTO> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
