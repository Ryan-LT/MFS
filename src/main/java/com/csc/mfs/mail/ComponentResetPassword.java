package com.csc.mfs.mail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.csc.mfs.model.User;
import com.csc.mfs.service.UserService;

@Component
public class ComponentResetPassword {
	@Autowired
	private UserService userService;
	
	@Cacheable("passwordConfirm")
	public String getConfirm(String email){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		User user = userService.findUserByEmail(email);
		if(null!=user){
			String token = UUID.randomUUID().toString();
			token+=bCryptPasswordEncoder.encode(token);
			return token.replaceAll("/", "");	
		} else {
			return null;
		}
		
	}
	
	@CacheEvict(value="passwordConfirm", allEntries=true)
	public void refreshConfirm(){
		
	}
}
