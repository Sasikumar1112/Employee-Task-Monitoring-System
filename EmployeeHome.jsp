<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Home</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>
<%String name=(String)session.getAttribute("name"); %>
<h1 align="center">Welcome <%=name %>!</h1>
<%
response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>
<br>
<div class="center">
<h1>Employee Home</h1>
<br>
<form  action="Join.jsp" method="post">
    <input type="submit" value="Join another Organization">
  </form>
  <br>
<form  action="EmployeePro.jsp" method="post">
    <input type="submit" value="View Projects and Tasks">
  </form>
  <br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
        </form>
        </div>
</body>
</html>