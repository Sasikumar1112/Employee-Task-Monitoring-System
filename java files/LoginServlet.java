package com.etm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String url="jdbc:postgresql://localhost:5432/postgres";
		String uname="postgres";
		String pw="0";
				//get par
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session=request.getSession();
        session.setAttribute("email", email);
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Connection conn=DriverManager.getConnection(url,uname,pw);
			String query="select * from employee where email="+"'"+email+"';";
			java.sql.Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			System.out.println("Log in"+query);
			rs.next();
			String f=rs.getString("first_name");
			System.out.println(f);
			String l=rs.getString("last_name");
			String name=f+" "+l;
			session.setAttribute("name", name);
			String password_r=rs.getString("password");
						//check password
			if(password.equals(password_r)) {
								//get org count
				String query2="select count(org_id) from works_in where email="+"'"+email+"';";
				java.sql.Statement st2=conn.createStatement();
				ResultSet rs2=st2.executeQuery(query2);
				try{rs2.next();
				int n=rs2.getInt(1);
				if(n>1) {
							//more than one org
					RequestDispatcher rd = request.getRequestDispatcher("MultiOrg.jsp");
					rd.forward(request, response);
				}
				else {
					String query3="select * from works_in where email="+"'"+email+"';";
					java.sql.Statement st3=conn.createStatement();
					ResultSet rs3=st3.executeQuery(query3);
					rs3.next();
					// set org_id
					String org_id=rs3.getString("org_id");
					session.setAttribute("org_id",org_id);
							//3 roles are there 
					String role=rs3.getString("role");
					session.setAttribute("role",role);
					if(role.equals("Super Admin"))
						response.sendRedirect("SuperAdmin.jsp");
					else if(role.equals("Manager"))
						response.sendRedirect("Manager.jsp");
					else if(role.equals("Employee"))
						response.sendRedirect("EmployeeHome.jsp");
					else
						response.sendRedirect("options.jsp");
				}
				}catch(SQLException e) {
					response.sendRedirect("options.jsp");
				}
					
				
			}
			else {
				System.out.println("\n Wrong password");
				request.setAttribute("invalid", "Wrong Password");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("\n At Email dont exist redirecting");
			request.setAttribute("invalid", "Email doesn't exist ");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		
	}

}
