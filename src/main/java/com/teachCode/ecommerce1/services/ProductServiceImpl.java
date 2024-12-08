package com.teachCode.ecommerce1.services;


import com.teachCode.ecommerce1.entities.Product;
import com.teachCode.ecommerce1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProductByStatusId(Long id, int page, int size){
        return productRepository.findByProductStatusId(id, PageRequest.of(page, size));
    }

    @Override
    public List<Product> getProductByStatusIdLimit(Long id) {

        return productRepository.findByProductStatusIdLimit(id);
    }


    @Override
    // En Java, Optional est une manière sécurisée de représenter
    // un objet qui peut ou non exister (il peut être présent ou absent).
    // L'API Optional aide à éviter les erreurs de type NullPointerException en forçant
    // les développeurs à traiter les valeurs manquantes explicitement.
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public Page<Product> searchProductByName(String name, int page, int size) {
        return productRepository.findByNameContaining(name, PageRequest.of(page, size));
    }
    @Override
    public List<Product> getRandomProductByCategoryIdLimit(Long id) {
        return productRepository.findByCategoryIdLimit(id);
    }

    @Override
    public Product createProduct(Product product) {
        // Validate the product
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getUnitPrice() == null || product.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        if (product.getUnitsInStock() == null || product.getUnitsInStock() < 0) {
            throw new IllegalArgumentException("Units in stock must be greater than or equal to zero");
        }

        // Set the creation and update times
        product.setDateCreated(Instant.now());
        product.setLastUpdated(Instant.now());

        // Save the product to the database
        return productRepository.save(product);
    }


}
