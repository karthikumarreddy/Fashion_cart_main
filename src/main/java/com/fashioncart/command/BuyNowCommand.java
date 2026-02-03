package com.fashioncart.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.ProductDAO;
import com.fashioncart.dto.Product;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BuyNowCommand implements Command {
	private static final Logger logger = LogManager.getLogger(BuyNowCommand.class);

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

		try {

			HttpSession session = req.getSession(false);
			User user = (User) session.getAttribute("loggedUser");
			int productId = Integer.parseInt(req.getParameter("id"));

			if (user == null || session == null || productId == 0) {
				req.setAttribute("errorMessage", "Login required . Please Login to purchase this item.");
				return false; // login.jsp
			}

			Product product = new ProductDAO().getProductById(productId);
			if (product == null)
				return false;

			CartDAO cartDAO = new CartDAO();
			cartDAO.addToCart(user.getUserId(), productId, product.getImagePath());
			Double totalAmount = product.getPrice();
			session.setAttribute("totalAmount", totalAmount);

			return true;
		} catch (Exception e) {
			logger.error("error in BuyNowCommand :" + e.getMessage());
		}
		return false;
	}

}
