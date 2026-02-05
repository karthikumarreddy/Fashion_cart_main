package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.Product;

/*
 *in this class four methods   
 *addtcart-adding the userId and product to the DB
 *getCartItems-fetching 
 */
public class CartDAO {
	private static final Logger logger = LogManager.getLogger(CartDAO.class);

	public void addToCart(int userId, int productId, String path) {

		String sql = """
						    INSERT INTO cart_items (user_id, product_id, quantity,image_path)
						    VALUES (?, ?, 1,?)
						    ON CONFLICT (user_id, product_id)
						    DO UPDATE SET quantity = cart_items.quantity + 1
						""";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, productId);
			ps.setString(3, path);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.error("error in CatDAO addToCart() : " + e.getMessage());
		}
	}

	public List<CartItem> getCartItems(int userId) {

		String sql = """
						   SELECT ci.quantity,ci.image_path,
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
								rs.getDouble("price"), rs.getString("image_path"), null, null);

				CartItem item = new CartItem();
				item.setProduct(p);
				item.setQuantity(rs.getInt("quantity"));

				cartList.add(item);
			}
		} catch (Exception e) {
			logger.error("error in CatDAO getCartItems() : " + e.getMessage());

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
			logger.error("error in CatDAO clearCart() : " + e.getMessage());

		}
		return 0;
	}

	public void updateQuantity(int userId, int productId, int quantity, String action) {

		try (Connection conn = GetDataSource.getDataSource().getConnection()) {

			if ("inc".equals(action)) {

				String sql = """
								    UPDATE cart_items
								    SET quantity = quantity + 1
								    WHERE user_id = ? AND product_id = ?
								""";

				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setInt(1, userId);
					ps.setInt(2, productId);
					ps.executeUpdate();
				}

			} else if ("dec".equals(action)) {

				// Decrease quantity
				String decSql = """
								    UPDATE cart_items
								    SET quantity = quantity - 1
								    WHERE user_id = ? AND product_id = ? AND quantity > 0
								""";

				try (PreparedStatement ps = conn.prepareStatement(decSql)) {
					ps.setInt(1, userId);
					ps.setInt(2, productId);
					ps.executeUpdate();
				}

				// Remove if quantity becomes 0
				String deleteSql = """
								    DELETE FROM cart_items
								    WHERE user_id = ? AND product_id = ? AND quantity <= 0
								""";

				try (PreparedStatement ps = conn.prepareStatement(deleteSql)) {
					ps.setInt(1, userId);
					ps.setInt(2, productId);
					ps.executeUpdate();
				}

			} else if ("remove".equals(action)) {

				String sql = """
								    DELETE FROM cart_items
								    WHERE user_id = ? AND product_id = ?
								""";

				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setInt(1, userId);
					ps.setInt(2, productId);
					ps.executeUpdate();
				}
			} else if ("updateQuantity".equals(action)) {

				String sql = " UPDATE cart_items  SET quantity = ? WHERE user_id = ? AND product_id = ? ";
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setInt(1, quantity);
					ps.setInt(2, userId);
					ps.setInt(3, productId);
					ps.executeUpdate();
				}
			}

		} catch (Exception e) {
			logger.error("error in CatDAO updateQuantity() : " + e.getMessage());

		}
	}

}
