package com.fashioncart.command;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.ProductDAO;
import com.fashioncart.dto.Product;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BuyNowCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession(false);

		User user = (User) session.getAttribute("loggedUser");

		if (user == null) {
			req.setAttribute("error", "Please login to Buy Product");
			return false; // login.jsp
		}
		if (session == null) {
			return false;
		}

		int productId = Integer.parseInt(req.getParameter("id"));
		System.out.println("id " + productId);
		CartDAO cartDAO = new CartDAO();
		cartDAO.addToCart(user.getUserId(), productId);
		Product product = new ProductDAO().getProductsById(productId);

		Double totalAmount = product.getPrice();
		System.out.println("Total Amount in buy now Command: " + totalAmount);
		session.setAttribute("totalAmount", totalAmount);

		if (product != null) {
			return true;
		}
		return false;
	}

}
