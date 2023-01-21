<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Waiting</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>

<%
response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>

<div class="center">
        <h1>Request Sent Successfully<br>
        Waiting for Approval</h1>
  <form action="FindHomeServlet" method="post">
    <p><input type="submit" value="Return Home"></p>
</form>
<form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
            </form>
</div>

</body>
</html>