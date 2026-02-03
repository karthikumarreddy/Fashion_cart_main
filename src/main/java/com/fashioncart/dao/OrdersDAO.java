package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.Orders;

public class OrdersDAO {
	private static final Logger logger = LogManager.getLogger(OrdersDAO.class);

	public int saveOrders(Orders orders) {

		int orderId = 0;

		String sql = "  INSERT INTO orders (total_amount, order_date, payment_mode, status)\r\n"
						+ "				    VALUES (?, ?, ?, ?)\r\n" + "				    RETURNING order_id";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDouble(1, orders.getTotalAmount());
			ps.setTimestamp(2, orders.getOrderDate());
			ps.setString(3, orders.getPaymentMode());
			ps.setString(4, orders.getStats());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				orderId = rs.getInt("order_id");
			}

		} catch (Exception e) {
			logger.error("error in OrdersDAO saveOrders() : " + e.getMessage());
		}
		return orderId;
	}
}
