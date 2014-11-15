package com.sjsu.market.dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DatabaseConnection {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://marketplacedb.cwot4cqrtrxp.us-west-2.rds.amazonaws.com:3306/marketplacedb", "root", "Janataraja-384");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static DB mongoConnection() {
		DB db = null;
		try {
			MongoClientURI clientURI = new MongoClientURI("mongodb://amoljmane:Janataraja-384@ds043200.mongolab.com:43200/marketplace");
			MongoClient mongoClient = new MongoClient(clientURI);
			db = mongoClient.getDB("marketplace");
			db.authenticate("amoljmane", "Janataraja-384".toCharArray());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return db;
	}
}
