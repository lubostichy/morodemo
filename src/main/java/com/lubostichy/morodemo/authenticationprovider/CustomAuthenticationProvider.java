package com.lubostichy.morodemo.authenticationprovider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lubostichy.morodemo.entity.User;
import com.lubostichy.morodemo.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private UserService userService;

	@Value("${websecurity.username}")
	private String adminUsername;

	@Value("${websecurity.password}")
	private String adminPassword;

	@Autowired
	public CustomAuthenticationProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = null;

		boolean isSystemAdmin = username.equals(adminUsername.toString());

		if (isSystemAdmin) {
			if (!password.equals(adminPassword.toString())) {
				return null;
			}
		} else {

			user = userService.getUserByUsername(username);
			if (user == null) {
				return null;
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			boolean passwordVerified = passwordEncoder.matches(password, user.getPassword());
			if (!passwordVerified) {
				return null;
			}
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(isSystemAdmin ? "ROLE_ADMIN" : user.getRole()));

		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
