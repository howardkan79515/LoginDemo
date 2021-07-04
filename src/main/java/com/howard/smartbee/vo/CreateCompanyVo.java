package com.howard.smartbee.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateCompanyVo {
	
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String address;
	
	@NotNull
	private List<Long> clintIds;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Long> getClintIds() {
		return clintIds;
	}

	public void setClintIds(List<Long> clintIds) {
		this.clintIds = clintIds;
	}


}
