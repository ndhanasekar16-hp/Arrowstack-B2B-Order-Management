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

import com.order.app.entity.CartEntity;
import com.order.app.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer/carts")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping
	public CartEntity saveCart(@Valid @RequestBody CartEntity cart) {
		return cartService.saveCart(cart);
	}

	@GetMapping("/{id}")
	public CartEntity getCartById(@PathVariable Long id) {
		return cartService.getCartById(id);
	}

	@GetMapping("/list")
	public List<CartEntity> getAllCarts() {
		return cartService.getAllCarts();
	}

	@PutMapping("/{id}")
	public CartEntity updateCart(@PathVariable Long id, @Valid @RequestBody CartEntity cartDetails) {
		return cartService.updateCart(id, cartDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);
	}

}
