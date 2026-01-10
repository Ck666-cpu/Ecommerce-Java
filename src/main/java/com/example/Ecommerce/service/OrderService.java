package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.OrderRequest;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    // CHANGE ARGUMENT: String userEmail instead of relying on request.getUserId()
    public Order placeOrder(String userEmail, OrderRequest request) {

        // 1. Find User by EMAIL (Secure!)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Create Order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3. Process Items (Same as before)
        for (OrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for: " + product.getName());
            }

            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .price(product.getPrice())
                    .build();

            order.getItems().add(orderItem);
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }

        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // NEW METHOD: Fetch orders using the email from the JWT
    public List<Order> getOrdersByUserEmail(String email) {
        // 1. Find the User ID from the email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Use the existing repository method to find orders by ID
        return orderRepository.findByUserId(user.getId());
    }

    @Autowired
    private CartService cartService; // Inject the cart service

    @Transactional
    public Order checkoutCart(String email) {
        // 1. Get the Cart
        Cart cart = cartService.getCart(email);
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 2. Convert Cart to OrderRequest DTO (reusing your existing logic!)
        OrderRequest request = new OrderRequest();
        request.setItems(new java.util.ArrayList<>());

        for (com.example.Ecommerce.model.CartItem cartItem : cart.getItems()) {
            OrderRequest.OrderItemRequest itemRequest = new OrderRequest.OrderItemRequest();
            itemRequest.setProductId(cartItem.getProduct().getId());
            itemRequest.setQuantity(cartItem.getQuantity());
            request.getItems().add(itemRequest);
        }

        // 3. Place Order (This handles stock deduction and saving)
        Order order = placeOrder(email, request);

        // 4. Clear the cart after successful order
        cartService.clearCart(email);

        return order;
    }
}