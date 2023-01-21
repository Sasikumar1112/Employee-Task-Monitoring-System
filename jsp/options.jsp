<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Options</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>
<br>
<div class="center">
<h1>Options</h1>

<%System.out.println("At options:"+session.getAttribute("email"));
response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>
<form  action="OrgCreate.jsp" method="post">
    <input type="submit" value="Purchase and Create Organization">
</form>
<br>
<form  action="OrgJoin.jsp" method="post">
    <input type="submit" value="Join Organization">
  </form>
  <br>
  <!-- Log out -->
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
        </form>
        </div>
</body>
</html>