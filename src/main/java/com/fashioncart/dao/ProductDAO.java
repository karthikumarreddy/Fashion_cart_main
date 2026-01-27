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

import com.fashioncart.dto.Product;

public class ProductDAO {
	
	//return the DataSource by getting from the context.xml to get the connection db
	private DataSource getDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
    }
	
	
	/*getting the product from product table in db
	 * and add each product in list 
	 * and return list of product to ListProductsCommand
	 */
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
