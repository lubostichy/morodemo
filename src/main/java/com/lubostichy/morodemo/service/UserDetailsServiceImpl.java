package com.lubostichy.morodemo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lubostichy.morodemo.dao.UserDAO;
import com.lubostichy.morodemo.entity.User;
import com.lubostichy.morodemo.entity.UserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDAO userDAO;

	public UserDetailsServiceImpl(@Qualifier("userDAOImpl") final UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with username " + username + " was not found");
		}
		return new UserDetails(user);
	}

}
