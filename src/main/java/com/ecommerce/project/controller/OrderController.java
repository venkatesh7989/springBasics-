package com.ecommerce.project.controller;

import com.ecommerce.project.Dto.OrderResponce;
import com.ecommerce.project.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponce> createOrder(@RequestHeader("X-user-id") String userId) {
        return orderService.createOrder(userId)  // ✅ fixed method name
                .map(orderResponce -> new ResponseEntity<>(orderResponce, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
