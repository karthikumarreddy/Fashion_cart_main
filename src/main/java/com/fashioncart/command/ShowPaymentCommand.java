package com.fashioncart.command;

import java.util.List;

import com.fashioncart.dao.CartDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.CartItem;
import util.User;

public class ShowPaymentCommand implements Command {

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
