package com.teachCode.ecommerce1.controllers;


import com.teachCode.ecommerce1.entities.ProductCategory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Allow only your Angular app's origin
@RequestMapping("/productcategory")
@AllArgsConstructor
public class ProductCategoryController {

    private ProductCategoryServiceImpl productCategoryService;

    @GetMapping
    public List<ProductCategory> getProductCategoryList(){
        return productCategoryService.getProductCategoryList();
    }

    @PostMapping
    public ProductCategory createProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.createCategory(productCategory);
    }

    // Update an existing product category by ID
    @PutMapping("/{id}")
    public ProductCategory updateProductCategory(
            @PathVariable Long id,
            @RequestBody ProductCategory productCategory) {
        return productCategoryService.updateCategory(id, productCategory);
    }

    // Delete a product category by ID
    @DeleteMapping("/{id}")
    public String deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteCategory(id);
        return "Category with ID " + id + " has been deleted.";
    }

}
