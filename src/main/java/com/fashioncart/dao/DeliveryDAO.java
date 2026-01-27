package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fashioncart.dto.Delivery;

public class DeliveryDAO {

	private DataSource getDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
    }
	public void saveDeliveryDetails(Delivery delivery) {
		
		String sql = "INSERT INTO delivery_address(order_id, customer_name, address_line1,address_line2,city,pincode,mobile)VALUES (?, ?, ?, ?, ?, ?, ?)";
		
			try (Connection c = getDataSource().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
				ps.setInt(1,delivery.getOrder_id());
				ps.setString(2, delivery.getCustomer_name());
				ps.setString(3, delivery.getAddress_line1());
				ps.setString(4, delivery.getAddress_line2());
				ps.setString(5, delivery.getCity());
				ps.setString(6, delivery.getPincode());
				ps.setString(7, delivery.getMoile());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
