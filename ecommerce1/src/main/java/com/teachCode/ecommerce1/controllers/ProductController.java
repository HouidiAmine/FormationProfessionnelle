package com.teachCode.ecommerce1.controllers;


import com.teachCode.ecommerce1.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductsService productsService;


    // POST method to create a product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productsService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);  // Return 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // Return 400 Bad Request for invalid data
        }
    }
    @GetMapping("/bystatus/{id}/{page}/{size}")
    public ResponseEntity<Page<Product>> getProductByStatusId(@PathVariable(value = "id") Long id,
                                                              @PathVariable(value = "page") int page,
                                                              @PathVariable(value = "size") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductByStatusId(id, page, size));
    }

    @GetMapping("/search/{name}/{page}/{size}")
    public ResponseEntity<Page<Product>> searchProductByName(@PathVariable("name") String name,
                                                             @PathVariable("page") int page,
                                                             @PathVariable("size") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.searchProductByName(name, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductById(id));
    }

    @GetMapping("/bycategory/random/{id}")
    public ResponseEntity<List<Product>> getRandomProductByCategoryIdLimit(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getRandomProductByCategoryIdLimit(id));
    }

    @GetMapping("/bystatus/random/{id}")
    public ResponseEntity<List<Product>> getRandomProductByStatusIdLimit(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductByStatusIdLimit(id));
    }


}

