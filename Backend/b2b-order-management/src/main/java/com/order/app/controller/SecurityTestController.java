package com.order.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SecurityTestController {

	   @GetMapping("/admin/test")
	    public String adminTest() {
	        return "ADMIN access successful";
	    }

	    @GetMapping("/customer/test")
	    public String customerTest() {
	        return "CUSTOMER access successful";
	    }
}
