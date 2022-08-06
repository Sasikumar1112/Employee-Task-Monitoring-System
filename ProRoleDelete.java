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
 * Servlet implementation class ProRoleDelete
 */
@WebServlet("/ProDelete")
public class ProRoleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProRoleDelete() {
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
		String email = request.getParameter("email");
		HttpSession session=request.getSession();
		String home = request.getParameter("home");
		String project_id=(String)session.getAttribute("project_id");
		System.out.println("At pro dlete role set java");
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
			String query2 = "delete from handles where email=(?) and project_id=(?)";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,email);
			st2.setInt(2,Integer.parseInt(project_id));
			st2.executeUpdate();
			System.out.println(query2);
			String query = "delete from assigned where email=(?) and project_id=(?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,email);
			st.setInt(2,Integer.parseInt(project_id));
			st.executeUpdate();
			System.out.println(st);
			st.close();
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
