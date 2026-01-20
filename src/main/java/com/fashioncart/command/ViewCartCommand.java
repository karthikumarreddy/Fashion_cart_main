package com.fashioncart.command;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class ViewCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		Gson gson=new Gson();
		HttpSession session =req.getSession();
		List<Product>cartList=(List<Product>) session.getAttribute("cartList");
		String json=gson.toJson(cartList);
		try {
			res.getWriter().print(json);
			res.getWriter().flush();
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}
