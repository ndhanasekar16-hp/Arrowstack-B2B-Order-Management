package com.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

}
