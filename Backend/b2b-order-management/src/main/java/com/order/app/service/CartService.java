package com.order.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order.app.entity.CartEntity;
import com.order.app.entity.ProductEntity;
import com.order.app.entity.UserEntity;
import com.order.app.repository.CartRepository;
import com.order.app.repository.ProductRepository;
import com.order.app.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class CartService {

	private final CartRepository cartRepo;
	private final ProductRepository productRepo;
    private final UserRepository userRepo;

	public CartService(CartRepository cartRepo,ProductRepository productRepo, UserRepository  userRepo ) {
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
        this.userRepo = userRepo;
	}

	public CartEntity saveCart(CartEntity cart) {
		 UserEntity user = userRepo.findById(cart.getUser().getId())
	                .orElseThrow(() -> new RuntimeException("User not found"));
		
		 
		 ProductEntity product = productRepo.findById(cart.getProduct().getId())
	                .orElseThrow(() -> new RuntimeException("Product not found"));
		 
		 cart.setUser(user);
	        cart.setProduct(product);
		 
		 
		 
		 return cartRepo.save(cart);
	}

	public CartEntity getCartById(Long id) {
		return cartRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Cart item not found with id: " + id));
	}

	public List<CartEntity> getAllCarts() {
		return cartRepo.findAll();
	}

	public CartEntity updateCart(Long id, @Valid CartEntity cartDetails) {
		CartEntity existingCart = cartRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Cart item not found with id: " + id));

		existingCart.setUser(cartDetails.getUser());
		existingCart.setProduct(cartDetails.getProduct());
		existingCart.setQuantity(cartDetails.getQuantity());

		return cartRepo.save(existingCart);
	}

	public void deleteCart(Long id) {
		CartEntity existingCart = cartRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Cart item not found with id: " + id));

		cartRepo.delete(existingCart);
		
	}
}
