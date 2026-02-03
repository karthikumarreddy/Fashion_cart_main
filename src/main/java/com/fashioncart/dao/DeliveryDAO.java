package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.Delivery;

public class DeliveryDAO {
	private static final Logger logger = LogManager.getLogger(DeliveryDAO.class);

	public void saveDeliveryDetails(Delivery delivery) {

		String sql = "INSERT INTO delivery_address(order_id, customer_name, address_line1,address_line2,city,pincode,mobile)VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection c = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, delivery.getOrder_id());
			ps.setString(2, delivery.getCustomer_name());
			ps.setString(3, delivery.getAddress_line1());
			ps.setString(4, delivery.getAddress_line2());
			ps.setString(5, delivery.getCity());
			ps.setString(6, delivery.getPincode());
			ps.setString(7, delivery.getMoile());
			ps.executeUpdate();
		} catch (Exception e) {
			logger.error("error in DeliveryDao saveDeliveryDeails() : " + e.getMessage());
		}
	}
}
