package com.csc.mfs.repository;

import org.springframework.data.repository.CrudRepository;

import com.csc.mfs.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
	
}
