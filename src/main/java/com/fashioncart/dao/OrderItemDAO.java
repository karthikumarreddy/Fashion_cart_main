package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.Product;

public class OrderItemDAO {

	public void saveOrderItem(int orderId, Product product, int quantity) {

		String sql = "INSERT INTO order_item(order_id,product_id, purchase_price, quantity) VALUES (?, ?, ?, ?)";

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
			try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

				ps.setInt(1, orderId);
				ps.setInt(2, Integer.parseInt(product.getId()));
				ps.setDouble(3, product.getPrice());
				ps.setInt(4, quantity);

				ps.executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
