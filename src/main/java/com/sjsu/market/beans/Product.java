package com.sjsu.market.beans;

public class Product {
	private String id;
	private String name;
	private int count;
	private float price;
	private String description;
	private String category;

	public Product(String name, int count, float price, String description, String category) {
		super();
		this.name = name;
		this.count = count;
		this.price = price;
		this.description = description;
		this.category = category;
	}

	public Product(String id, String name, int count, float price, String description) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
