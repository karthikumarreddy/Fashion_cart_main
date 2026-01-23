package com.fashioncart.command;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse res) {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return true; //login.jsp
    }
}

