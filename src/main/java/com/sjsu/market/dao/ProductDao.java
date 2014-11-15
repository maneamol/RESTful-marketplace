package com.sjsu.market.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.sjsu.market.beans.Product;

public class ProductDao {
	public boolean addProduct(DB db, Product product) {
		// System.out.println("product add" + productName + " " + productDesc +
		// " " +productPrice + " " + productCategory);
		/*
		 * "product_name": "dell xps", "product_count": 500, "product_price":
		 * 1500.45, "description": "Laptop"
		 */
		try {
			DBCollection productColletion = db.getCollection("featured");
			BasicDBObject document = new BasicDBObject();
			document.put("product_name", product.getName());
			document.put("product_count", product.getCount());
			document.put("product_price", product.getPrice());
			document.put("description", product.getDescription());
			document.put("category", product.getCategory());
			productColletion.insert(document);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}
}
