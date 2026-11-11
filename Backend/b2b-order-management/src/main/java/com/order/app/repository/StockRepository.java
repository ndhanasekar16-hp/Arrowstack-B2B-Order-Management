package com.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.entity.StockEntity;

public interface StockRepository extends JpaRepository <StockEntity,Long> {

}
