package com.fashioncart.command;
<<<<<<< HEAD

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
=======
>>>>>>> branch 'master' of https://github.com/karthikumarreddy/Fashion_cart_main.git
import org.mindrot.jbcrypt.BCrypt;
import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.UserDAO;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
private static final Logger logger=LogManager.getLogger(LoginCommand.class);
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
			
			//getting user input from login.jsp
	
			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			logger.debug("user name = "+userName);
			logger.debug("password = "+password);
			if (userName == null || password.trim() == null) {
				return false;
			}
			UserDAO userDao = new UserDAO();
			
			//getting user object from userDAO by username
			User user = userDao.findByUserName(userName);
			String pwd = userDao.getPassword(userName);
			
			if(user.getUserName().equals(userName) && BCrypt.checkpw(password, pwd)) {
				HttpSession session = req.getSession();
				session.setAttribute("loggedUser", user);
				
				
				/*getting cart count from cartDAO using userID 
				 * and setting the count in session
				 */
				CartDAO cartDAO = new CartDAO();
				int cartCount = cartDAO.getCartCount(user.getUserId());
				session.setAttribute("cartCount", cartCount);
				return true;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
