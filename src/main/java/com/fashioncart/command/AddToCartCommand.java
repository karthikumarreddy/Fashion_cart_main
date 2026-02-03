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

public class AddToCartCommand implements Command {
	private static final Logger logger = LogManager.getLogger(AddToCartCommand.class);

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession(false);
			if (session == null)
				return false;
			User user = (User) session.getAttribute("loggedUser");
			logger.debug("user :" + user);

			if (user == null) {
				req.setAttribute("errorMessage", "Login required. Please Login to add items to your cart.");
				return false; // login.jsp
			}

			int productId = Integer.parseInt(req.getParameter("id"));
			logger.debug("Product_id :" + productId);
			ProductDAO productDao = new ProductDAO();

			Product product = productDao.getProductById(productId);
			CartDAO cartDAO = new CartDAO();
			cartDAO.addToCart(user.getUserId(), productId, product.getImagePath());

			int count = cartDAO.getCartCount(user.getUserId());
			session.setAttribute("cartCount", count);
			req.setAttribute("successMessage", "Item added to your cart.");
			logger.debug("cart count : " + count);

			return true; // cart.jsp
		} catch (Exception e) {
			logger.error("error in AddToCartCommand : " + e.getMessage());
			return false;
		}

	}
}
