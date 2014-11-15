package com.sjsu.marketplace.process;

import java.sql.Connection;
import java.sql.SQLException;

import com.sjsu.market.beans.User;
import com.sjsu.market.dao.DatabaseConnection;
import com.sjsu.market.dao.UserDao;

public class UserProcess {
	public int signUp(User user) {
		Connection connection = null;

		UserDao userDao = new UserDao();
		int userId = -1;
		try {
			connection = DatabaseConnection.getConnection();
			if (connection != null) {
				userId = userDao.userSignup(connection, user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userId;
	}

	public User login(String email, String password) {
		Connection connection = null;

		UserDao userDao = new UserDao();
		User user = null;
		try {
			connection = DatabaseConnection.getConnection();
			if (connection != null) {
				user = userDao.userLogin(connection, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}
	
	public User adminLogin(String email, String password, String type) {
		Connection connection = null;

		UserDao userDao = new UserDao();
		User user = null;
		try {
			connection = DatabaseConnection.getConnection();
			if (connection != null) {
				user = userDao.adminLogin(connection, email, password, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}
}
