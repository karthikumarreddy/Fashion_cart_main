package com.fashioncart.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	boolean execute(HttpServletRequest req,HttpServletResponse res);
}
