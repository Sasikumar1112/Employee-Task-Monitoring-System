package com.etm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManageTaskServlet
 */
@WebServlet("/ManageTaskServlet")
public class ManageTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageTaskServlet() {
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
		String task_name = request.getParameter("task_name");
		String task_id = request.getParameter("task_id");
		String project_id = request.getParameter("project_id");
		String project_name = request.getParameter("project_name");
		String home = request.getParameter("home");
		HttpSession session=request.getSession();
		System.out.println("Project id at Mange serv:"+project_id);
		session.setAttribute("task_name", task_name);
		session.setAttribute("home", home);
		session.setAttribute("home", home);
		session.setAttribute("task_id", task_id);
		session.setAttribute("project_name", project_name);
		session.setAttribute("project_id", project_id);
		response.sendRedirect("ManageTask.jsp");
	}

}
