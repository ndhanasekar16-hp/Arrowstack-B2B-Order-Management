package com.order.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order.app.entity.ProductEntity;
import com.order.app.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductService {

	private final ProductRepository productRepo;
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	public ProductEntity saveProduct(ProductEntity product) {
		return productRepo.save(product);
	}

	public ProductEntity getProductById(Long id) {
		return productRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	}

	public List<ProductEntity> getAllProducts() {
		return productRepo.findAll();
	}

	public ProductEntity updateProduct(Long id, @Valid ProductEntity productDetails) {
		ProductEntity existingProduct = productRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
		existingProduct.setProductName(productDetails.getProductName());
		existingProduct.setPrice(productDetails.getPrice());

		return productRepo.save(existingProduct);
	}

	public void deleteProduct(Long id) {
		ProductEntity existingProduct = productRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		productRepo.delete(existingProduct);
	}
	
}
