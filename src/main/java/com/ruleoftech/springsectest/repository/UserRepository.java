package com.ruleoftech.springsectest.repository;

import com.ruleoftech.springsectest.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserDAO {
	@PersistenceContext
	private transient EntityManager entityManager;

	public Users findByName(String username) {
		List<Users> userList = new ArrayList<Users>();

		Query query = entityManager.createQuery("select u from Users u where u.username = :username");
		query.setParameter("username", username);
		userList = query.getResultList();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
}
