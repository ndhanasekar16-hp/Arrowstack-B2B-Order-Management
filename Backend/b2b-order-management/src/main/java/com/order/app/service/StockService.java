package com.order.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order.app.entity.StockEntity;
import com.order.app.repository.StockRepository;

import jakarta.validation.Valid;

@Service
public class StockService {

	private final StockRepository stockRepo;

	public StockService(StockRepository stockRepo) {
		this.stockRepo = stockRepo;
	}

	public StockEntity saveStock(StockEntity stock) {
		return stockRepo.save(stock);
	}

	public StockEntity getStockById(Long id) {
		return stockRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock record not found with id: " + id));
	}

	public List<StockEntity> getAllStocks() {
		return stockRepo.findAll();
	}

	public StockEntity updateStock(Long id, @Valid StockEntity stockDetails) {
		StockEntity existingStock = stockRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock record not found with id: " + id));

		existingStock.setProduct(stockDetails.getProduct());
		existingStock.setQuantity(stockDetails.getQuantity());
		existingStock.setLastUpdated(stockDetails.getLastUpdated());

		return stockRepo.save(existingStock);
	}

	public void deleteStock(Long id) {
		StockEntity existingStock = stockRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock record not found with id: " + id));

		stockRepo.delete(existingStock);
	}
	
}
