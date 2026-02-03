package com.fashioncart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.datasource.GetDataSource;
import com.fashioncart.dto.User;

public class UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	// to find the which user logging in
	public User findByUserName(String userName) throws SQLException, Exception {
		if (userName == null) {
			return null;
		}
		String sql = "select * from users where username=?";

		try (Connection c = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				return user;
			}

		} catch (Exception e) {
			logger.debug("error in UserDAO findByUserame() : " + e.getMessage());
		}
		return null;
	}

	public boolean saveUser(User user) {
		if (user == null) {
			return false;
		}

		String sql = " INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

		try (Connection conn = GetDataSource.getDataSource().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());

			ps.executeUpdate();
			return true;

		} catch (Exception e) {
			logger.debug("error in UserDAO saveUser() : " + e.getMessage());
		}
		return false;
	}
}
