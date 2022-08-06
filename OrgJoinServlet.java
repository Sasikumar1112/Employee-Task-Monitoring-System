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
 * Servlet implementation class OrgJoinServlet
 */
@WebServlet("/OrgJoinServlet")
public class OrgJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrgJoinServlet() {
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
					//get par
		String passcode = request.getParameter("passcode");
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		
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
						//check passcode exist or not
			String query="select * from org where passcode="+"'"+passcode+"';";
			java.sql.Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			String org_id=rs.getString("org_id");
						//insert in works in
			String query2 = "insert into works_in(org_id,Role,email) values(?,?,?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setInt(1,Integer.parseInt(org_id));
			st2.setString(2,"At Waiting list");
			st2.setString(3, email);
			st2.executeUpdate();
			
			RequestDispatcher rd = request.getRequestDispatcher("Waiting.jsp");
			rd.forward(request, response);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("\n No passcode");
			request.setAttribute("invalid", "Wrong Passcode ");
			RequestDispatcher rd = request.getRequestDispatcher("OrgJoin.jsp");
			rd.forward(request, response);
		}
	}

}
