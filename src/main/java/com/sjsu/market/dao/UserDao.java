package com.sjsu.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sjsu.market.beans.User;

public class UserDao {

	private static final String INSERT_USERS = "INSERT INTO users (username, firstname, lastName, email,pwd,memberType) " + " VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_USER = "SELECT ID FROM users WHERE email= ? AND pwd = ? ";
	private static final String UPDATE_TIMESTAMP= "UPDATE users SET last_logged = NOW() where ID = ?";
	private static final String GET_LOGIN_DETAILS = " SELECT ID,firstname,lastName,DATE_FORMAT(last_logged,'%b %d %Y %h:%i %p') LOGIN_DATE FROM users where ID = ? ";
	private static final String GET_ADMIN_USER = "SELECT ID FROM users WHERE email= ? AND pwd = ? AND memberType = 'admin'";
	
	public int userSignup(Connection connection, User user) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int userId = -1;
		statement = connection.prepareStatement(INSERT_USERS, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, user.getUserName());
		statement.setString(2, user.getFirstName());
		statement.setString(3, user.getLastName());
		statement.setString(4, user.getEmail());
		statement.setString(5, user.getPassword());
		statement.setString(6, user.getType());
		statement.executeUpdate();
		resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
			userId = resultSet.getInt(1);
		}
		return userId;
	}

	public User userLogin(Connection connection, String userName, String password) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int userId = -1;
		User user = null;
		statement = connection.prepareStatement(GET_USER);

		statement.setString(1, userName);
		statement.setString(2, password);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			userId = resultSet.getInt("id");
			if (userId > 0) {
				this.updateTimestamp(connection, userId); 
				user = this.getUserInfo(connection, userId);
			}
		}
		return user;
	}
	
	public User adminLogin(Connection connection, String userName, String password, String type) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int userId = -1;
		User user = null;
		statement = connection.prepareStatement(GET_ADMIN_USER);
		System.out.println(GET_ADMIN_USER);
		statement.setString(1, userName);
		statement.setString(2, password);
		
		System.out.println(userName + " " + password + " " + type);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			userId = resultSet.getInt("id");
			if (userId > 0) {
				this.updateTimestamp(connection, userId); 
				user = this.getUserInfo(connection, userId);
			}
		}
		return user;
	}
	
	private void updateTimestamp(Connection connection, int userId) throws SQLException {
		PreparedStatement statement = null;
		statement = connection.prepareStatement(UPDATE_TIMESTAMP);
		statement.setInt(1, userId);
		statement.executeUpdate();
	}
	
	private User getUserInfo(Connection connection, int userId) throws SQLException {
		User user = null;
		String firstName = null;
		String lastName = null;
		String lastTime = null;
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		statement = connection.prepareStatement(GET_LOGIN_DETAILS);
		statement.setInt(1, userId);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			firstName = resultSet.getString("firstname");
			lastName = resultSet.getString("lastName");
			lastTime = resultSet.getString("LOGIN_DATE");
		}
		if (null != firstName && null != lastName && null != lastName) {
			user = new User(userId, firstName, lastName, lastTime);
		}
		return user;
	}
}
