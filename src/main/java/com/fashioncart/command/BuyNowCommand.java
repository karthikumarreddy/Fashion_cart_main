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
		int productId = Integer.parseInt(req.getParameter("id"));

		if (user == null || session == null || productId == 0)
			return false; // login.jsp

		CartDAO cartDAO = new CartDAO();
		cartDAO.addToCart(user.getUserId(), productId);
		Product product = new ProductDAO().getProductById(productId);
		if (product == null)
			return false;

		Double totalAmount = product.getPrice();
		session.setAttribute("totalAmount", totalAmount);

		return true;
	}

}
