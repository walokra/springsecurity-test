package com.ruleoftech.springsectest.service;

import com.ruleoftech.springsectest.domain.Users;
import com.ruleoftech.springsectest.repository.UserDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.userdetails.User;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

//@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserDAO userdao;

	public CustomUserDetailsService() {

	}

	// this class is used by spring controller to authenticate and authorize user
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername(" + username + ")");

		Users u = userdao.findByName(username);
		if (u == null) {
			throw new UsernameNotFoundException("user name not found");
		}

		return buildUserFromUserEntity(u);

	}

	private User buildUserFromUserEntity(Users userEntity) {
		logger.info("buildUserFromUserEntity("+ userEntity + ")");

		// convert model user to spring security user
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		GrantedAuthority[] authorities = new GrantedAuthorityImpl[2];

		if (userEntity.getUsername().equals("admin")) {
			authorities[0] = new GrantedAuthorityImpl("SEC_ADMIN");
			authorities[1] = new GrantedAuthorityImpl("SEC_USER");
		} else if (userEntity.getUsername().equals("user")) {
			authorities[0] = new GrantedAuthorityImpl("SEC_USER");
			authorities[1] = new GrantedAuthorityImpl("SEC_USER,SEC_USER");
		}

		User springUser = new User(username, password, enabled,	accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		return springUser;
	}
}
