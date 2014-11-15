package com.sjsu.market.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sjsu.market.beans.PaymentInfo;
import com.sjsu.market.beans.Product;

public class MarketDao {
	public List<Product> getProductList(DB db, String collectionName) {
		DBCollection productCollection = db.getCollection(collectionName);
		
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("product_count", new BasicDBObject("$gt", 0));
		
		DBCursor productCursor = productCollection.find(gtQuery);
		DBObject productDoc = null;
		Product product = null;
		List<Product> productList = new ArrayList<Product>();
		while (productCursor.hasNext()) {
			productDoc = productCursor.next();
			try {
				product = new Product(productDoc.get("_id").toString(), productDoc.get("product_name").toString(), Integer.parseInt(productDoc.get("product_count").toString()),
						Float.parseFloat(productDoc.get("product_price").toString()), productDoc.get("description").toString());
				productList.add(product);
			} catch (Exception e) {
				System.out.println("error in ");
				System.out.println("id: " + productDoc.get("_id") + " name: " + productDoc.get("product_name") + " count: " + productDoc.get("product_count") + " price: "
						+ productDoc.get("product_price"));
			}
		}
		productCursor.close();
		return productList;
	}

	public boolean addToCart(DB db, int userId, int quantity, String itemId, float itemPrice) {
		DBCollection cartCollection = db.getCollection("carts");

		// search for cart if present
		BasicDBObject searchQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("isPurchased", "false"));
		obj.add(new BasicDBObject("userId", userId));
		searchQuery.put("$and", obj);

		System.out.println(searchQuery.toString());

		DBCursor cursor = cartCollection.find(searchQuery);
		DBObject cartDoc = null;
		if (cursor.hasNext()) {
			cartDoc = cursor.next();
		}

		// if not found insert document
		try {
			if (null == cartDoc) {
				BasicDBObject document = new BasicDBObject();
				document.put("userId", userId);
				document.put("isPurchased", "false");

				BasicDBObject itemdetail = new BasicDBObject();
				itemdetail.put("itemId", itemId);
				itemdetail.put("quantity", quantity);
				itemdetail.put("itemPrice", (itemPrice * quantity));

				List<BasicDBObject> objList = new ArrayList<BasicDBObject>();
				objList.add(itemdetail);
				document.put("items", objList);

				cartCollection.insert(document);
				return true;
			} else {
				BasicDBObject itemdetail = new BasicDBObject();
				itemdetail.put("itemId", itemId);
				itemdetail.put("quantity", quantity);
				itemdetail.put("itemPrice", (itemPrice * quantity));

				BasicDBObject newItem = new BasicDBObject();
				newItem.put("items", itemdetail);

				BasicDBObject pushQuery = new BasicDBObject();

				pushQuery.put("$push", newItem);
				// cartDoc.put("items", newItem);
				cartCollection.update(cartDoc, pushQuery);
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error happened!!");
		}
		return false;
	}

	
	public List<Product> getHistoryItemsList(DB db, int userId) {
		
		DBCollection cartCollection = db.getCollection("carts");
		List<Product> productList = null;

		// search for cart if present
		BasicDBObject searchQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("isPurchased", "true"));
		obj.add(new BasicDBObject("userId", userId));
		searchQuery.put("$and", obj);

