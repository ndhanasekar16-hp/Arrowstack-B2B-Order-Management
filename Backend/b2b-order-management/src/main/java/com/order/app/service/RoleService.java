package com.order.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order.app.entity.RoleEntity;
import com.order.app.repository.RoleRepository;

import jakarta.validation.Valid;

@Service
public class RoleService {

	private final RoleRepository roleRepo;

	public RoleService(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	public RoleEntity saveRole(RoleEntity role) {
		return roleRepo.save(role);
	}

	public RoleEntity getRoleById(Long id) {
		return roleRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
	}

	public List<RoleEntity> getAllRoles() {
		return roleRepo.findAll();
	}

	public RoleEntity updateRole(Long id, @Valid RoleEntity roleDetails) {
		RoleEntity existingRole = roleRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

		existingRole.setRoleName(roleDetails.getRoleName());

		return roleRepo.save(existingRole);
	}

	public void deleteRole(Long id) {
		RoleEntity existingRole = roleRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

		roleRepo.delete(existingRole);
	}
	
}
