package com.csc.mfs.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.annotations.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csc.mfs.model.Role;
import com.csc.mfs.model.User;
import com.csc.mfs.messages.Message;
import com.csc.mfs.repository.DownloadRepository;
import com.csc.mfs.repository.RoleRepository;
import com.csc.mfs.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FileService fileService;
	@Autowired
	DownloadRepository downloadRepsitory;
	
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	
	public Page<User> getAll(int pageNumber, int pageSize){
		  PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		            //new PageRequest(pageNumber - 1, pageSize,  Sort.Direction.DESC, "startTime");
		return (Page<User>) userRepository.findAll(pageRequest);
	}
	
	public long countRecord(){
		return userRepository.count();
	}
	
	public void delete(int idUser){
		User user= userRepository.findOne(idUser);
		if(null!=user){
			downloadRepsitory.removeByIdUser(user);	
		}
		fileService.updateUser(idUser);
		userRepository.delete(idUser);
	}
	
	public void lock(int id){
		User user =userRepository.findOne(id);
		if(null!=user){
			user.setActive(0);
			userRepository.flush();
		}
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
	
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("MEMBER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
