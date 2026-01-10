package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.OrderRequest;
import com.example.Ecommerce.model.Order;
import com.example.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    // Add 'Principal principal' to the arguments
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest, Principal principal) {

        // principal.getName() returns the email from the Token
        String email = principal.getName();

        Order newOrder = orderService.placeOrder(email, orderRequest);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/myorders")
    public ResponseEntity<List<Order>> getMyOrders(Principal principal) {
        // 1. Get the email from the security context (JWT)
        String email = principal.getName();

        // 2. Fetch orders for this specific user
        List<Order> orders = orderService.getOrdersByUserEmail(email);

        return ResponseEntity.ok(orders);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(Principal principal) {
        return ResponseEntity.ok(orderService.checkoutCart(principal.getName()));
    }
}