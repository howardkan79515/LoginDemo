package com.howard.smartbee.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import com.howard.smartbee.domain.Role;
import com.howard.smartbee.domain.User;

public class MyUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4234905100148684352L;
	
	private User user;
	
	public MyUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	public static String getUserInfo() {
		Object principal = getPrinciple();
		String username = null;
		if (principal instanceof UserDetails) {
		    username = ((UserDetails)principal).getUsername();
		} else if (principal instanceof String){
		    username = (String)principal;
		} else {
			username = principal.toString();	
		}
		return username;
	}

	private static Object getPrinciple() {
		 try {
			 return SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		 } catch(NullPointerException e) {
			 return "visiter";
		 }
	}

}
