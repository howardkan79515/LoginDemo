package com.howard.smartbee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.howard.smartbee.domain.Clint;
import com.howard.smartbee.exception.InvalidParameterException;
import com.howard.smartbee.repository.ClintRepository;
import com.howard.smartbee.security.MyUserDetails;
import com.howard.smartbee.vo.ClintVo;
import com.howard.smartbee.vo.UpdateClintVo;

@Service
public class ClintServce {
	
	@Autowired
	private ClintRepository clintRepository;
	
	public List<ClintVo> read(final String name) {
		List<Clint> clints = new ArrayList<>();
		if(StringUtils.isBlank(name)) {
			clints = clintRepository.findAll();
		} else {
			clints = clintRepository.findByName(name);
		}
		List<ClintVo> result = clints.stream()
				.map(clint -> wrapToClintVo(clint))
				.collect(Collectors.toList());
		return result;		
	}
	
	public ClintVo wrapToClintVo(Clint clint) {
		ClintVo result = new ClintVo();
		result.setId(clint.getId());
		result.setCompanyId(clint.getCompanyId());
		result.setEmail(clint.getEmail());
		result.setPhone(clint.getPhone());
		result.setName(clint.getName());
		return result;		
	}
	
	public Long create(final ClintVo clintVo) {
		Clint clint = wrapToClint(clintVo);
		Clint savedClint = clintRepository.save(clint);		
		return savedClint.getId();
	}
	
	public List<Long> batchCreate(final List<ClintVo> clintVos) {
		List<Clint> clints = clintVos.stream()
				.map(clintVo->wrapToClint(clintVo))
				.collect(Collectors.toList());
		List<Clint> savedClint = clintRepository
				.saveAll(clints);
		List<Long> result = savedClint.stream()
				.map(clint->clint.getId())
				.collect(Collectors.toList());
		return result;
	}
	
	public Long update(final UpdateClintVo updateClintVo) {
		Optional<Clint> clintOptional = clintRepository
				.findById(updateClintVo.getId());
		if(!clintOptional.isPresent()) {
			throw new InvalidParameterException("ClintId not exist.");
		}
		Clint clint = clintOptional.get();
		clint.setName(updateClintVo.getName());
		clint.setPhone(updateClintVo.getPhone());
		clint.setEmail(updateClintVo.getEmail());
		clint.setUpdateBy(MyUserDetails.getUserInfo());
		clint.setUpdateAt(new Date());
		Clint savedClint = clintRepository.save(clint);
		return savedClint.getId();
	}
	
	public void delete(final Long id) {
		clintRepository.deleteById(id);
	}
	
	private Clint wrapToClint(final ClintVo clintVo) {
		Clint result = new Clint();
		result.setName(clintVo.getName());
		result.setEmail(clintVo.getEmail());
		result.setPhone(clintVo.getPhone());
		result.setCreateBy(MyUserDetails.getUserInfo());
		result.setCreateAt(new Date());
		return result;
		
	}

}
