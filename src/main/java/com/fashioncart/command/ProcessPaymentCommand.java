package com.fashioncart.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProcessPaymentCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {
    	// grtting the paymentmode from request
        String paymentMode = req.getParameter("paymentMode");
        if (paymentMode == null || paymentMode.isEmpty()) {
            return false;
        }
      //gets the existing session it will not create a new session
        HttpSession session = req.getSession(false);
        if (session == null) {
        	return false;//payment.jsp
        }

       
        session.setAttribute("paymentMode", paymentMode);

        return true; //delivery.jsp
    }
}
