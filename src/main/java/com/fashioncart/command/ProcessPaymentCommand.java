package com.fashioncart.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProcessPaymentCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        String paymentMode = req.getParameter("paymentMode");
        if (paymentMode == null || paymentMode.isEmpty()) {
            return false;
        }

        HttpSession session = req.getSession(false);
        if (session == null) return false;

        // Store payment mode temporarily
        session.setAttribute("paymentMode", paymentMode);

        return true; // → delivery.jsp
    }
}
