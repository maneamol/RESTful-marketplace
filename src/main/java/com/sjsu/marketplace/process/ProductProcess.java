package com.sjsu.marketplace.process;

import com.mongodb.DB;
import com.sjsu.market.beans.Product;
import com.sjsu.market.dao.DatabaseConnection;
import com.sjsu.market.dao.ProductDao;

public class ProductProcess {
	public boolean addProduct(Product product) {
		DB db = DatabaseConnection.mongoConnection();
		ProductDao productDao = new ProductDao();
		boolean isAdded = productDao.addProduct(db, product);
		return isAdded;
	}
}
