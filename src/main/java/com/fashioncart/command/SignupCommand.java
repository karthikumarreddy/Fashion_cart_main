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
			String confirmPassword = req.getParameter("confirmPassword");
			logger.debug("confirm password " + confirmPassword);

			if (!ValidationServices.validateUserName(userName) || !ValidationServices.validateEmail(email)
							|| !ValidationServices.validatePssword(enterPassword)) {
				System.out.println("Inside validate method");
				System.out.println("username:  " + ValidationServices.validateUserName(userName));
				System.out.println("email: " + ValidationServices.validateEmail(email));
				System.out.println("Password: " + ValidationServices.validatePssword(enterPassword));
				return false;
			}
			if (userName == null || email == null || enterPassword == null || confirmPassword == null) {
				System.out.println("check");
				return false;

			}
			System.out.println("outside confirm Password");
			if (enterPassword.equals(confirmPassword)) {
				System.out.println(4 + "  =  " + enterPassword.equals(confirmPassword));
				enterPassword = BCrypt.hashpw(enterPassword, BCrypt.gensalt());
				User user = new User(userName, email, enterPassword);
				UserDAO userDao = new UserDAO();
				System.out.println("user : " + user);

				req.setAttribute("SuccessFullMessage", "Account Created Sucessfully");
				return userDao.saveUser(user);
			}
		} catch (Exception e) {
			logger.error("error in SignupCommand : " + e.getMessage());

		}
		return false;

	}

}
