package com.etm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PasscodeGenerate
 */
@WebServlet("/PasscodeGenerate")
public class PasscodeGenerate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasscodeGenerate() {
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
        String org_id=(String) session.getAttribute("org_id");
        
		String AlphaNumericString = "ABCDEFGHJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijkmnopqrstuvxyz";
		int n=5;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
		// generate a random number between
		// 0 to AlphaNumericString variable length
		int index
		= (int)(AlphaNumericString.length()
			* Math.random());
		// add Character one by one in end of sb
		sb.append(AlphaNumericString
			.charAt(index));	
		}
		String str=sb.toString();
		System.out.println(str);
		String passcode=str;
		
		//set sql connection
        String url="jdbc:postgresql://localhost:5432/postgres";
		String uname="postgres";
		String pw="0";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
							//Org id  
		try {
			Connection conn=DriverManager.getConnection(url,uname,pw);
			
							//update in org
			String query2 = "update org set passcode=(?) where org_id=(?)";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,passcode);
			st2.setInt(2,Integer.parseInt(org_id));
			st2.executeUpdate();
			st2.close();
			conn.close();
			response.sendRedirect("SuperAdmin.jsp");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
