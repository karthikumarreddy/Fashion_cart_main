package com.fashioncart.command;

import java.util.List;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ShowPaymentCommand implements Command {

	
	/*
	 * getting the user object
	 * getting the cartitems from the cartDAO using userID
	 * and returning  true if cartItems not null
	 */
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {
    		
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
        	return false;
        }

        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());

        if (cartItems == null || cartItems.isEmpty()) {
            return false; // cart.jsp
        }
        
        return true;//payment.jsp
    }
}
