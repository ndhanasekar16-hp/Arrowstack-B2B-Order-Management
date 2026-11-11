package com.order.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.app.entity.ProductEntity;
import com.order.app.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/products")
public class ProductController {
	
	private final ProductService Productservice;
	public ProductController(ProductService Productservice) {
		this.Productservice = Productservice;
	}

	@PostMapping
	public ProductEntity saveProduct(@Valid @RequestBody ProductEntity product) {
		return Productservice.saveProduct(product);
	}

	@GetMapping("/{id}")
	public ProductEntity getProductById(@PathVariable Long id) {
		return Productservice.getProductById(id);
	}

	@GetMapping("/list")
	public List<ProductEntity> getAllProducts() {
		return Productservice.getAllProducts();
	}

	@PutMapping("/{id}")
	public ProductEntity updateProduct(@PathVariable Long id, @Valid @RequestBody ProductEntity productDetails) {
		return Productservice.updateProduct(id, productDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		Productservice.deleteProduct(id);
	}
}
