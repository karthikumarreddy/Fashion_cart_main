package com.fashioncart.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class ViewCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {

		Gson gson = new Gson();
		res.setContentType("application/json");

		HttpSession session = req.getSession();
		List<Product> cartList = (List<Product>)session.getAttribute("cartList");
		System.out.println("cartList:  "+cartList);
		double total = 0.0;
		for (Product p : cartList) {
			System.out.println("Product :"+p);
			total += p.getPrice(); //quantity = 1
		}

		// Wrap both cart + total into ONE object
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("cartList", cartList);
		responseData.put("total", total);

		try {
			res.getWriter().print(gson.toJson(responseData));
			System.out.println(responseData);
			res.getWriter().flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
