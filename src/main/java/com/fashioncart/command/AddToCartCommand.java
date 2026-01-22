package com.fashioncart.command;

import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.ProductDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class AddToCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

	    HttpSession session = req.getSession();

	    List<Product> cartList =(List<Product>)session.getAttribute("cartList");

	    if (cartList == null) {
	        cartList = new ArrayList<>();
	    }

	    String productId = req.getParameter("id");
	    ProductDAO productDAO = new ProductDAO();
	    Product cartProduct = productDAO.getProductById(productId);//getting the products from db

	    if (cartProduct == null) {
	        return false; //home.jsp
	    }

	    cartList.add(cartProduct);
	    session.setAttribute("cartList", cartList);//cartlist is now present in the session 

	    return true; //cart.jsp
	}

}
