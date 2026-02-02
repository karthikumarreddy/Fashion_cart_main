package com.fashioncart.command;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dao.ProductDAO;
import com.fashioncart.dto.Product;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProductPageCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession(false);
			if (session == null)
				return false;

			User user = (User) session.getAttribute("loggedUser");
			if (user == null) {
				return false;
			}

			String action = req.getParameter("add");
			String productIdStr = req.getParameter("productId");

			if (productIdStr == null)
				return false;

			int productId = Integer.parseInt(productIdStr);

			if ("addcart".equals(action) && user != null) {
				CartDAO cartDao = new CartDAO();
				cartDao.addToCart(user.getUserId(), productId);
			}

			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.getProductById(productId);

			if (product == null)
				return false;
			CartDAO cartDao = new CartDAO();
			req.setAttribute("product", product);
			session.setAttribute("cartCount", cartDao.getCartCount(user.getUserId()));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
