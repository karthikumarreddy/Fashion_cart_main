package com.fashioncart.command;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class ShowPaymentCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");

	    HttpSession session = req.getSession(false);

	    List<Product> cartList = null;
	    if (session != null) {
	        cartList = (List<Product>) session.getAttribute("cartList");
	    }

	    double total = 0.0;

	    if (cartList != null) {
	        for (Product p : cartList) {
	            total += p.getPrice();
	        }
	    }
	    Gson gson=new Gson();
	    String json=gson.toJson(total);

	    try {
	    	System.out.println(json);
	        res.getWriter().print(json);
	        res.getWriter().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return true;
	}

}
