package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.Product;

public class ProductDAO {

	/**
	 * Fetch products. If category is null or "ALL", fetch all products.
	 */
	// public List<Product> getProductsList(String category) {
	//
	// List<Product> products = new ArrayList<>();
	//
	// boolean filterByCategory = (category != null && !category.equalsIgnoreCase("ALL"));
	//
	// String sql = filterByCategory ? "SELECT * FROM product WHERE category = ?" : "SELECT * FROM product";
	//
	// try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	//
	// if (filterByCategory) {
	// ps.setString(1, category);
	// }
	//
	// ResultSet rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// Product p = new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getString("category"),
	// rs.getDouble("price"), rs.getString("image_path"), rs.getString("availability"),
	// rs.getString("description"));
	// products.add(p);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return products; // ✅ never null
	// }
	//
	// /**
	// * Fetch single product by ID
	// */

	public List<Product> getProductsList(String category, int limit, int offset) {

		List<Product> products = new ArrayList<>();

		boolean filterByCategory = (category != null && !category.equalsIgnoreCase("ALL"));

		String sql = filterByCategory ? "SELECT * FROM product WHERE category = ? LIMIT ? OFFSET ?"
						: "SELECT * FROM product LIMIT ? OFFSET ?";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			int index = 1;

			if (filterByCategory) {
				ps.setString(index++, category);
			}

			ps.setInt(index++, limit);
			ps.setInt(index, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getString("category"),
								rs.getDouble("price"), rs.getString("image_path"), rs.getString("availability"),
								rs.getString("description"));
				products.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return products; // never null
	}

	public Product getProductById(int productId) {

		String sql = "SELECT * FROM product WHERE product_id = ?";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getString("category"),
								rs.getDouble("price"), rs.getString("image_path"), rs.getString("availability"),
								rs.getString("description"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getTotalProductCount(String category) {

		boolean filterByCategory = (category != null && !category.equalsIgnoreCase("ALL"));

		String sql = filterByCategory ? "SELECT COUNT(*) FROM product WHERE category = ?" : "SELECT COUNT(*) FROM product";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			if (filterByCategory) {
				ps.setString(1, category);
			}

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
