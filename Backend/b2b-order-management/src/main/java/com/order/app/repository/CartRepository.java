package com.order.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

	
	List<CartEntity> findByUser_Id(Long userId);
}
