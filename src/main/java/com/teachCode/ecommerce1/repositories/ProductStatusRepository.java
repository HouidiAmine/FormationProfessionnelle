package com.teachCode.ecommerce1.repositories;

import com.teachCode.ecommerce1.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
}
