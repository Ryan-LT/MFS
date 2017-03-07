package com.csc.mfs.repository;

import org.springframework.data.repository.CrudRepository;

import com.csc.mfs.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByName(String name);
	
}
