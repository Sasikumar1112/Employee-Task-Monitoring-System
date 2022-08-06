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
 * Servlet implementation class ManageProjectServlet
 */
@WebServlet("/ManageProjectServlet")
public class ManageProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageProjectServlet() {
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
		String	project_name=request.getParameter("project_name");
		String	project_id=request.getParameter("project_id");
		String	home=request.getParameter("home");
		HttpSession session=request.getSession();
        session.setAttribute("project_name", project_name);
        session.setAttribute("project_id", project_id);
        session.setAttribute("home", home);
        RequestDispatcher rd = request.getRequestDispatcher("ManageProject.jsp");
		rd.forward(request, response);
	}

}
