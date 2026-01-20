package com.fashioncart.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fashioncart.dao.ProductDAO;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class AddToCartCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session=null;
		List<Product> cartList = null;
		ProductDAO product = new ProductDAO();
		String product_id = req.getParameter("id");
		System.out.println("from add to cart"+product_id);
		Product cartProduct = product.getProductById(product_id);
		if (cartList == null) {
			cartList = new ArrayList<Product>();
			
		}
		if(session==null) {
			session=req.getSession();
		}
		
	
		cartList.add(cartProduct);
		System.out.println(cartList);
		session.setAttribute("cartList", cartList);
		Gson gson=new Gson();
		String json=gson.toJson(cartList);
		System.out.println(json);
		try {
			res.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
