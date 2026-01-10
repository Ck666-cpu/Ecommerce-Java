package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Find all reviews for a specific product
    List<Review> findByProductId(Long productId);
}