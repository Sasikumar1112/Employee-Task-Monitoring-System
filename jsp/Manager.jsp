<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manger</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%String name=(String)session.getAttribute("name"); %>
<h1 align="center">Welcome <%=name %>!</h1>
<%
response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");

String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
String email=(String)session.getAttribute("email");

	try{
		Class.forName("org.postgresql.Driver");
		Connection conn=DriverManager.getConnection(url,uname,pw);
					//get org id
		String query="select org_id from works_in where email="+"'"+email+"';";
		java.sql.Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String org_id=rs.getString("org_id");
		st.close();
					//Set in session
		session.setAttribute("org_id",org_id);
					//get passcode
		String query1="select * from org where org_id="+"'"+org_id+"'";
		Statement st1=conn.createStatement();
		ResultSet rs1=st1.executeQuery(query1);
		System.out.println("Query: "+query1);
		rs1.next();
		String org_name=rs1.getString("org_name");
		session.setAttribute("org_name",org_name);
		String passcode=rs1.getString("passcode");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally
	{
	    System.out.println("finally block executed");
	}

%>
	<div class="center">
<h1><%=session.getAttribute("org_name") %>  MANAGER</h1>
<br>
<form action="ManagerTable.jsp" method="post">
    <input type="submit" value="Manage Members">
  </form>
  <br>
  <form action="Projects.jsp" method="post">
    <input type="submit" value="Projects">
  </form>
  <br>
  <form action="options.jsp" method="post">
    <input type="submit" value="Back to Options Page">
  </form>
  <br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
            </form>
  </div>
</body>
</html>