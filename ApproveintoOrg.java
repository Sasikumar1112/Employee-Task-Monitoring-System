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

/**
 * Servlet implementation class ApproveintoOrg
 */
@WebServlet("/Approve")
public class ApproveintoOrg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveintoOrg() {
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
					//parameter
		String email = request.getParameter("email");
		String page = request.getParameter("page");
		
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
			String query2 = "update works_in set role=(?) where email=(?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,"Approved");
			st2.setString(2,email);
			st2.executeUpdate();
			System.out.println(query2);
			st2.close();
			conn.close();
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.equals("org")) {
			RequestDispatcher rd = request.getRequestDispatcher("OrgTable.jsp");
		rd.forward(request, response);}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("ManagerTable.jsp");
		rd.forward(request, response);}
	}

}
