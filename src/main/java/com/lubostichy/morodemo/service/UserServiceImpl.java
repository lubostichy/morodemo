package com.lubostichy.morodemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lubostichy.morodemo.dao.UserDAO;
import com.lubostichy.morodemo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;

	public UserServiceImpl(@Qualifier("userDAOImpl") final UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional
	public User getUserId(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void save(User user) {
		userDAO.save(user);
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		userDAO.deleteById(id);
		
	}

	@Override
	@Transactional 
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

}
