<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>
<div class="center">
        <h1>SignUp Page</h1>
        
<form action="SignupServlet" method="post">
<div class="txt-field">
    <p><input type="text" placeholder="First name" name="firstname" required></p>
    <p><input type="text" placeholder="Last name" name="lastname" required></p>
    <p><input type="password" placeholder="Password" name="password" required></p>
	<p><input type="email" placeholder="Email" name="email" required></p>
	</div>
    <p><input type="submit" value="Sign Up"></p>
    
</form>
</div>
</body>
</html>