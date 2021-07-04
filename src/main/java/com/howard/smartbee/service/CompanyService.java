package com.howard.smartbee.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.howard.smartbee.domain.Clint;
import com.howard.smartbee.domain.Company;
import com.howard.smartbee.exception.InvalidParameterException;
import com.howard.smartbee.repository.ClintRepository;
import com.howard.smartbee.repository.CompanyRepository;
import com.howard.smartbee.security.MyUserDetails;
import com.howard.smartbee.vo.ClintVo;
import com.howard.smartbee.vo.CompanyVo;
import com.howard.smartbee.vo.CreateCompanyVo;
import com.howard.smartbee.vo.UpdateCompanyVo;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ClintRepository clintRepository;
	
	
	public List<CompanyVo> read(final Long id, final String name) {
		List<Company> companys = readCompanys(id, name);
		List<CompanyVo> result = companys.stream()
				.map(company->wrapToCompanyVo(company))
				.collect(Collectors.toList());
		return result;		
	}
	
	public Long create(final CreateCompanyVo companyVo) {
		Company company = wrapToCompany(companyVo);
		Company savedCompany = companyRepository.save(company);
		updateClints(companyVo.getClintIds(), savedCompany.getId());
		return savedCompany.getId();		
	}
	
	public Long update(final UpdateCompanyVo updateCompanyVo) {
		Optional<Company> comapnyOptional = companyRepository
				.findById(updateCompanyVo.getId());
		if(!comapnyOptional.isPresent()) {
			throw new InvalidParameterException("CompanyId not exist.");
		}
		Company company = comapnyOptional.get();
		company.setName(updateCompanyVo.getName());
		company.setAddress(updateCompanyVo.getAddress());
		company.setUpdateBy(MyUserDetails.getUserInfo());
		company.setUpdateAt(new Date());
		Company savedCompany = companyRepository.save(company);
		updateClints(updateCompanyVo.getClintIds(), 
				savedCompany.getId());
		return savedCompany.getId();	
	}
	
	private CompanyVo wrapToCompanyVo(final Company company) {
		CompanyVo result = new CompanyVo();
		result.setId(company.getId());
		result.setAddress(company.getAddress());
		result.setName(company.getName());
		result.setCreateBy(company.getCreateBy());
		result.setUpdateBy(company.getUpdateBy());
		if(company.getClints() == null) {
			result.setClints(new ArrayList<>());
			return result;
		}
		List<ClintVo> clints = company.getClints().stream()
				.map(clint->wrapToClintVo(clint))
				.collect(Collectors.toList());
		result.setClints(clints);
		return result;		
	}


	private List<Company> readCompanys(final Long id, 
			final String name) {
		List<Company> companys = new ArrayList<>();
		if(StringUtils.isNotBlank(name)) {
			companys = companyRepository.findByName(name);
		} else if(id != null) {
			companys = Arrays.asList(companyRepository.findById(id).get());
		} else {
			companys = companyRepository.findAll();
		}
		return companys;
	}
	
	
	private void updateClints(final List<Long> clintIds, 
			final Long companyId) {
		List<Clint> clints = clintRepository
				.findByIdIn(clintIds);
		if(clints.isEmpty()) {
			return;
		}
		for(Clint clint : clints) {
			clint.setCompanyId(companyId);
		}
		clintRepository.saveAll(clints);
	}
	
	public void deleteCompany(final Long id) {
		List<Clint> clints = clintRepository
				.findByCompanyId(id);
		for(Clint clint : clints) {
			clint.setCompanyId(null);
		}
		clintRepository.saveAll(clints);
		companyRepository.deleteById(id);
	}
	
	private Company wrapToCompany(CreateCompanyVo companyVo) {
		Company result = new Company();
		result.setName(companyVo.getName());
		result.setAddress(companyVo.getAddress());
		result.setCreateBy(MyUserDetails.getUserInfo());
		result.setCreateAt(new Date());
		return result;		
	}
	
	private ClintVo wrapToClintVo(Clint clint) {
		ClintVo result = new ClintVo();
		result.setId(clint.getId());
		result.setCompanyId(clint.getCompanyId());
		result.setEmail(clint.getEmail());
		result.setPhone(clint.getPhone());
		result.setName(clint.getName());
		return result;		
	}
	
	

}
