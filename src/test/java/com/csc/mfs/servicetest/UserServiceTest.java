package com.csc.mfs.servicetest;

import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.csc.mfs.model.User;
import com.csc.mfs.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	private final String email="test@gmail.com";
	@Autowired
	private UserService userService;
	
	
	
	@Test
	public void create(){
		User user = new User();
		user.setActive(1);
		user.setEmail("test@gmail.com");
		user.setLastName("Test");
		user.setName("User Test");
		user.setPassword("123123");
		userService.saveUser(user);
		assertNotNull(userService.findUserByEmail(email));
	}
	
	@Test
	public void update(){
		User user = userService.findUserByEmail(email);
		user.setLastName("update");
		user.setActive(1);
		userService.updateUser(user);
		assertEquals("update", userService.findUserByEmail(email).getLastName());
	}
	
	@Test
	public void read(){
		User user = userService.findUserByEmail(email);
		assertNotNull(user);
	}
	
	@Test
	public void delete(){
		User user = userService.findUserByEmail(email);
		userService.delete(user.getId());
		assertEquals(0, (int)userService.findUserByEmail(email).getActive());
	}
	
}
