package com.teachCode.ecommerce1.repositories;

import com.teachCode.ecommerce1.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
