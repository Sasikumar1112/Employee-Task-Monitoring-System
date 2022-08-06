<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>

<%-- <%
response.setHeader("Cache-control", "no-cache,no-store,must-revalidate");
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%> --%>

<div class="center">
<h1>Create Organization Page</h1>
<form action="OrgCreateServlet" method="post">
<div class="txt-field">
   <p><input type="text" placeholder="Organization Name" name="org" required></p>

    </div>
        <p><input type="submit" value="Submit"></p>
</form>
<form  action="options.jsp" method="post">
            <input  type="submit" value="Back">
            </form>
            <br>
    <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
            </form>
</div>
</body>
</html>