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

import com.order.app.entity.InvoiceEntity;
import com.order.app.service.InvoiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	private final InvoiceService invoiceService;

	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@PostMapping
	public InvoiceEntity saveInvoice(@Valid @RequestBody InvoiceEntity invoice) {
		return invoiceService.saveInvoice(invoice);
	}

	@GetMapping("/{id}")
	public InvoiceEntity getInvoiceById(@PathVariable Long id) {
		return invoiceService.getInvoiceById(id);
	}

	@GetMapping("/list")
	public List<InvoiceEntity> getAllInvoices() {
		return invoiceService.getAllInvoices();
	}

	@PutMapping("/{id}")
	public InvoiceEntity updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceEntity invoiceDetails) {
		return invoiceService.updateInvoice(id, invoiceDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteInvoice(@PathVariable Long id) {
		invoiceService.deleteInvoice(id);
	}
}
