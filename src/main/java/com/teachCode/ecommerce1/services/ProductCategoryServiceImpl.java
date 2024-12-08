package com.teachCode.ecommerce1.services;


import com.teachCode.ecommerce1.entities.ProductCategory;
import com.teachCode.ecommerce1.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryRepository.findAll();
	}

	public ProductCategory createCategory(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	// Update an existing product category
	public ProductCategory updateCategory(Long id, ProductCategory productCategory) {
		Optional<ProductCategory> existingCategory = productCategoryRepository.findById(id);
		if (existingCategory.isPresent()) {
			ProductCategory categoryToUpdate = existingCategory.get();
			categoryToUpdate.setCategoryName(productCategory.getCategoryName()); // Use categoryName here
			// Update other fields as required
			return productCategoryRepository.save(categoryToUpdate);
		}
		throw new RuntimeException("Category not found with id: " + id); // Handle case when category is not found
	}

	// Delete a product category
	public void deleteCategory(Long id) {
		Optional<ProductCategory> existingCategory = productCategoryRepository.findById(id);
		if (existingCategory.isPresent()) {
			productCategoryRepository.delete(existingCategory.get());
		} else {
			throw new RuntimeException("Category not found with id: " + id); // Handle case if category not found
		}
	}

}
