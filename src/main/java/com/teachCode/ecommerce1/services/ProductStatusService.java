package com.teachCode.ecommerce1.services;




import com.teachCode.ecommerce1.entities.ProductStatus;

import java.util.List;

public interface ProductStatusService {

    List<ProductStatus> getProductStatusList();
    ProductStatus saveProductStatus(ProductStatus productStatus);  // Add this method
}
