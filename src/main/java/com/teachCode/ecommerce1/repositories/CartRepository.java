package com.teachCode.ecommerce1.repositories;


import com.teachCode.ecommerce1.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(String userId);
    /*findByNameAndAgeGreaterThanAndCreatedDateAfterOrderByAgeDesc*/
}