package com.order.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.app.entity.UserEntity;
import com.order.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService uservice;
	public UserController(UserService uservice) {
		this.uservice = uservice;
	}
	
	
	@PostMapping
	public UserEntity saveUser(@Valid @RequestBody UserEntity user, @RequestParam Long roleId) {
		return uservice.saveUser(user,roleId);
	}
	
	
		@GetMapping("/{id}")
		public UserEntity getUserById(@PathVariable Long id) {
			return uservice.getUserById(id);
		}
		
		
		@GetMapping("/list")
		public List<UserEntity> getAllUsers() {
			return uservice.getAllUsers();
		}
		
		
		@PutMapping("/{id}")
		public UserEntity updateUser(@PathVariable Long id, @Valid @RequestBody UserEntity userDetails) {
			return uservice.updateUser(id, userDetails);
		}
		

		@DeleteMapping("/{id}")
		public void deleteUser(@PathVariable Long id) {
			uservice.deleteUser(id);
		}
	
}
