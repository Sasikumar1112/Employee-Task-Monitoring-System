<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.sql.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Multi Org</title>
<link rel="stylesheet" href="loginstyle.css">
</head>
<body>
	

<%
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");

try{ 
	System.out.println("At Multi-Org block");
	//get email from string
	String email=(String)session.getAttribute("email");
	Connection conn=DriverManager.getConnection(url,uname,pw);
	//Org names
	String query="select * from works_in where email="+"'"+email+"';";
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
%><div class="center"><h1>Choose Organization</h1><%
	while(rs.next()){
		String org_id=rs.getString("org_id");
		String role=rs.getString("role");
		String query1="select * from org where org_id="+org_id;
		Statement st1=conn.createStatement();
		ResultSet rs1=st1.executeQuery(query);
		rs1.next();
		String org_name=rs1.getString("org_name");
		String msg=org_name+"  "+role;
%>				
				<form action="ChooseLogin" method="post">
				  <input type="hidden" name="org_id" value=<%=org_id%>>
				  <input type="hidden" name="org_name" value=<%=org_name%>>
				  <input type="hidden" name="role" value=<%=role%>>
				    <p><input type="submit" value=<%=msg%>></p>
				</form>
			<%}%>
			</div>
<%
	conn.close();
	st.close();
} catch (Exception e) {
e.printStackTrace();
}
finally
{
    System.out.println("Many Org finally block executed");
}
%>
</body>
</html>