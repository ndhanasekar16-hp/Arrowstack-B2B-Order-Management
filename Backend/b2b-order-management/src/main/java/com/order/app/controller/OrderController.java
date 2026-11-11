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

import com.order.app.entity.OrderEntity;
import com.order.app.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer/orders")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	
	
	
	@PostMapping
	public OrderEntity saveOrder(@Valid @RequestBody OrderEntity order) {
		return orderService.saveOrder(order);
	}

	@GetMapping("/{id}")
	public OrderEntity getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@GetMapping("/list")
	public List<OrderEntity> getAllOrders() {
		return orderService.getAllOrders();
	}

	@PutMapping("/{id}")
	public OrderEntity updateOrder(@PathVariable Long id, @Valid @RequestBody OrderEntity orderDetails) {
		return orderService.updateOrder(id, orderDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	
	@PostMapping("/create/{userId}")
	public OrderEntity createOrder(@PathVariable Long userId) {
	    return orderService.createOrder(userId);
	}

}
