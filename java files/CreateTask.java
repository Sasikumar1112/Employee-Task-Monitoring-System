package com.etm;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
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
 * Servlet implementation class CreateTask
 */
@WebServlet("/CreateTask")

public class CreateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTask() {
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
		String task_name = request.getParameter("task_name");
		HttpSession session=request.getSession();
		String project_id = (String)session.getAttribute("project_id");
		String org_id = (String)session.getAttribute("org_id");
		System.out.println("task name:"+task_name);
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
			String query2 = "insert into tasks1(task_name,project_id,org_id) values(?,?,?);";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1,task_name);
			st2.setInt(2,Integer.parseInt(project_id));
			st2.setInt(3,Integer.parseInt(org_id));
			st2.executeUpdate();
			st2.close();
			String query="select * from tasks1 where project_id="+"'"+project_id+"' and task_name='"+task_name+"'";
			java.sql.Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			String task_id=rs.getString("task_id");
			session.setAttribute("task_id",task_id);
			session.setAttribute("task_name",task_name);
			String name=(String)session.getAttribute("name");
			SMS(task_name,name);
			response.sendRedirect("Createtask.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void SMS(String msg,String by) {
		  // Find your Account Sid and Token at twilio.com/console 
	    final String ACCOUNT_SID = "ACb118528ae00838c736b006cdee619264"; 
	    final String AUTH_TOKEN = "670f04bd272db448384f39fa6866a81e"; 
	 
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("+917904570101"), new com.twilio.type.PhoneNumber("+19107883387"),
	                "\nNew Task has been Assigned \n Task Name: "+msg+"\n Assigned by: "+by)      
	            .create(); 
	        System.out.println(message.getSid()); 
		
	}

}
