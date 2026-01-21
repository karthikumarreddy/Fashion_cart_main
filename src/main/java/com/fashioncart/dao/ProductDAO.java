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

import com.google.gson.Gson;

import util.Product;

public class ProductDAO {

	

	public String getAllProducts() {
		Context ctx;
		List<Product> allProducts = new ArrayList<Product>();
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
			Connection conn = ds.getConnection();
			String sql = "select * from product";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("product_id");
				String name = rs.getString("Product_name");
				String category = rs.getString("category");
				double price = rs.getDouble("price");
				String image = rs.getString("image_path");
				String available = rs.getString("availability");
				Product p = new Product(id, name, category, price, image, available);
				allProducts.add(p);
			}

		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}

		Gson gson = new Gson();
		String json = gson.toJson(allProducts);
		return json;
	}

	public String getProductsByCategory(String category) {
		Context ctx;
		List<Product> allProducts = new ArrayList<Product>();
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
			Connection conn = ds.getConnection();
			String sql = "select * from product where category=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("product_id");
				String name = rs.getString("Product_name");
				String category1 = rs.getString("category");
				double price = rs.getDouble("price");
				String image = rs.getString("image_path");
				String available = rs.getString("availability");
				Product p = new Product(id, name, category1, price, image, available);
				allProducts.add(p);
			}

		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}

		Gson gson = new Gson();
		String json = gson.toJson(allProducts);
		return json;
	}

	public Product getProductById(String id) {
		Context ctx;

		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
			Connection conn = ds.getConnection();
			String sql = "select * from product where product_id=" + id;
			PreparedStatement ps = conn.prepareStatement(sql);

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

		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}
		return null;

	}
}
