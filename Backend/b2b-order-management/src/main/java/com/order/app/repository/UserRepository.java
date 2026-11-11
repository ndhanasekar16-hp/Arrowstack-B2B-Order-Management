package com.order.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.entity.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity,Long> {

	

Optional<UserEntity> findByUsername(String username);

	
}
