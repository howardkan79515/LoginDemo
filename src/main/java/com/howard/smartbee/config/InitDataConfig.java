package com.howard.smartbee.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.howard.smartbee.domain.Role;
import com.howard.smartbee.domain.User;
import com.howard.smartbee.repository.RoleRepository;
import com.howard.smartbee.repository.UserRepository;
import com.howard.smartbee.service.ClintServce;
import com.howard.smartbee.service.CompanyService;
import com.howard.smartbee.vo.ClintVo;
import com.howard.smartbee.vo.CreateCompanyVo;
@Configuration
public class InitDataConfig {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ClintServce clintServce;
	
	@Autowired
	private CompanyService companyService;
	
	@EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
    	initUserAndRole(); 
        initClint();
        initCompany();
    }


	private void initUserAndRole() {
		Role role = wrapToRole("SUPERUSER");
        Role manager = wrapToRole("MANAGER");
        Role operator = wrapToRole("OPERATOR");
        User root = wrapToUser("root", "root", role);
        User business = wrapToUser("business", "business", manager);
        User user = wrapToUser("user", "user", operator);
        roleRepository.saveAll(Arrays.asList(role, manager, operator));
        repository.saveAll(Arrays.asList(root, business, user));
	}


    private void initClint() {
		ClintVo clintVo = new ClintVo();
		clintVo.setEmail("howardkan79515@gmail.com");
		clintVo.setName("Google");
		clintVo.setPhone("0988777666");
		ClintVo azureVo = new ClintVo();
		azureVo.setEmail("howardkan@hotmail.com");
		azureVo.setName("Azure");
		azureVo.setPhone("0988777676");
		clintServce.create(clintVo);
		clintServce.create(azureVo);
    }
    
    private void initCompany() {
    	CreateCompanyVo companyVo = new CreateCompanyVo();
		companyVo.setName("Htc");
		companyVo.setAddress("New Taipei City");
		companyVo.setClintIds(new ArrayList<>());
		companyService.create(companyVo);
    }
    
	private Role wrapToRole(String name) {
		Role role = new Role();
    	role.setName(name);
		return role;
	}

	private User wrapToUser(String userName, String pwd, Role role) {
		User user = new User(); 
        user.setUsername(userName);
        user.setEnabled(true);
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(pwd));
		return user;
	}

}
