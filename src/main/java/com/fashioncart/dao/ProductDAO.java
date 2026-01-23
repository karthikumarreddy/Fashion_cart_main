package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Product;

public class ProductDAO {
	
	private DataSource getDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
    }
	public List<Product> getAllProductsList() {
		String sql = "select * from product";
		List<Product> allProducts = new ArrayList<>();

			try (Connection conn = getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Product p = new Product(rs.getString("product_id"), rs.getString("Product_name"),
							rs.getString("category"), rs.getDouble("price"), rs.getString("image_path"),
							rs.getString("availability"));
					allProducts.add(p);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return allProducts;
	} 
		
	

	public Product getProductById(String id) throws Exception {

			String sql = "select * from product where product_id=" + id;
			try (Connection conn = getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String id1 = rs.getString("product_id");
					String name = rs.getString("Product_name");
					String category = rs.getString("category");
					double price = rs.getDouble("price");
					String image = rs.getString("image_path");
					String available = rs.getString("availability");
					return new Product(id1, name, category, price, image, available);
				}
			}catch (NamingException | SQLException e) {
				e.printStackTrace();
			}
			return null;
		} 
	

	public List<Product> getProductsByCategoryList(String category) {

		List<Product> products = new ArrayList<>();
			String sql = "select * from product where category=?";
			try (Connection conn = getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setString(1, category);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Product p = new Product(rs.getString("product_id"), rs.getString("Product_name"),
							rs.getString("category"), rs.getDouble("price"), rs.getString("image_path"),
							rs.getString("availability"));
					products.add(p);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return products;
	}
}
