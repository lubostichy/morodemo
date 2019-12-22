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
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserId(final int id) {
		final User user = userDAO.getUserById(id);
		if (user == null) {
			throw new RuntimeException(String.format("User id not found %d", id));
		}
		return user;
	}

	@Override
	@Transactional
	public void save(final User user) {
		userDAO.save(user);

	}

	@Override
	@Transactional
	public void deleteById(final int id) {
		final User user = getUserId(id);
		if (user == null) {
			throw new RuntimeException(String.format("User id not found %d", id));
		}
		userDAO.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByUsername(final String username) {
		return userDAO.getUserByUsername(username);
	}

}
