<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Projects</title>
<link rel="stylesheet" href="loginstyle.css">
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
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");
%>
<div class="center">
<h1 align="center">Project Page</h1>
<%try{
	System.out.println("At emp Project block");
	//get par
	String email=(String)session.getAttribute("email");
	String org_id=(String)session.getAttribute("org_id");
	Connection conn=DriverManager.getConnection(url,uname,pw);
			//take project ids from handles
	String query1="select * from handles where email="+"'"+email+"' order by role desc;";
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);
	while(rs1.next()){
	String project_id=rs1.getString("project_id");
	String role=rs1.getString("role");//to send redirect
				//Display its deadline and name 
	String query="select * from projects1 where project_id="+"'"+project_id+"' ";
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	rs.next(); 
	String project_name=rs.getString("project_name");
	String date=rs.getString("deadline");
	String status=rs.getString("status");
	if(status==null)
		status="yet to complete";
	if(date==null)
		date="not set";
	if(role!=null){
		if(role.equals("Team Lead")){%>
	<form  action="TeamLeaderServlet" method="post">
	  <input type="hidden" name="project_id" value=<%=project_id%>>
            <input  type="submit" name="project_name" value=<%=project_name%>>
        </form><%}
		else{%>
		<form  action="TeamMemberServlet" method="post">
		  <input type="hidden" name="project_id" value=<%=project_id%>>
            <input  type="submit" name="project_name" value=<%=project_name%>>
        </form><%} %>
        <p align="center">Deadline: <%=date%>&emsp;Status: <%=status %></p>
		
	<%}
	}
	conn.close();
} catch (Exception e) {
e.printStackTrace();
}
finally
{
    System.out.println("finally block executed");
}%><form action="EmployeeHome.jsp" method="post">
            <input  type="submit" value="Back">
        </form>
</div>
</body>
</html>