package util;

import java.sql.Timestamp;

public class Orders {
	private String orderId;
	private double totalAmount;
	private Timestamp orderDate;
	private String paymentMode;
	private String status;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getStats() {
		return status;
	}
	public void setStats(String status) {
		this.status = status;
	}
	
	public Orders(double totalAmount, Timestamp orderDate, String paymentMode, String status) {
		super();
		this.totalAmount = totalAmount;
		this.setOrderDate(orderDate);
		this.paymentMode = paymentMode;
		this.status = status;
	}
	
	public Orders() {
	
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	
	
	

}
