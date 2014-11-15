package com.sjsu.marketplace.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mongodb.DB;
import com.sjsu.market.beans.PaymentInfo;
import com.sjsu.market.beans.Product;
import com.sjsu.market.dao.DatabaseConnection;
import com.sjsu.market.dao.MarketDao;
import com.sjsu.market.dao.TransactionDao;

public class MarketProcess {
	public List<Product> getProductList() {
		DB db = DatabaseConnection.mongoConnection();
		MarketDao marketDao = new MarketDao();
		List<Product> productList = marketDao.getProductList(db, "featured");
		return productList;
	}

	public boolean addToCart(int userId, int quantity, String itemId, float itemPrice) {
		DB db = DatabaseConnection.mongoConnection();
		MarketDao marketDao = new MarketDao();
		boolean isSucess = marketDao.addToCart(db, userId, quantity, itemId, itemPrice);
		return isSucess;
	}

	public List<Product> cartItemsList(int userId) {
		DB db = DatabaseConnection.mongoConnection();
		MarketDao marketDao = new MarketDao();
		List<Product> productList = marketDao.cartItemsList(db, userId);
		//List<Product> productList = marketDao.getHistoryItemsList(db, userId);
		return productList;
	}

	public List<Product> historyItemsList(int userId) {
		DB db = DatabaseConnection.mongoConnection();
		MarketDao marketDao = new MarketDao();
		//List<Product> productList = marketDao.cartItemsList(db, userId);
		List<Product> productList = marketDao.getHistoryItemsList(db, userId);
		return productList;
	}
	public void placeOrder(PaymentInfo paymentInfo) {
		System.out.println("in place order");
		DB db = DatabaseConnection.mongoConnection();
		MarketDao marketDao = new MarketDao();
		String cartId = marketDao.getCartId(db, paymentInfo);

		Connection connection = DatabaseConnection.getConnection();
		if (null != connection) {
			TransactionDao transactionDao = new TransactionDao();
			try {
				int transactionId = transactionDao.updateTransaction(connection, paymentInfo, cartId);
				if (transactionId > 0 ){
					marketDao.placeOrder(db, paymentInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}