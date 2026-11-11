package com.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.entity.InvoiceEntity;

public interface InvoiceRepository extends JpaRepository <InvoiceEntity,Long> {

	 boolean existsByOrderId(Long orderId);
}
