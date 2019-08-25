package com.lubostichy.morodemo.dao;

import java.util.List;

import com.lubostichy.morodemo.entity.User;

public interface UserDAO {

	public List<User> findAll();
	
	public User getUserById(int id);
}
