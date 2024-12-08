package com.teachCode.ecommerce1.services;


import com.teachCode.ecommerce1.entities.ProductStatus;
import com.teachCode.ecommerce1.repositories.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

    @Autowired
    private ProductStatusRepository productStatusRepository;


    @Override
    public List<ProductStatus> getProductStatusList() {
        return productStatusRepository.findAll();
    }

    @Override
    public ProductStatus saveProductStatus(ProductStatus productStatus) {
        return productStatusRepository.save(productStatus);
    }
}
