package com.lubostichy.morodemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lubostichy.morodemo.dao.UserDAO;
import com.lubostichy.morodemo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	
	public UserServiceImpl(@Qualifier("userDAOImpl") UserDAO userDAO) {
		this.userDao = userDAO;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	@Override
	@Transactional
	public User getUserId(int id) {
		return this.userDao.getUserById(id);
	}

	

}
