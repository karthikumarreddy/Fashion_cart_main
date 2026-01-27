package com.fashioncart.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.fashioncart.dao.UserDAO;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupCommand implements Command{
	private static final Logger logger=LogManager.getLogger(SignupCommand.class);
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
		
		//getting the values from the request
		String userName=req.getParameter("userName");
		logger.debug("user name : "+userName);
		String email=req.getParameter("email");
		logger.debug("user email : "+email);
		String password=req.getParameter("password");
		logger.debug("user password : "+password);
		
//		System.out.println("Username = " + req.getParameter("userName"));
//		System.out.println("Email = " + req.getParameter("email"));		 
//		System.out.println("signup outside if..");
		
		if(userName==null && email==null && password==null) {
//			System.out.println("inside signup cammand checking");
			return false;
		}
		
		// it will encrypt the password and stores it in db 
		password = BCrypt.hashpw(password, BCrypt.gensalt());
		User user=new User(userName,email,password);
		UserDAO userDao=new UserDAO();
		return userDao.saveUser(user);// this method will return a boolean value 
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return false;
	}
	}

}
