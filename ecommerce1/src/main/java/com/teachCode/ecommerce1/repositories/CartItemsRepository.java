package com.teachCode.ecommerce1.repositories;


import com.teachCode.ecommerce1.entities.Cart;
import com.teachCode.ecommerce1.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    Optional<CartItems> findByCartAndProductId(Cart cart, String productId);

    Optional<List<CartItems>> findByCart(Cart cart);

    void deleteByIdAndProductId(Long id, String productId);

}
