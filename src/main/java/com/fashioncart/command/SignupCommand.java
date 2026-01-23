package com.fashioncart.command;

import org.mindrot.jbcrypt.BCrypt;

import com.fashioncart.dao.UserDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.User;

public class SignupCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		String userName=req.getParameter("userName");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		 password = BCrypt.hashpw(password, BCrypt.gensalt());
		
		if(userName==null && email==null && password==null) {
			return false;
		}
		User user=new User(userName,email,password);
		UserDAO userDao=new UserDAO();
		return userDao.saveUser(user);
	}

}
