package com.fashioncart.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class ShowPaymentCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        // 1️⃣ Get existing session
        HttpSession session = req.getSession(false);
        if (session == null) {
            return false; // → cart.jsp
        }

        // 2️⃣ Get cart from session
        List<Product> cartList =
            (List<Product>) session.getAttribute("cartList");

        if (cartList == null || cartList.isEmpty()) {
            return false; // → cart.jsp
        }

        // 3️⃣ Calculate total amount
        double totalAmount = 0.0;

        for (Product p : cartList) {
            totalAmount += p.getPrice();
        }

        // 4️⃣ Set total amount for JSP
        req.setAttribute("totalAmount", totalAmount);

        // 5️⃣ Forward to payment.jsp (via CommandFactory)
        return true;
    }
}
