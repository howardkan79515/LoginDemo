package com.howard.smartbee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howard.smartbee.domain.Company;



public interface CompanyRepository extends JpaRepository<Company, Long>{

	List<Company> findByName(String name);
	
}
