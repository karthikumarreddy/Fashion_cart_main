package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.CartItem;
import util.Product;

public class CartDAO {
	private DataSource getDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/fashion_db");
    }
	
	public void addToCart(int userId, int productId) {

	    String sql = """
	        INSERT INTO cart_items (user_id, product_id, quantity)
	        VALUES (?, ?, 1)
	        ON CONFLICT (user_id, product_id)
	        DO UPDATE SET quantity = cart_items.quantity + 1
	    """;

	    try (
	        Connection conn = getDataSource().getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {
	        ps.setInt(1, userId);
	        ps.setInt(2, productId);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public List<CartItem> getCartItems(int userId) {

	    String sql = """
	        SELECT ci.quantity,
	               p.product_id, p.product_name, p.price, p.image_path
	        FROM cart_items ci
	        JOIN product p ON ci.product_id = p.product_id
	        WHERE ci.user_id = ?
	    """;

	    List<CartItem> list = new ArrayList<>();

	    try (
	        Connection conn = getDataSource().getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Product p = new Product(
	                rs.getString("product_id"),
	                rs.getString("product_name"),
	                null,
	                rs.getDouble("price"),
	                rs.getString("image_path"),
	                null
	            );

	            CartItem item = new CartItem();
	            item.setProduct(p);
	            item.setQuantity(rs.getInt("quantity"));

	            list.add(item);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public void clearCart(int userId) {

	    String sql = "DELETE FROM cart_items WHERE user_id = ?";

	    try (
	        Connection conn = getDataSource().getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {
	        ps.setInt(1, userId);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public int getCartCount(int userId) {

	    String sql = "SELECT COALESCE(SUM(quantity),0) FROM cart_items WHERE user_id = ?";

	    try (
	        Connection conn = getDataSource().getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {
	        ps.setInt(1, userId);
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
