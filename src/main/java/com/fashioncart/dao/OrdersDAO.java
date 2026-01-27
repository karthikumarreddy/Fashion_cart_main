package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fashioncart.dto.Orders;

public class OrdersDAO {
	
	private DataSource getDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
    }

	public int saveOrders(Orders orders) {

		int orderId = 0;

		String sql = """
				    INSERT INTO orders
				    (total_amount, order_date, payment_mode, status)
				    VALUES (?, ?, ?, ?)
				    RETURNING order_id
				""";
		
			try (Connection conn = getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setDouble(1, orders.getTotalAmount());
				ps.setTimestamp(2, orders.getOrderDate());
				ps.setString(3, orders.getPaymentMode());
				ps.setString(4, orders.getStats());

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					orderId = rs.getInt("order_id");
				}

			}catch (Exception e) {
				e.printStackTrace();
			}
			return orderId;
	}
}
