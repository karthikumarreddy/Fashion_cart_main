package com.fashioncart.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.ProductDAO;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class AddToCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession(); // always get session

		// 1. Read cart from session
		List<Product> cartList = (List<Product>) session.getAttribute("cartList");

		// 2. If cart doesn't exist, create it
		if (cartList == null) {
			cartList = new ArrayList<>();
		}

		// 3. Get product
		String productId = req.getParameter("id");
		ProductDAO productDAO = new ProductDAO();
		Product cartProduct = productDAO.getProductById(productId);

		// 4. Add product (duplicates allowed)
		cartList.add(cartProduct);

		// 5. Save back to session
		session.setAttribute("cartList", cartList);

		// 6. Return JSON response
		try {
			res.setContentType("application/json");
			res.getWriter().print(new Gson().toJson(cartList));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
}
