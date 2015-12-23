package com.ruleoftech.springsectest.service;

import com.ruleoftech.springsectest.domain.Users;
import com.ruleoftech.springsectest.repository.UserDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.*;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * Custom authentication provider for validating user login.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserDAO userdao;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());

		logger.info("username:" + username + "; password=" + password);

		// 1. Use the username to load the data for the user, including authorities and password.
		Users user = userdao.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("user name not found");
		}

		// 2. Check the passwords match
		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("Bad Credentials");
		}

		// 3. Preferably clear the password in the user object before storing in authentication object
		user.setPassword(null);

		// 4. Return an authenticated token, containing user data and authorities
		GrantedAuthority[] authorities = new GrantedAuthorityImpl[2];
		if (user.getUsername().equals("admin")) {
			authorities[0] = new GrantedAuthorityImpl("SEC_ADMIN");
			authorities[1] = new GrantedAuthorityImpl("SEC_USER");
		} else if (user.getUsername().equals("user")) {
			authorities[0] = new GrantedAuthorityImpl("SEC_USER");
			authorities[1] = new GrantedAuthorityImpl("SEC_USER,SEC_USER");
		}
		logger.info("user=" + authentication.getName() + "; authorities=" + authorities[0]);

		return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), authorities);
	}

	public boolean supports(Class aClass) {
		return true;
	}

}
