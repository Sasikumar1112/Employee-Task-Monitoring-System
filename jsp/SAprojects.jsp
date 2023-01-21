<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Super Admin Projects</title>
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
<td><b>Project Name</b></td>
<td><b>Deadline</b></td>
<td><b>Status</b></td>
<td><b>Team Lead</b></td>
<td><b>Team Members</b></td>
<td><b>Tasks</b></td>
<td><b>Options</b></td>
</tr>
<%
try{ 
	System.out.println("At SA project block");
				//get par
	String org_id=(String)session.getAttribute("org_id");
	Connection conn=DriverManager.getConnection(url,uname,pw);
					//all project info
	String query1="select * from projects1 where org_id="+org_id;
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);
	while(rs1.next()){
	String project_id=rs1.getString("project_id");
	String project_name=rs1.getString("project_name");
	String deadline=rs1.getString("deadline");
	String status=rs1.getString("status");
	if(status==null)
		status="Incomplete";
	if(deadline==null)
		deadline="yet to set";
	
	%>
		<tr bgcolor="#DEB887" style="color:#120708">
		
		<td ><%=project_name%></td>
		<td><%=deadline%></td>
		<td><%=status%></td>
		<%				//first name
	String query="select * from handles where project_id="+project_id+" order by role asc";
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	rs.next();
	String email=rs.getString("email");
						//name of leader
	String query2="select * from employee where email='"+email+"';";
	Statement st2=conn.createStatement();
	ResultSet rs2=st2.executeQuery(query2);
	System.out.println("Query: "+query2);
	rs2.next();
	%>
	<td ><%=rs2.getString("first_name")%> <%=rs2.getString("last_name")%></td>
	<td >
	<%while(rs.next()){
		email=rs.getString(1);
						//members
	String query3="select * from employee where email='"+email+"';";
	Statement st3=conn.createStatement();
	ResultSet rs3=st3.executeQuery(query3);
	System.out.println("Query: "+query3);
	rs3.next();%>
		<%=rs3.getString("first_name")%> <%=rs3.getString("last_name")%><br>
	<%} %></td>
	<% 
	String query4="select * from tasks1 where project_id='"+project_id+"';";
	Statement st4=conn.createStatement();
	ResultSet rs4=st4.executeQuery(query4);
	System.out.println("Query: "+query4);%><td >
	<%try{while(rs4.next()){%>
		<%=rs4.getString("task_name")%><br>
	<%}%></td>
	<%}catch(Exception e){%>
			<td></td>
		<%}System.out.println("pro id at manage:"+project_id);
	%><td><form  action="ManageProjectServlet" method="post">
	<input  type="hidden" name="project_id" value=<%=project_id %>>
	<input  type="hidden" name="project_name" value=<%=project_name %>>
	<input  type="hidden" name="home" value="SA">
            <input  type="submit" value="Manage">
        </form></td></tr>
<%		
	}
	
	conn.close();
	
} catch (Exception e) {
e.printStackTrace();
}
finally
{
    System.out.println("finally block executed");
}
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