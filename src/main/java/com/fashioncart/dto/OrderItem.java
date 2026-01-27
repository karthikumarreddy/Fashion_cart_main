package com.fashioncart.dto;

public class OrderItem {

    private int orderItemId;  
    private int orderId;      
    private int productId;    
    private double price;     
    private int quantity;     

   
    public OrderItem() {
    }

    public OrderItem(int orderId, int productId, double price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem(int orderItemId, int orderId, int productId, double price, int quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Optional: for debugging
    @Override
    public String toString() {
        return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId 
               + ", productId=" + productId + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
