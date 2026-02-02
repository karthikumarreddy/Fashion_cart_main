package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.dao.CartDAO;
import com.fashioncart.dto.CartItem;
import com.fashioncart.dto.CartItemView;
import com.fashioncart.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCartCommand implements Command {
	private static final Logger logger = LogManager.getLogger(ViewCartCommand.class);

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession(false);
			User user = (User) session.getAttribute("loggedUser");
			if (session == null || user == null) {

				req.setAttribute("errorMessage", "Login required. Please Login to view your cart.");
				return false;// home.jsp
			}

			CartDAO cartDAO = new CartDAO();
			String action = req.getParameter("action");
			String productId = req.getParameter("productId");
			int productIdint = 0;
			if (action != null || productId != null) {

				productIdint = Integer.parseInt(req.getParameter("productId"));

				switch (action) {

				case "inc": {
					cartDAO.updateQuantity(user.getUserId(), productIdint, action);
					break;
				}
				case "dec": {
					cartDAO.updateQuantity(user.getUserId(), productIdint, action);

					break;
				}
				case "remove": {
					cartDAO.updateQuantity(user.getUserId(), productIdint, action);
					break;
				}

				}
			}

			List<CartItem> cartItems = cartDAO.getCartItems(user.getUserId());

			if (cartItems == null || cartItems.isEmpty()) {
				session.setAttribute("cartCount", 0);
				return true;
			}

			// calculating the total amount
			List<CartItemView> cartDTOList = new ArrayList<>();

			double totalAmount = 0;

			for (CartItem item : cartItems) {
				CartItemView dto = new CartItemView(item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getCategory(),
								item.getProduct().getPrice());
				dto.setQuantity(item.getQuantity());

				totalAmount += dto.getPrice() * dto.getQuantity();

				cartDTOList.add(dto);
			}
			if (totalAmount <= 0) {
				cartDAO.updateQuantity(user.getUserId(), productIdint, "remove");
			}

			req.setAttribute("cartList", cartDTOList);
			session.setAttribute("totalAmount", totalAmount);
			session.setAttribute("cartCount", cartDAO.getCartCount(user.getUserId()));

			return true; // cart.jsp
		} catch (

		Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return false; // home.jsp
		}
	}

}
