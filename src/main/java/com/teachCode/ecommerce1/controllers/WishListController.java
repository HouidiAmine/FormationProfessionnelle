package com.teachCode.ecommerce1.controllers;


import com.teachCode.ecommerce1.dto.request.WishListDto;
import com.teachCode.ecommerce1.entities.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin()
public class WishListController {

	@Autowired
	WishlistService wishlistService;

    public WishListController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String addProductToWishList(@RequestBody WishListDto wishListDto){
        wishlistService.addProductToWishListByUser(wishListDto);
        return "Product Added to Wishlist successfully";
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{userId}")
    public List<WishList> getAllProductByUserWishListId(@PathVariable(value = "userId") Long userId) {
        return wishlistService.getAllProductByUserWishListId(userId);
    }
}
