package com.ecommerce.project.controller;

import com.ecommerce.project.Dto.ProductRequest;
import com.ecommerce.project.Dto.ProductResponse;
import com.ecommerce.project.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.CreateProduct(productRequest);  // ✅ use injected instance
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updatedProduct(@PathVariable long id ,
            @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id ,productRequest).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String Keyword){
        return ResponseEntity.ok(productService.searchProduct(Keyword));
    }
}
