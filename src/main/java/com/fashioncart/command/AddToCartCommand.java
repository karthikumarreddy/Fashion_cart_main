package com.fashioncart.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddToCartCommand implements Command {
	private static final Logger logger=LogManager.getLogger(AddToCartCommand.class);
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
	try {
	    HttpSession session = req.getSession();
	    User user = (User) session.getAttribute("loggedUser");
	    logger.debug("user :" + user);

	    if (user == null) {
	        req.setAttribute("error", "Please login to add items to cart");
	        return false; // login.jsp
	    }


	    int productId = Integer.parseInt(req.getParameter("id"));
	    logger.debug("Product_id :" + productId);

	    CartDAO cartDAO = new CartDAO();
	    cartDAO.addToCart(user.getUserId(), productId);
	    
	    int count = cartDAO.getCartCount(user.getUserId());
	    session.setAttribute("cartCount", count);
	    logger.debug("cart count : "+count);


	    return true; // cart.jsp
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return false;
	}


	}
}
