package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.CartItemView;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCartCommand implements Command {
	private static final Logger logger=LogManager.getLogger(ViewCartCommand.class);
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
		// gets the existing system 
	    HttpSession session = req.getSession(false);
	    if (session == null) {
	    	return false;//home.jsp
	    }

	    // getting the user from the session
	    User user = (User) session.getAttribute("loggedUser");
	    if (user == null) {
	    	return false; //home.jsp
	    }

	    // geting all the items from cart_item table
	    CartDAO cartDAO = new CartDAO();
	    List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());
	    
	    
	    if (cartItems == null || cartItems.isEmpty()) {
	        session.setAttribute("cartCount", 0);
	        return true;
	    }

	    // calculating the total amount 
	    List<CartItemView> cartDTOList = new ArrayList<>();

	    double totalAmount = 0;

	    for (CartItem item : cartItems) {
	        CartItemView dto = new CartItemView(
	            item.getProduct().getId(),
	            item.getProduct().getName(),
	            item.getProduct().getCategory(),
	            item.getProduct().getPrice()
	        );
	        dto.setQuantity(item.getQuantity());

	        totalAmount += dto.getPrice() * dto.getQuantity();
	        
	        // adding the productdto to cartDtolist
	        cartDTOList.add(dto);
	    }
	    
	    req.setAttribute("cartList", cartDTOList);
	    session.setAttribute("totalAmount", totalAmount); 
	    session.setAttribute("cartCount",cartDAO.getCartCount(user.getUserId()));

	    return true; //cart.jsp
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return false; //home.jsp
	}
	}

}
