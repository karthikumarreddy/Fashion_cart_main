package com.fashioncart.command;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.fashioncart.dao.UserDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.User;

public class LoginCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
			String userName = req.getParameter("userName");
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			if (userName == null || password.trim() == null || email.trim() == null) {
				return false;
			}
			UserDAO userDao = new UserDAO();
			User user = userDao.findByUserName(userName);
			String pwd = userDao.getPassword(userName);
			if (user.getUserName().equals(userName) && user.getEmail().equals(email) && BCrypt.checkpw(password, pwd)) {
				HttpSession session = req.getSession();
				session.setAttribute("loggedUser", user);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
