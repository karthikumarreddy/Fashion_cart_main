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
			if (session == null)
				return false;
			String category = request.getParameter("category");
			logger.debug("category : " + category);

			// Pagination params
			int page = 1;
			int recordsPerPage = 1;

			String pageParam = request.getParameter("page");
			if (pageParam != null) {
				page = Integer.parseInt(pageParam);
			}

			int offset = (page - 1) * recordsPerPage;

			ProductDAO productDAO = new ProductDAO();

			// Fetch paginated products
			List<Product> products = productDAO.getProductsList(category, recordsPerPage, offset);

			if (products == null || products.isEmpty()) {
				return false;
			}

			// Total pages
			int totalRecords = productDAO.getTotalProductCount(category);

			int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

			session.setAttribute("productList", products);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);
			session.setAttribute("category", category);

			return true; // Home.jsp

		} catch (Exception e) {
			logger.error("Error in ListProductCommand", e);
			return false;
		}
	}
}
