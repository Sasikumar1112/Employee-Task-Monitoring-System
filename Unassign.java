package com.etm;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class Unsign
 */
@WebServlet("/Unassign")
public class Unassign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unassign() {
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
		String project_id=(String)session.getAttribute("project_id");
		String task_id=(String)session.getAttribute("task_id");
		String redir=request.getParameter("redir");
		System.out.println("At unassign set java");
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
			String query2 = "delete from assigned where email=(?) and project_id=(?) and task_id=(?)";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,email);
			st2.setInt(2,Integer.parseInt(project_id));
			st2.setInt(3,Integer.parseInt(task_id));
			st2.executeUpdate();
			System.out.println(st2);
			st2.close();
			conn.close();
			if(redir.equals("create"))
				response.sendRedirect("Createtask.jsp");
			else
				response.sendRedirect("ManageTask.jsp");
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
