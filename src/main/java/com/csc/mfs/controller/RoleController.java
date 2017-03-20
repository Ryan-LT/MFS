package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.model.Role;
import com.csc.mfs.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleRepository roleReponsitory;
	
	@RequestMapping("/all")
	public List<Role> getAll(){
		return roleReponsitory.findAll();
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insertFile(@RequestBody Role role){
		roleReponsitory.save(role);
	}
	
	@RequestMapping("/updateName/{id}/{name}")
	public void updateName(@PathVariable int id, @PathVariable String name){
		Role role = roleReponsitory.findOne(id);
		role.setRole(name);
		roleReponsitory.flush();
		
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		roleReponsitory.delete(id);
		
	}
	
	

}