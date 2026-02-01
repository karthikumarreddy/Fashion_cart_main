package com.fashioncart.command;

import com.fashioncart.dao.ProductDAO;
import com.fashioncart.dto.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductPageCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		try {
			String productIdStr = req.getParameter("productId");
			if (productIdStr == null) {
				return false;
			}

			int productId = Integer.parseInt(productIdStr);

			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.getProductById(productId);

			if (product == null) {
				return false;
			}

			req.setAttribute("product", product);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
