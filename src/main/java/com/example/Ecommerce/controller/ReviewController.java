package com.example.Ecommerce.controller;

import com.example.Ecommerce.model.Review;
import com.example.Ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // ADD Review (Authenticated Users)
    @PostMapping("/add")
    public ResponseEntity<Review> addReview(
            @RequestParam Long productId,
            @RequestBody Map<String, Object> payload,
            Principal principal) {

        Integer rating = (Integer) payload.get("rating");
        String comment = (String) payload.get("comment");

        return ResponseEntity.ok(
                reviewService.addReview(principal.getName(), productId, rating, comment)
        );
    }

    // VIEW Reviews (Public)
    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsForProduct(productId));
    }
}