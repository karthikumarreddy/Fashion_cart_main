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
			if (session == null) {
				return false;
			}
			String productIdStr = req.getParameter("productId");
			if (productIdStr == null)
				return false;
			int productId = Integer.parseInt(productIdStr);
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.getProductById(productId);
			if (product == null) {

				return false;
			}
			req.setAttribute("product", product);

			User user = (User) session.getAttribute("loggedUser");

			String action = req.getParameter("add");

			if ("addcart".equals(action)) {
				if (user == null) {
					req.setAttribute("errorMessage", "please login to add the product in cart");
					System.out.println("inside productpage user is null");
					return false;
				} else {
					CartDAO cartDao = new CartDAO();
					cartDao.addToCart(user.getUserId(), productId);
					session.setAttribute("cartCount", cartDao.getCartCount(user.getUserId()));
				}
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
