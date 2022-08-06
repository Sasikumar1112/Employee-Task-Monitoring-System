<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Org Table</title>
</head>
<body>

</body>
</html>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>
<%
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");
%>
<html>
<head>
 <link rel="stylesheet" href="Table.css">

<head>
<title>View organization Members</title>
<body>
<h1 align="center"><%=session.getAttribute("org_name") %></h1>
<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Name</b></td>
<td><b>Email</b></td>
<td><b>Role</b></td>
<td><b>Options</b></td>
</tr>
<%
String pagename="org";
try{ 
	System.out.println("At Org block");
				//get par
	String email=(String)session.getAttribute("email");
	String org_id=(String)session.getAttribute("org_id");
	Connection conn=DriverManager.getConnection(url,uname,pw);
					//tame email id in works_in
	String query1="select * from works_in where org_id="+"'"+org_id+"' order by role desc;";
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);
	while(rs1.next()){
	String emailr=rs1.getString("email");
	String roler=rs1.getString("role");
						//first name
	String query="select * from employee where email="+"'"+emailr+"' ";
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	rs.next();
%>
	<tr bgcolor="#DEB887" style="color:#120708">
		
		<td ><%=rs.getString(2)%> <%=rs.getString(3)%></td>
		<td><%=emailr%></td>
		<td><%=roler %></td>
<%
		String msg="Change";
		if(roler.equals("Approved"))
	 		msg="Assign role";
		if(roler.equals("At Waiting list")){
			 msg="Add";
			 %>
			 <td><form action="Approve" method="post">
					<input type="hidden" name="email" value=<%=emailr%>>
					<input type="hidden" name="page" value=<%=pagename%>>
					<input	type="submit" value="Approve">
				</form>
				<form action="Delete" method="post">
					<input type="hidden" name="email" value=<%=emailr%>>
					<input type="hidden" name="page" value=<%=pagename%>>
					<input type="submit" value="Refuse">
				</form></td>				
			</tr>
		<%}

		else if(roler.equals("Super Admin"))
		{
		%><td><b>(Me)</b></td></tr><%
		}
		else{%>
			<td><form action="RoleChange" method="post">
			      <label for="role"></label>
			  <select name="role" id="Role" >
			    <option value="Employee">Employee</option>
			    <option value="Manager">Manager</option>
			  </select>
			  <input type="hidden" name="email" value=<%=emailr%>>
			  <input type="hidden" name="page" value=<%=pagename%>>
			    <input type="submit" value=<%=msg%>>
			</form>
			<form action="Delete" method="post">
				<input type="hidden" name="email" value=<%=emailr%>>
				<input type="hidden" name="page" value=<%=pagename%>>
				<input type="submit" value="Remove">
			</form></td>
			</tr>
			
	<%}
	
	}
	conn.close();
	
} catch (Exception e) {
e.printStackTrace();
}
finally
{
    System.out.println("finally block executed");
}
%>
</table>
<div align="center">
<br>
<form  action="SuperAdmin.jsp" method="post">
            <input  type="submit" value="Back to home">
        </form>
        <br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
        </form>
        <br>
        <br>
</div>
</body>
</html>