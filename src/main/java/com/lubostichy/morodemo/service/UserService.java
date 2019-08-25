package com.lubostichy.morodemo.service;

import java.util.List;

import com.lubostichy.morodemo.entity.User;

public interface UserService {

	public List<User> findAll();
	
	public User getUserId(int id);
}
