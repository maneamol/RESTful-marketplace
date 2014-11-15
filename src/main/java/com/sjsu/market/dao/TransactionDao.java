package com.sjsu.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sjsu.market.beans.PaymentInfo;

public class TransactionDao {
	private static final String INSERT_CC_DETAIL = "INSERT INTO transactionDetails(userid, cartid, cartnumber, csv) values (?, ?, ?, ?)";
	public int updateTransaction(Connection connection, PaymentInfo paymentInfo, String cartId) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int transactionId = -1;
		statement = connection.prepareStatement(INSERT_CC_DETAIL, Statement.RETURN_GENERATED_KEYS);

		statement.setInt(1, paymentInfo.getUserId());
		statement.setString(2, cartId);
		statement.setLong(3, paymentInfo.getCreditCardNumber());
		statement.setInt(4, paymentInfo.getCsvNumber());
		statement.executeUpdate();
		resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
			transactionId = resultSet.getInt(1);
		}
		return transactionId;
	}
}
