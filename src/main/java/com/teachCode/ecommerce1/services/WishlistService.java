package com.teachCode.ecommerce1.services;


import com.teachCode.ecommerce1.dto.request.WishListDto;
import com.teachCode.ecommerce1.entities.Product;
import com.teachCode.ecommerce1.entities.WishList;
import com.teachCode.ecommerce1.repositories.WishListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class WishlistService {
	@Autowired
    WishListRepository wishListRepository;
	@Autowired
	ProductsService productsService;

    public List<WishList> getAllProductByUserWishListId(Long id) {
        return wishListRepository.findAllWishListById(id);
    }

    public void addProductToWishListByUser(WishListDto wishListDto) {
        // Fetch the product using the product ID from the DTO
        Optional<Product> productOptional = productsService.
                getProductById(wishListDto.getProductId());

        // Check if the product is present
        if (productOptional.isPresent()) {
//            extrait l'objet Product réel contenu dans l'Optional,
//            permettant de continuer à travailler avec cet objet.
            Product product = productOptional.get();
            log.info("Product found for userId -: " + wishListDto.getUserId());

            // Build the WishList object
            WishList wishListInstance = WishList.builder()
                    .userId(wishListDto.getUserId())
                    .product(product)  // pass the actual product object, not the Optional
                    .build();

            // Save the WishList to the repository
            wishListRepository.save(wishListInstance);
            log.info("Product added to wishlist successfully");
        } else {
            log.error("Product with ID " + wishListDto.getProductId() +
                    " not found for userId " + wishListDto.getUserId());
            throw new RuntimeException("Product not found");
        }
    }
}
