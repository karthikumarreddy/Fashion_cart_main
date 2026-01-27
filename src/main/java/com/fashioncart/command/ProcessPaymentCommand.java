package com.fashioncart.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProcessPaymentCommand implements Command {
	private static final Logger logger=LogManager.getLogger(ProcessPaymentCommand.class);
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {
    	try {

    		//getting input from payment.jsp
        String paymentMode = req.getParameter("paymentMode");
        logger.debug("payment mode : " + paymentMode);
        if (paymentMode == null || paymentMode.isEmpty()) {
            return false;//payment.jsp
        }
        //gets the existing session it will not create a new session
        HttpSession session = req.getSession(false);
        if (session == null) {
        	return false;//payment.jsp
        }
        
        session.setAttribute("paymentMode", paymentMode);

        return true; //delivery.jsp
    }catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return false;
	}
    }
}
