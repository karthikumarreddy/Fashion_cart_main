package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.CartItemView;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RemoveFromCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
	
		try {
			
			// it will return the existing object insted of creating new 
			HttpSession session=req.getSession(false);
			if(session==null) {
				
				System.out.println("inside session remove cart");	
				return false;
			}
			User user=(User)session.getAttribute("loggedUser");
			if(user== null) {
				System.out.println("is=nside user remove cart");
				return false;
			}
			
			int productId=Integer.parseInt(req.getParameter("productId"));
			CartDAO cartDao=new CartDAO();
			cartDao.removeFromCart(user.getUserId(), productId);
			
			List<CartItem> cartItems = cartDao.getCartItems(user.getUserId());
			
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
		
		    int cartCount = cartDao.getCartCount(user.getUserId());
			session.setAttribute("cartCount",cartCount);
			return true;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
