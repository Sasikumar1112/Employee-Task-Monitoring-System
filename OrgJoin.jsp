<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Join</title>
    <link rel="stylesheet" href="loginstyle.css">
</head>
<body>
<div class="center">
        <h1>Join Page</h1>
<form action="OrgJoinServlet" method="post">
   <% String invalid=null; 
       %>
	<% if (request.getAttribute("invalid") != null) {
		invalid = (String)request.getAttribute("invalid");
       		out.println("<br>"+invalid + "<br>");
      }  %>
      <div class="txt-field">
    <p><input type="text" placeholder="Organization Code" name="passcode" required></p>
    </div>
    <p><input type="submit" value="Submit"></p>
</form>
</div>
</body>
</html>