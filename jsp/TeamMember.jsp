<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Team Member</title>
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
<h1 align="center">Team Member :<%=project_name %></h1>

 <%
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");
%>
  <!-- Avail for him only -->
<h1 align="center">Tasks in <%=project_name %></h1>
			<div align="center">
<!--   <form  action="Sort" method="post">
  <input  type="hidden" name="leadormem" value="TM">
            <input  type="submit" value="sort"> -->
            </form>
            </div>
            <br>
	<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Task</b></td>
<td><b>Deadline</b></td>
</tr>
	<%
	String email=(String)session.getAttribute("email");
	String org_id=(String)session.getAttribute("org_id");
	Connection conn=DriverManager.getConnection(url,uname,pw);
	String sort=";";
	if (request.getAttribute("sort") != null) {
		sort = (String)request.getAttribute("invalid");
      }
	try{String query="select * from assigned where email='"+email+"' and project_id="+project_id;
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	while(rs.next()){
	String task_idr=rs.getString("task_id");
	String query2="select * from tasks where task_id="+task_idr+" and project_id="+project_id;
	Statement st2=conn.createStatement();
	ResultSet rs2=st2.executeQuery(query2);
	System.out.println("Query: "+query2);
	rs2.next();
	String task_name=rs2.getString("task_name");
	String datet=rs2.getString("deadline_t");
	if(datet==null)
			datet="not set yet";
			%>
		<tr>
	<td><%=task_name %></td>
	<td><%=datet %></td></tr>
	<%}%>
	</table><%
	
	}
	catch(Exception e){%>
	<tr align="center"><td>No Tasks Found</td></tr></table>
	<%
	conn.close();
}
finally
		{
		    System.out.println("finally block executed");
		}
%>
<br>
<%String role=(String)session.getAttribute("role");
if(role.equals("Manager")){ %>
<form  action="Projects.jsp" method="post">
            <input  type="submit" value="Back to Projects Page">
            </form><%}
else{%><form  action="EmployeeHome.jsp" method="post">
            <input  type="submit" value="Back to Employee Page">
            </form>
            <%} %><br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
            </form>
  </div>
</body>
</html>