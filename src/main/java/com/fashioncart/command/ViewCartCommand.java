package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.ProductDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.CartItem;
import util.User;

public class ViewCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		// gtes the existing system 
	    HttpSession session = req.getSession(false);
	    if (session == null) {
	    	return false;
	    }

	    // geting the user from the session 
	    User user = (User) session.getAttribute("loggedUser");
	    if (user == null) {
	    	return false;
	    }

	    // geting all the items from cart_item table
	    CartDAO cartDAO = new CartDAO();
	    List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());
	    
	    
	    if (cartItems == null || cartItems.isEmpty()) {
	        req.setAttribute("cartList", new ArrayList<>());
	        req.setAttribute("totalAmount", 0.0);   
	        session.setAttribute("cartCount", 0);
	        return true;
	    }

	    // calculating the total amount 
	    List<ProductDTO> cartDTOList = new ArrayList<>();
	    double totalAmount = 0;

	    for (CartItem item : cartItems) {
	        ProductDTO dto = new ProductDTO(
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
	    req.setAttribute("totalAmount", totalAmount); 
	    session.setAttribute("cartCount",cartDAO.getCartCount(user.getUserId()));

	    return true; // cart.jsp
	}

}
