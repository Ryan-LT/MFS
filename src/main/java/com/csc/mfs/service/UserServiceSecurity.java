package com.csc.mfs.service;

import com.csc.mfs.model.User;

public interface UserServiceSecurity {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
