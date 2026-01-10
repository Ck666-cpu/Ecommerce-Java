// File: src/main/java/com/example/Ecommerce/repository/CategoryRepository.java
package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}