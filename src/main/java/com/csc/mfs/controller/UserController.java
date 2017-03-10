package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.repository.UserRepository;
import com.csc.mfs.service.UserService;
import com.csc.mfs.messages.Message;
import com.csc.mfs.model.User;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/all")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		userService.delete(id);
	}
	
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable int id){
		return userService.getUser(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void updateUser(@RequestBody User user){
		user.setPassword(userRepository.findOne(user.getId()).getPassword());
		userService.updateUser(user);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void addUser(@RequestBody User user){
		userService.saveUser(user);
	}
	
	@RequestMapping(value="/changePass", method=RequestMethod.POST)
	public Message changePassword(@RequestBody int id, @RequestBody String oldPass, @RequestBody String newPass){
		return userService.changePassword(id, oldPass, newPass);
	}
}















