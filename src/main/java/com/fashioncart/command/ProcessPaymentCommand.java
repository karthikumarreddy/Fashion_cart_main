package com.fashioncart.command;

import java.sql.Timestamp;
import java.util.List;

import com.fashioncart.dao.OrderItemDAO;
import com.fashioncart.dao.OrdersDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Orders;
import util.Product;

public class ProcessPaymentCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		String paymentMode = req.getParameter("paymentMode");
		if (paymentMode == null || paymentMode.isEmpty()) {
			paymentMode = "COD";
		}

		// 2️⃣ Get cart from session
		HttpSession session = req.getSession(false);
		if (session == null) {
			return false;
		}

		List<Product> cartList = (List<Product>) session.getAttribute("cartList");
		if (cartList == null || cartList.isEmpty()) {
			return false;
		}

		// 3️⃣ Calculate total amount + quantity
		double totalAmount = 0.0;
		boolean[] counted = new boolean[cartList.size()];

		for (int i = 0; i < cartList.size(); i++) {
			if (counted[i])
				continue;

			Product p = cartList.get(i);
			int quantity = 1;

			for (int j = i + 1; j < cartList.size(); j++) {
				if (p.getId().equals(cartList.get(j).getId())) {
					quantity++;
					counted[j] = true;
				}
			}

			totalAmount += p.getPrice() * quantity;
		}

		// 4️⃣ Save order
		Orders order = new Orders(totalAmount, new Timestamp(System.currentTimeMillis()), paymentMode, "ORDERED");

		OrdersDAO orderDao = new OrdersDAO();
		int orderId = orderDao.saveOrders(order);

		if (orderId <= 0) {
			return false;
		}

		// 5️⃣ Save order items with quantity
		OrderItemDAO itemDao = new OrderItemDAO();
		counted = new boolean[cartList.size()]; // reset array

		for (int i = 0; i < cartList.size(); i++) {
			if (counted[i])
				continue;

			Product p = cartList.get(i);
			int quantity = 1;

			for (int j = i + 1; j < cartList.size(); j++) {
				if (p.getId().equals(cartList.get(j).getId())) {
					quantity++;
					counted[j] = true;
				}
			}

			itemDao.saveOrderItem(orderId, p, quantity);
		}

		session.removeAttribute("cartList");

		return true; // forward to success page
	}
}
