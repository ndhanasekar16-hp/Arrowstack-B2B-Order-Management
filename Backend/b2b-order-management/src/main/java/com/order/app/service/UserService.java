package com.order.app.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.order.app.entity.RoleEntity;
import com.order.app.entity.UserEntity;
import com.order.app.repository.RoleRepository;
import com.order.app.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepo,RoleRepository roleRepo ,PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo =roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public UserEntity saveUser(UserEntity user, Long roleId) {
		RoleEntity role = roleRepo.findById(roleId)
		        .orElseThrow(() -> new RuntimeException("Role not found"));

		user.setRole(role);
		 
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepo.save(user);
	}

	public UserEntity getUserById(Long id) {
		
		return userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}

	public List<UserEntity> getAllUsers() {
		
		return userRepo.findAll() ;
	}

	public UserEntity updateUser(Long id, @Valid UserEntity userDetails) {
		UserEntity existingUser = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		
		existingUser.setUsername(userDetails.getUsername());
		existingUser.setEmail(userDetails.getEmail());
		return userRepo.save(existingUser);
	}

	public void deleteUser(Long id) {
		
		UserEntity existingUser = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		
		userRepo.delete(existingUser);
	}
}
