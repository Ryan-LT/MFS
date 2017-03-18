package com.csc.mfs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.csc.mfs.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	Page<User> findByActive(Integer active, Pageable pageable);
	

}
