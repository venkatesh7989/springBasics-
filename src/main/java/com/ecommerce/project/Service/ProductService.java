package com.ecommerce.project.Service;

import com.ecommerce.project.Dto.ProductRequest;
import com.ecommerce.project.Dto.ProductResponse;
import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class ProductService {

    private  final ProductRepository productRepositoty;




    public ProductResponse CreateProduct(ProductRequest productRequest) {
        Product product= new Product();
        updateProductfromRequest(product,productRequest);
        Product savedProduct = productRepositoty.save(product);
        return mapToProductResponse(savedProduct);

    }

    private ProductResponse mapToProductResponse(Product saveProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(saveProduct.getId());
        response.setName(saveProduct.getName());
        response.setCategory(saveProduct.getCategory());
        response.setDescription(saveProduct.getDescription());
        response.setActive(saveProduct.isActive());
        response.setImageUrl(saveProduct.getImageUrl());
        response.setStockquantity(saveProduct.getStockQuantity());
        response.setPrice(saveProduct.getPrice());
        return response;
    }

    private void updateProductfromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockquantity());
        product.setPrice(productRequest.getPrice());
    }

    public Optional<ProductResponse> updateProduct(long id, ProductRequest productRequest) {
       return productRepositoty.findById(id).map(existingproduct->{
            updateProductfromRequest(existingproduct,productRequest);
         Product savedproduct=   productRepositoty.save(existingproduct);
            return mapToProductResponse(savedproduct);
        });
}

    public List<ProductResponse> getAllProducts() {
        return productRepositoty.findByActiveTrue().stream().map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        return productRepositoty.findById(id).map(product -> {
            product.setActive(false);
            productRepositoty.save(product);
            return true;
        }).orElse(false);
    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepositoty.searchProduct(keyword).stream().map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

}
