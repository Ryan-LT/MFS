package com.csc.mfs.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.service.UserService;

import com.csc.mfs.messages.Message;
import com.csc.mfs.model.User;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/all")
	public Page<User> getAll(Pageable pageable){
		return userService.getAll(pageable);
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		userService.delete(id);
	}
	
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable int id){
		return userService.getUser(id);
	}
	
	@RequestMapping(value="/getByEmail/{mail}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByEmail(@PathVariable String mail){
		System.out.println(mail);
		return userService.findUserByEmail(mail);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateUser(@RequestBody User user){
		userService.updateUser(user);
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Message addUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@RequestMapping(value="/changePass", method=RequestMethod.POST)
	public Message changePassword(@RequestBody String jsonStr){
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
		return userService.changePassword(index, oldPass, newPass);
	}
	
	@RequestMapping(value="/get/rank/{idRank}", method=RequestMethod.GET)
	public long countUserByRankr(@PathVariable("idRank") Integer idRank, Pageable pageable){
		return userService.findByRankId(idRank, pageable).getTotalElements();
	}
	
}















