package com.fashioncart.dto;

public class ProductqtyDTO {

	private String id;
	private String name;
	private String category;
	private double price;
	private int quantity;

	public ProductqtyDTO(String id, String name, String category, double price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = 1; // default
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
