package com.howard.smartbee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.howard.smartbee.service.CompanyService;
import com.howard.smartbee.vo.CompanyVo;
import com.howard.smartbee.vo.CreateCompanyVo;
import com.howard.smartbee.vo.ResultVo;
import com.howard.smartbee.vo.UpdateCompanyVo;




@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping(value = "/read")
	public ResultVo<?> findCompany(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name) {
		List<CompanyVo> result = companyService.read(id, name);
		return ResultVo.genSuccessResult(result);
	}
	
	@PostMapping(value = "/create")
	public ResultVo<?> creatCompany(@Valid @RequestBody 
			CreateCompanyVo companyVo) {
		Long result = companyService
				.create(companyVo);
        return ResultVo.genSuccessResult(result);
	}
	
	@PutMapping(value = "/update")
	public ResultVo<?> updateCompany(@Valid @RequestBody 
			UpdateCompanyVo updateCompanyVo) {
		Long result = companyService
				.update(updateCompanyVo);
        return ResultVo.genSuccessResult(result);
	}
	
	@DeleteMapping("/{id}")
	public ResultVo<?> deleteCompany(
			@PathVariable("id") Long id) {
		companyService.deleteCompany(id);
        return ResultVo.genSuccessResult(null);
	}

}
