package com.teachCode.ecommerce1.controllers;



import com.teachCode.ecommerce1.dto.request.CartDto;
import com.teachCode.ecommerce1.dto.response.DtoCart;
import com.teachCode.ecommerce1.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:8084")
@Slf4j
//@CrossOrigin(origins = "http://example.com", methods = {RequestMethod.GET, RequestMethod.POST})
//@CrossOrigin(origins = "http://localhost:3000") // Permet uniquement à React de faire des requêtes

public class CartController {

	@Autowired
	CartService cartService;

	@PutMapping("/addproduct")
	public String addToCart(@RequestBody CartDto cartDto) {
		return cartService.addItemsToCart(cartDto);
	}

	@GetMapping("/cartitems/{user_id}")
	public List<DtoCart> getCartItems(@PathVariable(value = "user_id") String userId) {
		return cartService.getCartItems(userId);
	}

	@DeleteMapping("{product_id}/{item_id}")
	public void deleteProduct(@PathVariable(value = "product_id") String product_id,
			@PathVariable(value = "item_id") Long item_id) {
		cartService.deleteProduct(product_id, item_id);
	}

}
