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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		        throws ServletException, IOException {

    		    String action = request.getParameter("command");
    		    System.out.println("Action: " + action);

    		    if (action == null) {
    		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action is missing");
    		        return;
    		    }

    		    Command cmd = CommandFactory.getCommand(action);

    		    if (cmd == null) {
    		        response.sendError(HttpServletResponse.SC_NOT_FOUND,
    		                "No command found for action: " + action);
    		        return;
    		    }

    		    boolean flag = cmd.execute(request, response);
    		    System.out.println("Command executed, flag=" + flag);

    		    // THIS LINE FIXES EVERYTHING
    		    if (response.isCommitted()) {
    		        return; // JSON already sent → STOP
    		    }

    		    CommandConfig cmdConfig = CommandFactory.configMap.get(action);
    		    String forwardPage = flag
    		            ? cmdConfig.getSuccessPage()
    		            : cmdConfig.getFailurePage();

    		    System.out.println("Forwardpage: "+forwardPage);
    		    request.getRequestDispatcher(forwardPage)
    		           .include(request, response);
//    		    response.sendRedirect(forwardPage);
    		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