		DBCursor cursor = cartCollection.find(searchQuery);
		DBObject cartDoc = null;
		while (cursor.hasNext()) {
			cartDoc = cursor.next();
		

		// if item isn't find
		if (null != cartDoc) {
			productList = new ArrayList<Product>();
			Product product = null;

			BasicDBObject findProduct = new BasicDBObject();
			DBCursor productCursor = null;
			DBCollection productCollection = db.getCollection("featured");

			BasicDBList dbProductList = (BasicDBList) cartDoc.get("items");
			DBObject productDoc = null;
			for (Object object : dbProductList) {
				BasicDBObject dbObject = (BasicDBObject) object;
				ObjectId productId = new ObjectId(dbObject.get("itemId").toString());
				findProduct.put("_id", productId);
				productCursor = productCollection.find(findProduct);
				if (productCursor.hasNext()) {
					productDoc = productCursor.next();
					try {
						product = new Product(productDoc.get("_id").toString(), productDoc.get("product_name").toString(), Integer.parseInt(dbObject.get("quantity").toString()),
								Float.parseFloat(dbObject.get("itemPrice").toString()), productDoc.get("description").toString());
						productList.add(product);
					} catch (Exception e) {
						System.out.println("error in ");
						System.out.println("id: " + productDoc.get("_id") + " name: " + productDoc.get("product_name") + " count: " + productDoc.get("product_count") + " price: "
								+ productDoc.get("product_price"));
					}
				}
			}
		}
		}
		return productList;
	}

	
	
	
	public List<Product> cartItemsList(DB db, int userId) {
		DBCollection cartCollection = db.getCollection("carts");
		List<Product> productList = null;

		// search for cart if present
		BasicDBObject searchQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("isPurchased", "false"));
		obj.add(new BasicDBObject("userId", userId));
		searchQuery.put("$and", obj);

		DBCursor cursor = cartCollection.find(searchQuery);
		DBObject cartDoc = null;
		if (cursor.hasNext()) {
			cartDoc = cursor.next();
		}

		// if item isn't find
		if (null != cartDoc) {
			productList = new ArrayList<Product>();
			Product product = null;

			BasicDBObject findProduct = new BasicDBObject();
			DBCursor productCursor = null;
			DBCollection productCollection = db.getCollection("featured");

			BasicDBList dbProductList = (BasicDBList) cartDoc.get("items");
			DBObject productDoc = null;
			for (Object object : dbProductList) {
				BasicDBObject dbObject = (BasicDBObject) object;
				ObjectId productId = new ObjectId(dbObject.get("itemId").toString());
				findProduct.put("_id", productId);
				productCursor = productCollection.find(findProduct);
				if (productCursor.hasNext()) {
					productDoc = productCursor.next();
					try {
						product = new Product(productDoc.get("_id").toString(), productDoc.get("product_name").toString(), Integer.parseInt(dbObject.get("quantity").toString()),
								Float.parseFloat(dbObject.get("itemPrice").toString()), productDoc.get("description").toString());
						productList.add(product);
					} catch (Exception e) {
						System.out.println("error in ");
						System.out.println("id: " + productDoc.get("_id") + " name: " + productDoc.get("product_name") + " count: " + productDoc.get("product_count") + " price: "
								+ productDoc.get("product_price"));
					}
				}
			}
		}
		return productList;
	}

	public void placeOrder(DB db, PaymentInfo paymentInfo) {
		updateProductCatlog(db, paymentInfo.getUserId());
		DBCollection cartCollection = db.getCollection("carts");
		BasicDBObject searchQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("isPurchased", "false"));
		obj.add(new BasicDBObject("userId", paymentInfo.getUserId()));
		
		BasicDBObject newValue = new BasicDBObject();
		newValue.put("isPurchased", "true");
		cartCollection.update(searchQuery, new BasicDBObject("$set",newValue));

	}

public void updateProductCatlog(DB db, int userId) {
		
		System.out.println(" update product count ");

		DBCollection cartCollection = db.getCollection("carts");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isPurchased", "false");
		searchQuery.put("userId", userId);

		DBCursor cartCursor = cartCollection.find(searchQuery);
		DBObject cartDoc = null;

		System.out.println(" cartCursor " + cartCursor.count());

		while (cartCursor.hasNext()) {
			cartDoc = cartCursor.next();
			try {
				BasicDBList itemsIdList = (BasicDBList) cartDoc.get("items");

				for (Object it : itemsIdList) {

					// ///////////////////////////////

					DBObject cartItem = (DBObject) it;

					DBCollection productCollection = db.getCollection("featured");

					BasicDBObject searchProductQuery = new BasicDBObject();
					String cartItem_id = cartItem.get("itemId").toString();
					searchProductQuery.put("_id", new ObjectId(cartItem_id));

					BasicDBObject updateQuantityQuery = new BasicDBObject();
					updateQuantityQuery.put(
							"$inc",
							new BasicDBObject("product_count",
										-(Integer.parseInt(
														cartItem.get("quantity").toString()))));

					productCollection.update(searchProductQuery, updateQuantityQuery);

				}

				// ////////////////////////////////////

			} catch (Exception e) {
				System.out.println("error in " + cartCursor.curr().toString());
			}
		}
		cartCursor.close();
	}

	public String getCartId(DB db, PaymentInfo paymentInfo) {
		DBCollection cartCollection = db.getCollection("carts");

		// search for cart if present
		BasicDBObject searchQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("isPurchased", "false"));
		obj.add(new BasicDBObject("userId", paymentInfo.getUserId()));
		searchQuery.put("$and", obj);

		DBCursor cursor = cartCollection.find(searchQuery);
		DBObject cartDoc = null;
		if (cursor.hasNext()) {
			cartDoc = cursor.next();
		}
		return cartDoc.get("_id").toString();
	}
}
