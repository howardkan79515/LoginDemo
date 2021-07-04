package com.howard.smartbee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.howard.smartbee.domain.Clint;
import com.howard.smartbee.domain.Company;
import com.howard.smartbee.repository.ClintRepository;
import com.howard.smartbee.repository.CompanyRepository;
import com.howard.smartbee.vo.ClintVo;
import com.howard.smartbee.vo.CompanyVo;
import com.howard.smartbee.vo.CreateCompanyVo;

class CompanyServiceTest {
	
	@InjectMocks
	private CompanyService companyService;
	
	@Mock
	private CompanyRepository companyRepository;
	
	@Mock
	private ClintRepository clintRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unused")
	@Test
	void test_save_success() {
		CreateCompanyVo companyVo = getInputValue();
		Company company = getSavedValue();
		when(companyRepository.save(ArgumentMatchers.any()))
		.thenReturn(company);
		List<Clint> clints = getClints();
		when(clintRepository.findByIdIn(ArgumentMatchers.any()))
		.thenReturn(getClints());
		companyService.create(companyVo);
		ArgumentCaptor<Company> argumentCaptor = ArgumentCaptor
				.forClass(Company.class);
		verify(companyRepository, times(1)).save(argumentCaptor.capture());
		Company result = argumentCaptor.<Company> getValue();
		verify(clintRepository, times(1)).saveAll(ArgumentMatchers.any()); 
		assertEquals(result.getCreateBy(), "visiter");
		assertEquals(result.getName(), "SmartBee");
		assertEquals(result.getAddress(), "Tapie xxxxx");
	}
	
	@Test
	void test_read_success() {
		when(companyRepository.findAll())
		.thenReturn(getCompanys());
		List<CompanyVo> result = companyService.read(null, null);
		ClintVo clint = new ClintVo();
		clint.setEmail("howardkan@gmail.com");
		clint.setName("Howard");
		clint.setId(1L);
		CompanyVo expect = result.get(0);
		assertAll("expect",
	            () -> assertEquals("google", expect.getName()),
	            () -> assertEquals("USA", expect.getAddress()),
	            () -> assertEquals("Howard", expect.getCreateBy())
	        );
	}
	
	private List<Company> getCompanys() {
		Company google = new Company();
		google.setName("google");
		google.setAddress("USA");
		google.setCreateBy("Howard");
		google.setClints(getClints());
		return Arrays.asList(google);
	}
	
	@Test
	void test_delete_success() {
		when(clintRepository
				.findByCompanyId(1L))
		.thenReturn(getClints());
		companyService.deleteCompany(1L);
		verify(clintRepository, times(1)).saveAll(ArgumentMatchers.any()); 
	}
		
	private List<Clint> getClints() {
		Clint clint = new Clint();
		clint.setEmail("howardkan@gmail.com");
		clint.setName("Howard");
		clint.setId(1L);
		List<Clint> result = new ArrayList<>();
		result.add(clint);
		return result;
	}

	private Company getSavedValue() {
		Company company = new Company();
		company.setId(1L);
		company.setName("SmartBee");
		company.setAddress("Tapie xxxxx");
		return company;
	}

	private CreateCompanyVo getInputValue() {
		CreateCompanyVo companyVo = new CreateCompanyVo();
		companyVo.setName("SmartBee");
		companyVo.setAddress("Tapie xxxxx");
		companyVo.setClintIds(new ArrayList<>());
		return companyVo;
	}

}
