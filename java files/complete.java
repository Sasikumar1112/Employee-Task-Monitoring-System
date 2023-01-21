package com.etm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class complete
 */
@WebServlet("/complete")
public class complete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public complete() {
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
		String url="jdbc:postgresql://localhost:5432/postgres";
		String uname="postgres";
		String pw="0";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Connection conn=DriverManager.getConnection(url,uname,pw);
	
			String query2 = "update  tasks1 set status=(?) where task_id="+task_id+" and project_id="+project_id;
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,"Completed");
			st2.executeUpdate();
			session.setAttribute("task_name", task_name);
			session.setAttribute("home", home);
			session.setAttribute("task_id", task_id);
			session.setAttribute("project_name", project_name);
			session.setAttribute("project_id", project_id);
			if(home.equals("lead"))
				response.sendRedirect("TeamLeader.jsp");
			else
				response.sendRedirect("TeamMember.jsp");
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}


