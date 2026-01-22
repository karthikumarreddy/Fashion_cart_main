package com.fashioncart.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Product;

public class ShowPaymentCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        HttpSession session = req.getSession(false);
        if (session == null) {
            return false; // → cart.jsp
        }
        List<Product> cartList =
            (List<Product>) session.getAttribute("cartList");

        if (cartList == null || cartList.isEmpty()) {
            return false; // → cart.jsp
        }
        double totalAmount = 0.0;

        for (Product p : cartList) {
            totalAmount += p.getPrice();
        }
 
        req.setAttribute("totalAmount", totalAmount);
        return true;
    }
}
