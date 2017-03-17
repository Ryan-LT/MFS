package com.csc.mfs.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.repository.UserRepository;
import com.csc.mfs.service.UserService;

import com.csc.mfs.messages.Message;
import com.csc.mfs.model.User;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/all/{page}/{pageSize}")
	public ResponseEntity<Page<User>> getAll(@PathVariable int page, @PathVariable int pageSize){
		return ResponseEntity.ok().body(userService.getAll(page, pageSize));
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		userService.delete(id);
	}
	
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable int id){
		if(null!=userService.getUser(id)){
			return ResponseEntity.ok().body(userService.getUser(id));
		} else{
			return ResponseEntity.ok().body(null);
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void updateUser(@RequestBody User user){
		user.setPassword(userRepository.findOne(user.getId()).getPassword());
		userService.updateUser(user);
	}
	
	@RequestMapping(value="/countUser/")
	public ResponseEntity<Long> countFileByUser(){
		return ResponseEntity.ok().body(userService.countUser());
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<Message> addUser(@RequestBody User user){
		return ResponseEntity.ok().body(userService.saveUser(user));
	
	}
	
	@RequestMapping(value="/changePass", method=RequestMethod.POST)
	public ResponseEntity<Message> changePassword(@RequestBody String jsonStr){
		int index= 0; 
		String oldPass="";
		String newPass="";
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(jsonStr);
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);
			index = ((Long) jsonObject.get("id")).intValue();
            oldPass = (String) jsonObject.get("oldPass");
            newPass = (String) jsonObject.get("newPass");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(userService.changePassword(index, oldPass, newPass));
	}
}















