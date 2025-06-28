package com.ecommerce.project.Service;

import com.ecommerce.project.Dto.OrderResponce;
import com.ecommerce.project.Dto.OrderitemDTO;
import com.ecommerce.project.Model.*;
import com.ecommerce.project.Repository.OrderRepository;
import com.ecommerce.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartitemService cartitemService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Optional<OrderResponce> createOrder(String userId) {
        List<Cartitem> cartitems = cartitemService.getCart(userId);

        if (cartitems.isEmpty()) {
            return Optional.empty();
        }

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();

        BigDecimal totalPrice = cartitems.stream()
                .map(item -> item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);

        List<Orderitem> orderitems = cartitems.stream()
                .map(item -> {
                    Orderitem orderitem = new Orderitem();
                    orderitem.setProduct(item.getProduct());
                    orderitem.setQuantity(item.getQuantity());
                    orderitem.setPrice(item.getPrice());
                    orderitem.setOrder(order); // Required to avoid null error
                    return orderitem;
                })
                .toList();

        order.setItems(orderitems);

        Order savedOrder = orderRepository.save(order);

        cartitemService.clearCart(userId);

        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponce mapToOrderResponse(Order order) {
        return new OrderResponce(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream().map(orderItem -> new OrderitemDTO(
                        orderItem.getId(),
                        orderItem.getProduct().getId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                )).toList(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
