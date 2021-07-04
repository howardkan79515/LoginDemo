package com.howard.smartbee.vo;

import java.util.List;


public class CompanyVo {
	
	private Long id;
	
	private String name;
	
	private String address;
	
	private String createBy;
	
	private String updateBy;
	
	private List<ClintVo> clints;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<ClintVo> getClints() {
		return clints;
	}

	public void setClints(List<ClintVo> clints) {
		this.clints = clints;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

}
