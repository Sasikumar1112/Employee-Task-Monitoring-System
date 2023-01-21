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
 * Servlet implementation class OrgCreateServlet
 */
@WebServlet("/OrgCreateServlet")
public class OrgCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrgCreateServlet() {
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
					//get parameter
		String orgName = request.getParameter("org");
		HttpSession session=request.getSession();
        String email=(String) session.getAttribute("email");
        
        			//passcode
        String AlphaNumericString = "ABCDEFGHJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijkmnopqrstuvxyz";
			int n=6;
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
			int index= (int)(AlphaNumericString.length()* Math.random());
			sb.append(AlphaNumericString.charAt(index));	
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
							//Org Create 
		try {
			Connection conn=DriverManager.getConnection(url,uname,pw);
			String query2 = "insert into org(org_name,passcode) values(?,?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,orgName);
			st2.setString(2,passcode);
			st2.executeUpdate();
			st2.close();
			
							//get org id
			String query="select MAX(org_id) from org ";
			java.sql.Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			String org_id=rs.getString(1);
			st.close();
							//insert in works with
			String query3 = "insert into works_in(email,org_id,role) values(?,?,?);";
			PreparedStatement st3 = conn.prepareStatement(query3);
			st3.setString(1,email);
			st3.setInt(2,Integer.parseInt(org_id));
			st3.setString(3,"Super Admin");
			st3.executeUpdate();
			st3.close();
							
			session.setAttribute("org_id",org_id);
			session.setAttribute("email", email);
			conn.close();
			response.sendRedirect("SuperAdmin.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
           
	}

