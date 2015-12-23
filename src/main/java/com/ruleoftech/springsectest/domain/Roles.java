package com.ruleoftech.springsectest.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLES")
public class Roles implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLES_ID")
	private Integer id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "ROLE")
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Roles{" +
				"id=" + id +
				", username='" + username + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
