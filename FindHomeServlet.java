package com.etm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FindHomeServlet
 */
@WebServlet("/FindHomeServlet")
public class FindHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String redirect=(String) session.getAttribute("role");
		if(redirect==null ||redirect.equals("At Waiting list"))
			response.sendRedirect("options.jsp");
		else{
			if(redirect.equals("SuperAdmin"))
				response.sendRedirect("SuperAdmin.jsp");
			if(redirect.equals("Manager"))
				response.sendRedirect("Manager.jsp");
			if(redirect.equals("Employee"))
				response.sendRedirect("EmployeeHome.jsp");
		}
	}

}
