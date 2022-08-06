package com.etm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProjectRole
 */
@WebServlet("/ProjectRole")
public class ProjectRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectRole() {
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
				//get Para
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String home = request.getParameter("home");
		HttpSession session=request.getSession();
		String project_id=(String)session.getAttribute("project_id");
		String project_name=(String)session.getAttribute("project_name");
		String org_id=(String)session.getAttribute("org_id");
		if(session.getAttribute("project_id")==null)
			project_id=request.getParameter(project_id);
		
		System.out.println("At pro role set java");
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
			String query2 = "insert into handles(role,email,project_id,project_name,org_id) values(?,?,?,?,?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,role);
			st2.setString(2,email);
			st2.setInt(3,Integer.parseInt(project_id));
			st2.setString(4,project_name);
			st2.setInt(5,Integer.parseInt(org_id));
			st2.executeUpdate();
			System.out.println(query2);
			st2.close();
			conn.close();
			if(home.equals("CR")) {
				RequestDispatcher rd = request.getRequestDispatcher("CreateProject.jsp");
				rd.forward(request, response);}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("ManageProject.jsp");
				rd.forward(request, response);
			}
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
