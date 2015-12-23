package com.ruleoftech.springsectest.repository;

import com.ruleoftech.springsectest.domain.Users;

public interface UserDAO {
	Users findByName(String login);
}
