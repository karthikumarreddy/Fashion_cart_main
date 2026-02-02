package com.fashioncart.command;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fashioncart.dao.ProductDAO;
import com.fashioncart.dto.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListProductCommand implements Command {
	private static final Logger logger = LogManager.getLogger(ListProductCommand.class);

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			HttpSession session = request.getSession();
			String category = request.getParameter("category");
			if (session == null)
				return false;

			logger.debug("category : " + category);
			ProductDAO productDAO = new ProductDAO();

			List<Product> products = productDAO.getProductsList(category);
			if (products == null || products.isEmpty()) {

				return false;
			}
			session.setAttribute("productList", products);
			session.setAttribute("category", category);
			return true; // Home.jsp

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
}
