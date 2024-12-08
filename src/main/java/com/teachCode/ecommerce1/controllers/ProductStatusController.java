package com.teachCode.ecommerce1.controllers;


import com.teachCode.ecommerce1.entities.ProductStatus;
import com.teachCode.ecommerce1.services.ProductStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductStatusController {

    @Autowired
    private ProductStatusService productStatusService;

    @GetMapping("/productstatus")
    public List<ProductStatus> getProductStatusList(){
        return productStatusService.getProductStatusList();
    }

    // Endpoint to create a new ProductStatus
    @PostMapping
    public ResponseEntity<ProductStatus> createProductStatus(@RequestBody ProductStatus productStatus) {
        try {
            ProductStatus createdStatus = productStatusService.saveProductStatus(productStatus);
            return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

