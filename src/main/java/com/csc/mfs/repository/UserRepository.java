package com.csc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.csc.mfs.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	@Query(value = "SELECT count(*) FROM user", nativeQuery = true)
	long countUser();

}
