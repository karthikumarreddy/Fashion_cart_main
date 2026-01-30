package com.fashioncart.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.fashioncart.dao.UserDAO;
import com.fashioncart.dto.User;
import com.fashioncart.service.ValidationServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupCommand implements Command {
	private static final Logger logger = LogManager.getLogger(SignupCommand.class);

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {

			String userName = req.getParameter("userName");
			logger.debug("user name : " + userName);
			String email = req.getParameter("email");
			logger.debug("user email : " + email);
			String enterPassword = req.getParameter("enterPassword");
			logger.debug("user Entered password : " + enterPassword);
			String confirmPassword = req.getParameter("confrmPassword");
			logger.debug("confirm password " + confirmPassword);
			boolean flag = false;

			if (userName == null || !ValidationServices.validateUserName(userName)) {
				req.setAttribute("userNameMessage", "Invalid UserName Enter Again!");
				flag = true;
			}

			if (email == null || !ValidationServices.validateEmail(email)) {
				req.setAttribute("EmailMessage", "Invalid Email Enter Again!");
				flag = true;
			}
			if (enterPassword == null || confirmPassword == null || !ValidationServices.validatePssword(enterPassword)) {

				req.setAttribute("passwordMessage", "Password must be at least 8 characters long and include one uppercase letter, "
								+ "one lowercase letter,one number, " + "and one special character (@ # $ % ^ & + = !).");
				flag = true;
			}

			if (flag) {
				req.setAttribute("submitted", Boolean.TRUE);
				return false;
			}

			if (enterPassword.equals(confirmPassword)) {
				confirmPassword = BCrypt.hashpw(confirmPassword, BCrypt.gensalt());
				User user = new User(userName, email, confirmPassword);
				UserDAO userDao = new UserDAO();
				req.setAttribute("message", "Account Created Sucessfully");
				return userDao.saveUser(user);
			}
		} catch (

		Exception e) {
			logger.error(e.getMessage());

		}
		return false;
	}

}
