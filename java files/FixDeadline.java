package com.etm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FixDeadline
 */
@WebServlet("/FixDeadline")
public class FixDeadline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FixDeadline() {
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
		String date=request.getParameter("date");
		HttpSession session=request.getSession();
		String project_id=(String)session.getAttribute("project_id");
		if(session.getAttribute("project_id")==null)
			project_id=request.getParameter(project_id);
		String home = request.getParameter("home");
		
		System.out.println("At pro time set java");
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
			String query2 = "update projects1 set deadline='"+date+"' where project_id=(?)";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setInt(1,Integer.parseInt(project_id));
			st2.executeUpdate();
			System.out.println(query2);
			st2.close();
			conn.close();
			if(home.equals("CR"))
				response.sendRedirect("CreateProject.jsp");
			else
				response.sendRedirect("ManageProject.jsp");
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
