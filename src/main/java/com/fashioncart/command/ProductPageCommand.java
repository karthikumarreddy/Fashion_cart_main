package com.fashioncart.command;

import java.util.List;

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
			User user = (User) session.getAttribute("loggedUser");
			List<Product> products = (List<Product>) req.getAttribute("allProductList");
			int productId = Integer.parseInt(req.getParameter("productId"));

			if (user == null || session == null || products == null || productId == 0)
				return false;
			for (Product p : products) {
				if (p.getId().equals(productId)) {
					req.setAttribute("productDetails", p);
					return true;
				}
			}
		} catch (Exception e) {

			return false;
		}
		return false;
	}

}
