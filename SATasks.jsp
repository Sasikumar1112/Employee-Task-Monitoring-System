<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SA Tasks</title>
</head>
<body>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>
<%
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");
%>
<h1 align="center"><%=session.getAttribute("org_name") %></h1>
<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Task Name</b></td>
<td><b>Deadline</b></td>
<td><b>Status</b></td>
<td><b>Options</b></td>
</tr>
<%
try{ 
	System.out.println("At SA task block");
				//get par
	String org_id=(String)session.getAttribute("org_id");
	Connection conn=DriverManager.getConnection(url,uname,pw);
					//all project info
	String query1="select * from tasks1 ;";
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);
	while(rs1.next()){
	String task_id=rs1.getString("task_id");
	String project_id=rs1.getString("project_id");
	String task_name=rs1.getString("task_name");
	String deadline=rs1.getString("deadline_t");
	String status=rs1.getString("status");
	//get project name
	String query="select * from projects1 where project_id="+project_id;
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	rs.next();
	String project_name=rs.getString("project_name");
	if(status==null)
		status="Incomplete";
	if(deadline==null)
		deadline="yet to set";
	
	%>
		<tr bgcolor="#DEB887" style="color:#120708">
		
		<td ><%=task_name%></td>
		<td><%=deadline%></td>
		<td><%=status%></td>

	<td><form  action="ManageTaskServlet" method="post">
	<input  type="hidden" name="task_id" value=<%=task_id %>>
	<input  type="hidden" name="project_id" value=<%=project_id %>>
	<input  type="hidden" name="project_name" value=<%=project_name %>>
	<input  type="hidden" name="task_name" value=<%=task_name %>>
	<input  type="hidden" name="home" value="SA">
            <input  type="submit" value="Manage Task">
        </form></td></tr>
	<%}conn.close();
}catch(Exception e){%>
			<td></td>
		<%}
	%>
</table>
<div align="center">
<br>
<form  action="SuperAdmin.jsp" method="post">
            <input  type="submit" value="Back to home">
        </form>
        <br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
        </form>
        <br>
        <br>
</div>
</body>
</html>