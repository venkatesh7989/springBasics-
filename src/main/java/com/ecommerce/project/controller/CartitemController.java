package com.ecommerce.project.controller;

import com.ecommerce.project.Dto.CartiteamRequest;
import com.ecommerce.project.Model.Cartitem;
import com.ecommerce.project.Service.CartitemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartitemController {
    private  final CartitemService cartitemService;
    @PostMapping
    public ResponseEntity<String> addTocart(@RequestHeader(value = "X-user-id", required = false) String userId,

                                            @RequestBody CartiteamRequest request) {

        if (userId == null || userId.isBlank()) {
            return ResponseEntity.badRequest().body("Missing or invalid user ID in header");
        }

        try {
            if (!cartitemService.addToCart(userId, request)) {
                return ResponseEntity.badRequest().body("Product out of stock or user not found");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid user ID format");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
@DeleteMapping("/item/{productId}")
    public ResponseEntity<Void>removefromcart(@RequestHeader(value = "X-user-id", required = false) String userId,
                                                  @PathVariable Long productId){
   boolean delete= cartitemService.deleteByUserAndProduct(userId ,productId);
    return delete?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
}
@GetMapping
    public ResponseEntity<List<Cartitem>>getcart(@RequestHeader("X-user-id") String userId){
        return ResponseEntity.ok(cartitemService.getCart(userId));

    }

}



