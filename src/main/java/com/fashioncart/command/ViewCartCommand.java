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
			if (session == null)
				return false;
			User user = (User) session.getAttribute("loggedUser");
			if (user == null) {

				req.setAttribute("errorMessage", "Login required. Please Login to view your cart.");
				return false;// home.jsp
			}
			CartDAO cartDAO = new CartDAO();

			String action = req.getParameter("action");
			String productId = req.getParameter("productId");
			int quantity = 0;
			int productIdint = 0;

			if (action != null || productId != null) {

				productIdint = Integer.parseInt(req.getParameter("productId"));

				switch (action) {

				case "inc": {
					cartDAO.updateQuantity(user.getUserId(), productIdint, quantity, action);
					break;
				}
				case "dec": {
					cartDAO.updateQuantity(user.getUserId(), productIdint, quantity, action);
					break;
				}
				case "remove": {
					cartDAO.updateQuantity(user.getUserId(), productIdint, quantity, action);
					break;
				}
				case "updateQuantity": {
					String quantitystr = req.getParameter("enterQuantity");

					if (quantitystr != null && !quantitystr.trim().isEmpty()) {
						try {
							quantity = Integer.parseInt(quantitystr.trim());
						} catch (NumberFormatException e) {
							logger.error("Invalid quantity input: " + quantitystr + ". Resetting to 1.");
							quantity = 1;
						}
					}
					if (quantity < 1)
						quantity = 1;
					if (quantity > 100) {
						req.setAttribute("quantityErrorMessage", "Quantity cannot be more than 100");
						quantity = 100;
					}

					cartDAO.updateQuantity(user.getUserId(), productIdint, quantity, action);
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
								item.getProduct().getPrice(), item.getProduct().getImagePath());
				dto.setQuantity(item.getQuantity());

				totalAmount += dto.getPrice() * dto.getQuantity();

				cartDTOList.add(dto);
			}

			req.setAttribute("cartList", cartDTOList);
			session.setAttribute("totalAmount", totalAmount);
			session.setAttribute("cartCount", cartDAO.getCartCount(user.getUserId()));

			return true; // cart.jsp
		} catch (Exception e) {
			logger.error("error in ViewCartCommand : " + e.getMessage());
			return false; // home.jsp
		}
	}

}
