package com.teachCode.ecommerce1.services;

import com.teachCode.ecommerce1.dto.request.CartDto;
import com.teachCode.ecommerce1.dto.response.DtoCart;

import java.util.List;

public interface CartService {

    String addItemsToCart(CartDto cartDto);

    List<DtoCart> getCartItems(String userId);

    void deleteProduct(String productId, Long userId);

}
