package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.Product;

public class ProductDAO {

	/*
	 * getting the product from product table in db and add each product in list and return list of product to ListProductsCommand =======
	 * 
	 * /* getting the product from product table in db and add each product in list and return list of product to ListProductsCommand
	 */
	public List<Product> getAllProductsList() {
		String sql = "select * from product";
		List<Product> allProducts = new ArrayList<>();

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"), rs.getString("Product_name"), rs.getString("category"),
								rs.getDouble("price"), rs.getString("image_path"), rs.getString("availability"));
				allProducts.add(p);
			}
			return allProducts;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> getProductsByCategoryList(String category) {

		List<Product> products = new ArrayList<>();
		String sql = "select * from product where category=?";
		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"), rs.getString("Product_name"), rs.getString("category"),
								rs.getDouble("price"), rs.getString("image_path"), rs.getString("availability"));
				products.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public Product getProductsById(int productId) {

		String sql = "select * from product where product_id=?";
		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"), rs.getString("Product_name"), rs.getString("category"),
								rs.getDouble("price"), rs.getString("image_path"), rs.getString("availability"));
				return p;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
