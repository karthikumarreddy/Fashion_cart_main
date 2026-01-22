package com.fashioncart.command;

import java.util.List;

import com.fashioncart.dao.ProductDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
            products = productDAO.getProductsByCategoryList(category);//getting the product base on category from db and stored to an list
        }
        if (products == null || products.isEmpty()) {
            return false; //error.jsp
        }

        HttpSession session=request.getSession();
        session.setAttribute("productList", products);

      
        return true; //Home.jsp
    }
}
