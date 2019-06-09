package com.twinkle.bookstore.bean;

/**
 * @Name com.twinkle.bookstore.bean/User.java
 * @Description: 用户表
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class User {
	private String id;
	private String username;
	private String password;
	private String email;

	public User() {
		super();
	}

	public User(String id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}
