package com.csc.mfs.service;

import com.csc.mfs.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
