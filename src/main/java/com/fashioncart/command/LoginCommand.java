package com.fashioncart.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.UserDAO;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
	private static final Logger logger = LogManager.getLogger(LoginCommand.class);

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {

			HttpSession session = req.getSession();
			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			logger.debug("user name = " + userName);
			logger.debug("password = " + password);

			if (userName == null || password.trim() == null || session == null)
				return false;

			UserDAO userDao = new UserDAO();
			User user = userDao.findByUserName(userName);

			if (user == null) {
				req.setAttribute("usernameError", "username is incorrect");
				return false;
			}

			String pwd = user.getPassword();

			if (user.getUserName().equals(userName)) {
				if (BCrypt.checkpw(password, pwd)) {
					session.setAttribute("loggedUser", user);
					CartDAO cartDAO = new CartDAO();
					int cartCount = cartDAO.getCartCount(user.getUserId());
					session.setAttribute("cartCount", cartCount);
					session.setAttribute("username", user.getUserName());
					return true;
				} else {
					req.setAttribute("passwordError", "password is incorrect!");
					return false;
				}
			}

		} catch (Exception e) {
			logger.error("error in LoginCommand : " + e.getMessage());
		}
		return false;

	}
}
