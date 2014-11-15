package com.sjsu.market.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String creditCard;
	private String type;
	private String email;
	private int userId;
	private String loginTime;

	public User() {
	}

	public User(int userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public User(int userId, String firstName, String lastName, String loginTime) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginTime = loginTime;
	}

	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public User(String userName, String password, String firstName, String lastName, String creditCard, String type) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditCard = creditCard;
		if (type == null) {
			this.type = "cust";
		} else {
			this.type = type;
		}
	}

	public User(String userName, String password, String firstName, String lastName, String type) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
