package com.etm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChooseLogin
 */
@WebServlet("/ChooseLogin")
public class ChooseLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseLogin() {
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
		String org_id = request.getParameter("org_id");
		String org_name = request.getParameter("org_name");
		String role = request.getParameter("role");
		HttpSession session=request.getSession();
		session.setAttribute("org_id",org_id);
		session.setAttribute("org_name",org_name);
		session.setAttribute("role",role);
		if(role.equals("Manager")) {
			RequestDispatcher rd = request.getRequestDispatcher("Manager.jsp");
			rd.forward(request, response);}
		else if(role.equals("Employee")){RequestDispatcher rd = request.getRequestDispatcher("EmployeeHome.jsp");
			rd.forward(request, response);}
		else {RequestDispatcher rd = request.getRequestDispatcher("options.jsp");
		rd.forward(request, response);}
	}

}
