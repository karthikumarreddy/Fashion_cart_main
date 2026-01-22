package com.fashioncart.command;

import java.util.List;

import com.fashioncart.dao.ProductDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Product;

public class ListProductCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest request,
                           HttpServletResponse response) {

        String category = request.getParameter("category");
        
        

        ProductDAO productDAO = new ProductDAO();
        List<Product> products;

        if (category == null || category.equalsIgnoreCase("ALL")) {
            products = productDAO.getAllProductsList();
        } else {
            products = productDAO.getProductsByCategoryList(category);
        }

        if (products == null || products.isEmpty()) {
            return false; // → error.jsp
        }

        // Send data to JSP
        request.setAttribute("productList", products);
        request.setAttribute("category", category);

        return true; // → Home.jsp
    }
}
