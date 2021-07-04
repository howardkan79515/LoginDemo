package com.howard.smartbee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howard.smartbee.domain.Clint;

public interface ClintRepository extends JpaRepository<Clint, Long>{
	
	List<Clint> findByName(String name);
	
	List<Clint> findByIdIn(List<Long> ids);
	
	List<Clint> findByCompanyId(Long companyId);
}
