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

import com.order.app.entity.StockEntity;
import com.order.app.service.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stocks")
public class StockController {

	private final StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@PostMapping
	public StockEntity saveStock(@Valid @RequestBody StockEntity stock) {
		return stockService.saveStock(stock);
	}

	@GetMapping("/{id}")
	public StockEntity getStockById(@PathVariable Long id) {
		return stockService.getStockById(id);
	}

	@GetMapping("/list")
	public List<StockEntity> getAllStocks() {
		return stockService.getAllStocks();
	}

	@PutMapping("/{id}")
	public StockEntity updateStock(@PathVariable Long id, @Valid @RequestBody StockEntity stockDetails) {
		return stockService.updateStock(id, stockDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteStock(@PathVariable Long id) {
		stockService.deleteStock(id);
	}
}
