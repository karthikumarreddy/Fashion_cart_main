package com.fashioncart.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProcessPaymentCommand implements Command {

	@Override
	public boolean execute(HttpServletRequest req, HttpServletResponse res) {
		
		return false;
	}

}
