package com.csc.mfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csc.mfs.model.User;
import com.csc.mfs.messages.Message;
import com.csc.mfs.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public void delete(int id){
		userRepository.delete(id);
	}
	
	public User getUser(int id){
		return userRepository.findOne(id);
	}
	
	public void updateUser(User user){
		user.setPassword(userRepository.findOne(user.getId()).getPassword());
		userRepository.saveAndFlush(user);
	}
	
	public Message changePassword(int id, String oldPass, String newPass){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		User user = userRepository.findOne(id);
		if(user.getPassword().equals(bCryptPasswordEncoder.encode(oldPass))){
			user.setPassword(bCryptPasswordEncoder.encode(newPass));
			userRepository.flush();
			return (new Message(true, "Seccussful"));
		} else {
			return (new Message(false, "Old password is wrong!"));
		}
	}
}




