package com.teachCode.ecommerce1;

import com.teachCode.ecommerce1.dto.response.DtoCart;
import com.teachCode.ecommerce1.repositories.CartRepository;
import com.teachCode.ecommerce1.services.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Ecommerce1ApplicationTests {
	@Autowired
	CartService cartService;

	@Test
	void contextLoads() {
		List<DtoCart> liste=cartService.getCartItems("2");
		System.out.println("ma résultat");
		System.out.println(liste);
	}


}
