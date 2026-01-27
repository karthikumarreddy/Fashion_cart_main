package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.ProductqtyDTO;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

	    HttpSession session = req.getSession(false);
	    if (session == null) return false;

	    User user = (User) session.getAttribute("loggedUser");
	    if (user == null) return false;

	    CartDAO cartDAO = new CartDAO();
	    List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());

	    if (cartItems == null || cartItems.isEmpty()) {
	        req.setAttribute("cartList", new ArrayList<>());
	        req.setAttribute("totalAmount", 0.0);   
	        session.setAttribute("cartCount", 0);
	        return true;
	    }

	    List<ProductqtyDTO> cartDTOList = new ArrayList<>();
	    double totalAmount = 0;

	    for (CartItem item : cartItems) {
	        ProductqtyDTO dto = new ProductqtyDTO(
	            item.getProduct().getId(),
	            item.getProduct().getName(),
	            item.getProduct().getCategory(),
	            item.getProduct().getPrice()
	        );
	        dto.setQuantity(item.getQuantity());

	        totalAmount += dto.getPrice() * dto.getQuantity();
	        cartDTOList.add(dto);
	    }

	    req.setAttribute("cartList", cartDTOList);
	    session.setAttribute("totalAmount", totalAmount); // ✅ FIX
	    session.setAttribute("cartCount",
	        cartDAO.getCartCount(user.getUserId()));

	    return true; // cart.jsp
	}

}
