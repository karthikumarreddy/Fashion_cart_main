package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.Product;

/*
 *in this class four methods   
 *addtcart-adding the userId and product to the DB
 *getCartItems-fetching 
 */
public class CartDAO {
	public void addToCart(int userId, int productId) {

		String sql = """
						    INSERT INTO cart_items (user_id, product_id, quantity)
						    VALUES (?, ?, 1)
						    ON CONFLICT (user_id, product_id)
						    DO UPDATE SET quantity = cart_items.quantity + 1
						""";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
						         p.product_id, p.product_name, p.price,p.category
						   FROM cart_items ci
						   JOIN product p ON ci.product_id = p.product_id
						   WHERE ci.user_id = ?
						   ORDER BY p.product_name;
						""";

		List<CartItem> cartList = new ArrayList<>();

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getString("category"),
								rs.getDouble("price"), null, null, null);

				CartItem item = new CartItem();
				item.setProduct(p);
				item.setQuantity(rs.getInt("quantity"));

				cartList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	public void clearCart(int userId) {

		String sql = "DELETE FROM cart_items WHERE user_id = ?";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCartCount(int userId) {

		String sql = "SELECT SUM(quantity) FROM cart_items WHERE user_id = ?";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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

	public void updateQuantity(int userId, int productId, String action) {

		String sql = "";
		if (action.equals("inc")) {
			sql = """
							    UPDATE cart_items
							    SET quantity = quantity + 1
							    WHERE user_id = ? AND product_id = ?
							""";
		} else if (action.equals("dec")) {
			sql = """
							    UPDATE cart_items
							    SET quantity = quantity - 1
							    WHERE user_id = ? AND product_id = ? AND quantity > 0
							""";
		} else if (action.equals("remove")) {
			sql = """
							    DELETE FROM cart_items
							    WHERE user_id = ? AND product_id = ?
							""";
		}
		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ps.setInt(2, productId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
