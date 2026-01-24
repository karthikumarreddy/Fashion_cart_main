package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.ProductDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class AddToCartCommand implements Command {
	
	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			HttpSession session = req.getSession();
			if (session.getAttribute("loggedUser") == null) {
	            req.setAttribute("error", "Please login to add items to cart");
	            return false; // login.jsp
	        }

			List<Product> cartList = (List<Product>) session.getAttribute("cartList");

			if (cartList == null) {
				cartList = new ArrayList<>();
			}

			String productId = req.getParameter("id");// gets the product id
			ProductDAO productDAO = new ProductDAO();
			Product cartProduct = productDAO.getProductById(productId);// getting the products from db
			
			if (cartProduct == null) {
				return false; // home.jsp
			}else {
				
				cartList.add(cartProduct);
				session.setAttribute("cartList", cartList);// cartlist is now present in the session
				session.setAttribute("cartCount", cartList.size());
			}
			return true; // cart.jsp
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
