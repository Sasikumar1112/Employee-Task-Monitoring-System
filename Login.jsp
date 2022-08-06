<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="loginstyle.css">
</head>
<body>
<%System.out.println("At log in page:"+session.getAttribute("email"));%>
    <div class="center">
        <h1>Login Page</h1>
        <form  action="LoginServlet" method="post">
            <% String invalid=null;
       %>
	<% if (request.getAttribute("invalid") != null) {
		invalid = (String)request.getAttribute("invalid");
       		out.println("<h2><b>"+invalid + "</b></h2>");
      }  %>
            <div class="txt-field">
            <p><input type="email" placeholder="Email" name="email" required></p>
            <p><input type="password" placeholder="Password" name="password" required></p>
        </div>
            <p><input type="submit" value="Log in"></p>
        </form>
        <h3>New User?</h3>
        <form action="Signup.jsp" method="post">
            <input  type="submit" value="Sign Up">
        </form>
        </div>
</body>
</html>