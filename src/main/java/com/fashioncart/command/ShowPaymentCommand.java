package com.fashioncart.command;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Product;

public class ShowPaymentCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("application/json");
		List<Product> cartList = (List<Product>) req.getAttribute("cartList");

		double total = 0.0;
		if (cartList != null) {

			for (Product p : cartList) {
				total += p.getPrice();
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(total);
		try {
			res.getWriter().print(json);
			return true;
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

	}

}
