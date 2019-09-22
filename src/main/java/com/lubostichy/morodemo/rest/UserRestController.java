package com.lubostichy.morodemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lubostichy.morodemo.entity.User;
import com.lubostichy.morodemo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	private final UserService userService;

	@Autowired
	public UserRestController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = userService.getUserId(userId);
		if (user == null) {
			throw new RuntimeException("User id not found - " + userId);			
		}
		return user;
	}

	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		user.setId(0);
		if (user.getUsername() == null) {
			user.setUsername("x" + user.getName().toLowerCase());
		}
		if (user.getPassword() == null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getUsername());
			user.setPassword(hashedPassword);
		}
		if (user.getRole() == null) {
			user.setRole("ROLE_USER");
		}
		userService.save(user);
		return user;
	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}

}
