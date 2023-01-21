<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Team Lead</title>
</head>
<body>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>
<%
String project_id=(String)session.getAttribute("project_id");
String project_name=(String)session.getAttribute("project_name");
System.out.println("Project id at team leader:"+project_id);%>
<div align="center">
<br>
<h1 align="center">Team Leader of:<%=project_name %></h1>
<form action="CreateTask" method="post">
<input type="text" name="task_name">
    <input type="submit" value="Create new Task">
  </form>
  </div>
 <%
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");
%>
  <!-- Available for me, available in project -->

<%try{
	System.out.println("At Team Lead block");
	//get par
	String email=(String)session.getAttribute("email");
	String org_id=(String)session.getAttribute("org_id");
	Connection conn=DriverManager.getConnection(url,uname,pw);
				//inside project
			//take project ids from handles
			%>

<%
	String query1="select * from tasks1 where project_id="+project_id;
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);%>
				<h1 align="center">Tasks in <%=project_name %></h1>
	<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Task</b></td>
<td><b>Deadline</b></td>
<td><b>Status</b></td>
<td><b>Options</b></td></tr>
	<%while(rs1.next()){
		String task_id=rs1.getString("task_id");
		String task_name=rs1.getString("task_name");
		String datet=rs1.getString("deadline_t");
		String status=rs1.getString("status");
		if(datet==null)
			datet="not set yet";
		if(status==null)
			status="Incomplete";
	%>
	<tr>
	<td><%=task_name %></td>
	<td><%=datet %></td>
	<td><%=status %></td>
	<td>
		<form  action="ManageTaskServlet" method="post">
		<input type="hidden" name="project_id" value=<%=project_id%>>
		<input type="hidden" name="project_name" value=<%=project_name%>>
		  <input type="hidden" name="task_id" value=<%=task_id%>>
		   <input type="hidden" name="task_name" value=<%=task_name%>>
		   <input type="hidden" name="home" value="manage">
            <input  type="submit"  value="Manage">
        </form>
        </td>
        </tr>
	<%}%>
	</table>
	<br>
	<br>

	<%
	String sort=";";
	if (request.getAttribute("sort") != null) {
		sort = (String)request.getAttribute("sort");
      }
	String query="select * from assigned where email='"+email+"' and project_id="+project_id;
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	%>
		
			<!-- Get His Tasks -->
			<br>
			<h1 align="center">Your Tasks</h1>

            <br>
	<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Task</b></td>
<td><b>Deadline</b></td>
<td><b>Status</b></td>
<td><b>Options</b></td>
</tr>
	<%try{while(rs.next()){
	String task_idr=rs.getString("task_id");
	String query2="select * from tasks1 where task_id="+task_idr+" and project_id="+project_id;
	Statement st2=conn.createStatement();
	System.out.println("Query: "+query2);
	ResultSet rs2=st2.executeQuery(query2);
	while(rs2.next()){
	String task_id2=rs2.getString("task_id");
	String task_name=rs2.getString("task_name");
	String datet=rs2.getString("deadline_t");
	String status=rs2.getString("status");
	if(datet==null)
			datet="not set yet";
	if(status==null)
			status="Incomplete";%>
		<tr>
	<td><%=task_name %></td>
	<td><%=datet %></td><td><%=status %></td>
	<td><%if(status!="Completed"){ %><form  action="complete" method="post">
		<input type="hidden" name="project_id" value=<%=project_id%>>
		<input type="hidden" name="project_name" value=<%=project_name%>>
		  <input type="hidden" name="task_id" value=<%=task_id2%>>
		   <input type="hidden" name="task_name" value=<%=task_name%>>
		   <input type="hidden" name="home" value="lead">
            <input  type="submit"  value="Mark as Complete">
        </form></td><%}else{%><td></td><%} %></tr>
	<%}
	}
	
	%>
	
	</table><%
	}catch(Exception e){%>
	<tr align="center"><td>No Tasks Found</td></tr></table>
	<%
	conn.close();
}
}
catch (Exception e) {
	%><tr align="center"><td>No Tasks Found</td></tr></table><%
e.printStackTrace();
}
finally
{
    System.out.println("finally block executed");
}
%>
<br>
<div align="center">
<%String role =(String)session.getAttribute("role");
if(role.equals("Manager")){ %>
<form  action="Projects.jsp" method="post">
            <input  type="submit" value="Back to Projects Page">
            </form>
            <%}else{ %>
            <form  action="EmployeePro.jsp" method="post">
            <input  type="submit" value="Back to Projects Page">
            </form><%} %>
            <br>
  <form  action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
            </form>
            <br>
            </div>
</body>
</html>