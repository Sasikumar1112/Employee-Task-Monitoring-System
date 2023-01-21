package com.etm;

import java.beans.Statement;
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
 * Servlet implementation class FixDeadlineTask
 */
@WebServlet("/FixDeadlineTask")
public class FixDeadlineTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FixDeadlineTask() {
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
		String date=request.getParameter("date");
		String redir=request.getParameter("date");
		HttpSession session=request.getSession();
		String project_id=(String)session.getAttribute("project_id");
		String task_id=(String)session.getAttribute("task_id");
		
		System.out.println("At pro time set java");
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
			String query1="select * from projects1 where project_id="+"'"+project_id+"';";
			java.sql.Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query1);
			rs.next();
			String datep=rs.getString("deadline");
			if(date.compareTo(datep)>0) {
				System.out.println("\n Deadline is greater than project deadline");
				request.setAttribute("invalid", "Deadline is greater than project deadline");
				RequestDispatcher rd = request.getRequestDispatcher("Createtask.jsp");
				rd.forward(request, response);
			}
			else {
			String query2 = "update tasks1 set deadline_t='"+date+"' where task_id=(?) and project_id=(?)";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setInt(1,Integer.parseInt(task_id));
			st2.setInt(2,Integer.parseInt(project_id));
			st2.executeUpdate();
			System.out.println(query2);
			st2.close();
			conn.close();
			if(redir.equals("create"))
				response.sendRedirect("Createtask.jsp");
			else
				response.sendRedirect("ManageTask.jsp");
			}
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
