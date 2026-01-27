package com.fashioncart.dto;

public class Delivery {
	
	private int order_id;
	private String customer_name;
	private String address_line1;
	private String Address_line2;
	private String city;
	private String pincode;
	private String moile;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return Address_line2;
	}
	public void setAddress_line2(String address_line2) {
		Address_line2 = address_line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMoile() {
		return moile;
	}
	public void setMoile(String moile) {
		this.moile = moile;
	}
	public Delivery( int orderId, String customer_name, String address_line1, String address_line2, String city,
					String pincode, String moile) {
		super();
		this.order_id = orderId;
		this.customer_name = customer_name;
		this.address_line1 = address_line1;
		this.Address_line2 = address_line2;
		this.city = city;
		this.pincode = pincode;
		this.moile = moile;
	}
	
	
	
}
