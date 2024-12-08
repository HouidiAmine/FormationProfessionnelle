package com.teachCode.ecommerce1.repositories;

import com.teachCode.ecommerce1.entities.ProductStatus;
import com.teachCode.ecommerce1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional <User> findByEmail(String email);
}
