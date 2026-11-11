package com.order.app.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.app.entity.CartEntity;
import com.order.app.entity.OrderEntity;
import com.order.app.entity.ProductEntity;
import com.order.app.repository.CartRepository;
import com.order.app.repository.OrderRepository;
import com.order.app.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class OrderService {

	private final OrderRepository orderRepo;
	private final CartRepository cartRepo;
	private final ProductRepository productRepo;
	private final InvoiceService invoiceService; 
	
	public OrderService(OrderRepository orderRepo , CartRepository cartRepo, ProductRepository productRepo , InvoiceService  invoiceService){
		this.orderRepo = orderRepo;
		this.cartRepo =cartRepo;
		this.productRepo = productRepo;
		this.invoiceService = invoiceService;
	}

	public OrderEntity saveOrder(OrderEntity order) {
		return orderRepo.save(order);
	}

	public OrderEntity getOrderById(Long id) {
		return orderRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
	}

	public List<OrderEntity> getAllOrders() {
		return orderRepo.findAll();
	}

	public OrderEntity updateOrder(Long id, @Valid OrderEntity orderDetails) {
		OrderEntity existingOrder = orderRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

		existingOrder.setUser(orderDetails.getUser());
		existingOrder.setOrderDate(orderDetails.getOrderDate());
		existingOrder.setTotalAmount(orderDetails.getTotalAmount());
		existingOrder.setStatus(orderDetails.getStatus());

		return orderRepo.save(existingOrder);
	}

	public void deleteOrder(Long id) {
		OrderEntity existingOrder = orderRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

		orderRepo.delete(existingOrder);
	}
	
	
	
	@Transactional
	public OrderEntity createOrder(Long userId) {

	    List<CartEntity> cartItems = cartRepo.findByUser_Id(userId);

	    if (cartItems.isEmpty()) {
	        throw new RuntimeException("Cart is empty");
	    }

	    BigDecimal total = BigDecimal.ZERO;

	    for (CartEntity cart : cartItems) {

	        ProductEntity product = cart.getProduct();

	        if (product.getStockQuantity() < cart.getQuantity()) {
	            throw new RuntimeException(
	                    "Insufficient stock for: " + product.getProductName());
	        }

	        BigDecimal price = BigDecimal.valueOf(product.getPrice());

	        total = total.add(price.multiply(
	                BigDecimal.valueOf(cart.getQuantity())));

	        product.setStockQuantity(
	                product.getStockQuantity() - cart.getQuantity());

	        productRepo.save(product);
	    }

	    OrderEntity order = new OrderEntity();
	    order.setUser(cartItems.get(0).getUser());
	    order.setOrderDate(LocalDateTime.now());
	    order.setTotalAmount(total);
	    order.setStatus("PLACED");

	    OrderEntity savedOrder = orderRepo.save(order);

	    invoiceService.generateInvoice(savedOrder.getId());
	    
	    cartRepo.deleteAll(cartItems);

	    return savedOrder;
	}
	
	
	
	
}
