package org.sarath.jwt.demo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AppUser {
	private int id;
	private String username;
	private String name;
	private String password;
	private List<String> roles;

	public AppUser(int id, String username, String name, String password, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
