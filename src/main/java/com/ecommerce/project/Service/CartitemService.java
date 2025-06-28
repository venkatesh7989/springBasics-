package com.ecommerce.project.Service;

import com.ecommerce.project.Dto.CartiteamRequest;
import com.ecommerce.project.Model.Cartitem;
import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Model.User;
import com.ecommerce.project.Repository.CartitemRepository;
import com.ecommerce.project.Repository.ProductRepository;
import com.ecommerce.project.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartitemService {
    private final ProductRepository productRepository;
    private  final UserRepository userRepository;
    private  final CartitemRepository cartitemRepository;
    public boolean addToCart(String userId, CartiteamRequest request) {
        Optional<Product> Productopt = productRepository.findById(request.getProductid());
        if (Productopt.isEmpty())
            return false;
        Product product = Productopt.get();
        if (product.getStockQuantity() < request.getQuantity()) {
            return false;
        }
        Optional<User> useropt = userRepository.findById(Long.valueOf(userId));
        if (useropt.isEmpty())
        return false;
        User user = useropt.get();
        Cartitem exstingCartitem = cartitemRepository.findByUserAndProduct(user, product);
        if (exstingCartitem != null) {
            exstingCartitem.setQuantity(exstingCartitem.getQuantity() + request.getQuantity());
            exstingCartitem.setPrice(exstingCartitem.getPrice().multiply(BigDecimal.valueOf(exstingCartitem.getQuantity())));
            cartitemRepository.save(exstingCartitem);
        } else {
            Cartitem cartitem = new Cartitem();
            cartitem.setUser(user);
            cartitem.setProduct(product);
            cartitem.setQuantity(request.getQuantity());
            cartitem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));

            cartitemRepository.save(cartitem);
        }

                return  true;
    }

    public boolean deleteByUserAndProduct(String userId, Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if (productOpt.isPresent() && userOpt.isPresent()) {
            cartitemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
            return true;
        }

        // If either product or user is not found
        return false;
    }

    public List<Cartitem> getCart(String userId) {
        return userRepository.findById(Long.valueOf(userId)).map(cartitemRepository::findByUser)
                .orElseGet(List::of);
    }
    public  void clearCart(String userid){
        userRepository.findById(Long.valueOf(userid)).
                ifPresent(cartitemRepository::deleteByuser);
    }
}

