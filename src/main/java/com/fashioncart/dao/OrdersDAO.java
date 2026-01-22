package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Orders;

public class OrdersDAO {

	public int saveOrders(Orders orders) {
		Context ctx;
	    int orderId = 0;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
			Connection conn = ds.getConnection();
			String sql = "insert into orders (total_amount,order_date,payment_mode,status) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, orders.getTotalAmount());
			ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setString(3,orders.getPaymentMode());
			ps.setString(4, "ORDERED");
			
			ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                orderId = rs.getInt("order_id");
	            }

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return orderId;
	}

}
