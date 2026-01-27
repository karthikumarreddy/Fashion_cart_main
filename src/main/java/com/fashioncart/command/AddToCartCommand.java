package com.fashioncart.command;

import com.fashioncart.dao.CartDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.User;

public class AddToCartCommand implements Command {
	
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
	
	    HttpSession session = req.getSession();
	    User user = (User) session.getAttribute("loggedUser");

	    if (user == null) {
	        req.setAttribute("error", "Please login to add items to cart");
	        return false; // login.jsp
	    }


	    int productId = Integer.parseInt(req.getParameter("id"));

	    CartDAO cartDAO = new CartDAO();
	    cartDAO.addToCart(user.getUserId(), productId);
	    
	    int count = cartDAO.getCartCount(user.getUserId());
	    session.setAttribute("cartCount", count);


	    return true; // cart.jsp
	}


}
