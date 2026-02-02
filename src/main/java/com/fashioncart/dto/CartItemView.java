package com.fashioncart.dto;

public class CartItemView {

	private String id;
	private String imagePath;
	private String name;
	private String category;
	private double price;
	private int quantity;
	private double subTotal;

	public CartItemView(String id, String name, String category, double price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = 1; // default
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	// getters & setters
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.subTotal = this.price * quantity;
	}
}
