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

import com.howard.smartbee.service.ClintServce;
import com.howard.smartbee.vo.ClintVo;
import com.howard.smartbee.vo.ResultVo;
import com.howard.smartbee.vo.UpdateClintVo;



@RestController
@RequestMapping("/api/clint")
public class ClintController {
	
	@Autowired
	private ClintServce clintServce;
	
	@PostMapping("/batchCreate")
    public ResultVo<?> batchCreate(@Valid @RequestBody 
    		final List<ClintVo> clintVos) {
		List<Long> result = clintServce
				.batchCreate(clintVos);
		return ResultVo.genSuccessResult(result);	
	}
	
	@PostMapping("/create")
    public ResultVo<?> create(@Valid @RequestBody 
    		final ClintVo clintVo) {
		Long result = clintServce.create(clintVo);
		return ResultVo.genSuccessResult(result);	
	}
		
	@GetMapping(value = "/read")
	public ResultVo<?> findClint(
			@RequestParam(value = "name", required = false) String name) {
		List<ClintVo> result = clintServce.read(name);
		return ResultVo.genSuccessResult(result);
	}
	
	@PutMapping(value = "/update")
	public ResultVo<?> updateClint(@Valid @RequestBody 
			UpdateClintVo updateCompanyVo) {
		Long result = clintServce
				.update(updateCompanyVo);
        return ResultVo.genSuccessResult(result);
	}
	
	@DeleteMapping("/{id}")
	public ResultVo<?> deleteClint(
			@PathVariable("id") Long id) {
		clintServce.delete(id);
        return ResultVo.genSuccessResult(null);
	}

}
