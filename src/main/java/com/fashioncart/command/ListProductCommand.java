package com.fashioncart.command;

import java.io.IOException;

import com.fashioncart.dao.ProductDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListProductCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    String category = request.getParameter("category");
	    ProductDAO product = new ProductDAO();

	    if (category == null) {
	        return false;
	    }

	    try {
	        String json = null;

	        if ("ALL".equals(category)) {
	            json = product.getAllProducts();
	        } else if ("MEN".equalsIgnoreCase(category) || "WOMEN".equalsIgnoreCase(category) ||"children".equalsIgnoreCase(category)) {
	            json = product.getProductsByCategory(category);
	        }

	        if (json != null) {
	            System.out.println("JSON SENT: " + json);
	            System.out.println(category);// console
	            response.getWriter().print(json);   // browser
	            response.getWriter().flush();
	            return true;
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}

