package com.lubostichy.morodemo.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lubostichy.morodemo.service.UserService;

@RestController
@RequestMapping("/secured")
public class SecuredRestController {

	private final UserService userService;

	public SecuredRestController(final UserService userService) {
		this.userService = userService;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable final int userId) {
		userService.deleteById(userId);
		return "User with id " + userId + " has been deleted";
	}

}
