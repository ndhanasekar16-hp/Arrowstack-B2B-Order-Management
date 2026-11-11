package com.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity ,Long> {

}
