package com.howard.smartbee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howard.smartbee.domain.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
	public User findByUsername(String username);

}
