package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.Product;

public class OrderItemDAO {
	private static final Logger logger = LogManager.getLogger(OrderItemDAO.class);

	public void saveOrderItem(int orderId, Product product, int quantity) {

		String sql = "INSERT INTO order_item(order_id,product_id, purchase_price, quantity) VALUES (?, ?, ?, ?)";
		try (Connection c = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			ps.setInt(2, Integer.parseInt(product.getId()));
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, quantity);

			ps.executeUpdate();

		} catch (Exception e) {
			logger.error("error in OrderItemDAO saveOrderitem() : " + e.getMessage());
		}
	}
}
