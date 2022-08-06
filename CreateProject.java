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
 * Servlet implementation class CreateProject
 */
@WebServlet("/CreateProject")
public class CreateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProject() {
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
		String project_name = request.getParameter("project_name");
		HttpSession session=request.getSession();
		String org_id=(String) session.getAttribute("org_id");
		System.out.println("Pro name:"+project_name);
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
			String query2 = "insert into projects1(project_name,org_id) values(?,?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,project_name);
			st2.setInt(2,Integer.parseInt(org_id));
			st2.executeUpdate();
			st2.close();
			String query="select MAX(project_id) from projects1";
			java.sql.Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			String project_id=rs.getString(1);
			System.out.println("id by max:"+project_id);
			session.setAttribute("project_id",project_id);
			session.setAttribute("project_name",project_name);
			response.sendRedirect("CreateProject.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
