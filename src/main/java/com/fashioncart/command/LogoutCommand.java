package com.fashioncart.command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {
	private static final Logger logger=LogManager.getLogger(LogoutCommand.class);
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {
    	try {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return true; //login.jsp
    }catch (Exception e) {
		e.printStackTrace();
		logger.error(e.getMessage());
		return false;
	}
    }
}

