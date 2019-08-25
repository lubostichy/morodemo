package com.lubostichy.morodemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lubostichy.morodemo.entity.User;
import com.lubostichy.morodemo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {

		return userService.getUserId(userId);
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

}
