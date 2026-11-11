package com.order.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.order.app.entity.InvoiceEntity;
import com.order.app.entity.OrderEntity;
import com.order.app.repository.InvoiceRepository;
import com.order.app.repository.OrderRepository;

import jakarta.validation.Valid;

@Service
public class InvoiceService {

	
	private final InvoiceRepository invoiceRepo;
	private final OrderRepository orderrepo;

	public InvoiceService(InvoiceRepository invoiceRepo , OrderRepository orderrepo) {
		this.invoiceRepo = invoiceRepo;
		this.orderrepo = orderrepo;
	}

	public InvoiceEntity saveInvoice(InvoiceEntity invoice) {
		return invoiceRepo.save(invoice);
	}

	public InvoiceEntity getInvoiceById(Long id) {
		return invoiceRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
	}

	public List<InvoiceEntity> getAllInvoices() {
		return invoiceRepo.findAll();
	}

	public InvoiceEntity updateInvoice(Long id, @Valid InvoiceEntity invoiceDetails) {
		InvoiceEntity existingInvoice = invoiceRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

		existingInvoice.setOrder(invoiceDetails.getOrder());
		existingInvoice.setInvoiceNumber(invoiceDetails.getInvoiceNumber());
		existingInvoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
		existingInvoice.setTotalAmount(invoiceDetails.getTotalAmount());
		existingInvoice.setStatus(invoiceDetails.getStatus());

		return invoiceRepo.save(existingInvoice);
	}

	public void deleteInvoice(Long id) {
		InvoiceEntity existingInvoice = invoiceRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

		invoiceRepo.delete(existingInvoice);
	}
	
	
	
	public InvoiceEntity generateInvoice(Long orderId) {

		if (invoiceRepo.existsByOrderId(orderId)) {
			throw new RuntimeException("Invoice already generated for Order ID: " + orderId);
		}
		
		
	    OrderEntity order = orderrepo.findById(orderId)
	            .orElseThrow(() ->
	                    new RuntimeException("Order not found with id: " + orderId));

	    InvoiceEntity invoice = new InvoiceEntity();

	    invoice.setOrder(order);
	    invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
	    invoice.setInvoiceDate(LocalDateTime.now());
	    invoice.setTotalAmount(order.getTotalAmount());
	    invoice.setStatus("GENERATED");

	    return invoiceRepo.save(invoice);
	}
	
	
}
