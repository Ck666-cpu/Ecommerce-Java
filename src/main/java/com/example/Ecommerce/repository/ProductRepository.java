package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find products by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Find products by price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Find products with stock available
    List<Product> findByStockQuantityGreaterThan(Integer quantity);
    
    // Find products by exact name
    Optional<Product> findByNameIgnoreCase(String name);
    
    // Custom query example
    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice AND p.stockQuantity > 0")
    List<Product> findAvailableProductsUnderPrice(@Param("maxPrice") BigDecimal maxPrice);
    
    // Count products in stock
    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity > 0")
    long countProductsInStock();
}