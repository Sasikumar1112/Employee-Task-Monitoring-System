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
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
					//get Parameter
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email = request.getParameter("email");
        String password = request.getParameter("password");
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
							//SignUp 
		try {
			Connection conn=DriverManager.getConnection(url,uname,pw);
			String query2 = "insert into employee values(?,?,?,?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,email);
			st2.setString(2,firstname);
			st2.setString(3,lastname);
			st2.setString(4,password);
			st2.executeUpdate();
			st2.close();
			conn.close();
			HttpSession session=request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("name", firstname+" "+lastname);
			RequestDispatcher rd = request.getRequestDispatcher("options.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("\n At Email already exist redirecting");
			request.setAttribute("invalid", "Email already exist ");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
	}

}
