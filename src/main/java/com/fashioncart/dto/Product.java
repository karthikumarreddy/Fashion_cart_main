package com.fashioncart.dto;

public class Product {
	private String id;
	private String name;
	private String category;
	private double price;
	private String imagePath;
	private String availability;

	
	public Product() {
	};

	public Product(String id, String name, String category, double price, String imagePath, String availability) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.imagePath = imagePath;
		this.availability = availability;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String isAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

}
