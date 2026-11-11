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

import com.order.app.entity.RoleEntity;
import com.order.app.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping
	public RoleEntity saveRole(@Valid @RequestBody RoleEntity role) {
		return roleService.saveRole(role);
	}

	@GetMapping("/{id}")
	public RoleEntity getRoleById(@PathVariable Long id) {
		return roleService.getRoleById(id);
	}

	@GetMapping("/list")
	public List<RoleEntity> getAllRoles() {
		return roleService.getAllRoles();
	}

	@PutMapping("/{id}")
	public RoleEntity updateRole(@PathVariable Long id, @Valid @RequestBody RoleEntity roleDetails) {
		return roleService.updateRole(id, roleDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
}
