package com.fashioncart.servlets;

import java.io.IOException;

import com.fashioncart.command.Command;
import com.fashioncart.command.CommandConfig;
import com.fashioncart.command.CommandFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("/controller")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("command");// getting the command from index.jsp/home.jsp
		System.out.println("Action: " + action);

		if (action == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action is missing");
			return;
		}

		Command cmd = CommandFactory.getCommand(action);// creates the object for the command

		if (cmd == null) {
			request.setAttribute("error", "Oops! Something went wrong. The requested page could not be found. Please try again later.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		boolean flag = cmd.execute(request, response);
		System.out.println("Command executed, flag=" + flag);

		CommandConfig cmdConfig = CommandFactory.configMap.get(action);
		String forwardPage = flag ? cmdConfig.getSuccessPage() : cmdConfig.getFailurePage();

		System.out.println("Forwardpage: " + forwardPage);
		request.getRequestDispatcher(forwardPage).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
