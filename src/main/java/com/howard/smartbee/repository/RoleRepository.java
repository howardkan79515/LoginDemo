package com.howard.smartbee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howard.smartbee.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
