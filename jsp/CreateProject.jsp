<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Project</title>
</head>
<body>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%if (session.getAttribute("email")==null)
	response.sendRedirect("Login.jsp");
	%>
<%
String url="jdbc:postgresql://localhost:5432/postgres";
String uname="postgres";
String pw="0";
Class.forName("org.postgresql.Driver");
String project_id=(String)session.getAttribute("project_id");
String project_name=(String)session.getAttribute("project_name");
String org_id=(String)session.getAttribute("org_id");
%>
<h1 align="center"><%=project_name%></h1>
<%
try{ 
	System.out.println("At Create Project block");
	Connection conn=DriverManager.getConnection(url,uname,pw);
			//take deadline and project id
	String query1="select * from projects1 where project_id="+project_id;
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);
	rs1.next();
	session.setAttribute("project_id",project_id);
	String date=rs1.getString("deadline");
	String msg="set";
	if(date!=null){
		msg="change";
	%>
	<h2 align="left">Deadline: <%=date %></h2>
				<!-- Deadline show-->
	<%}%>
	<div align="left">
<form  action="FixDeadline" method="post">
<ul>Deadline: </ul>
<ul><input type="date" id="appt" name="date"> 
        <input type="hidden" name="redir" value="project"></ul>

<ul><input type="submit" value=<%=msg %>>
 <input type="hidden" name="home" value="CR"></ul>
        </form></div>
	
	        <!-- Table -->
<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>
</tr>
<tr bgcolor="#A52A2A">
<td><b>Name</b></td>
<td><b>Email</b></td>
<td><b>Project Role</b></td>
<td><b>Options</b></td>
</tr>

<%
session.setAttribute("project_name",project_name);
String msgop="Change role";
	String query="select * from works_in where org_id="+"'"+org_id+"' and role<>'Super Admin'";
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	while(rs.next()){ 
	String emailr=rs.getString("email");
						//first name and last name
	String query2="select * from employee where email="+"'"+emailr+"' ";
	Statement st2=conn.createStatement();
	ResultSet rs2=st2.executeQuery(query2);
	System.out.println("Query: "+query2);
	rs2.next();
	%>
	<tr bgcolor="#DEB887" style="color:#120708">
		
		<td ><%=rs2.getString("first_name")%> <%=rs2.getString("last_name")%></td>
		<td><%=emailr%></td>
						<%//GEt role
	String role=null;
	String query3="select role from handles where email="+"'"+emailr+"' and project_id="+project_id;
	Statement st3=conn.createStatement();
	ResultSet rs3=st3.executeQuery(query3);
	System.out.println("Query: "+query3);
	try{rs3.next();
	role=rs3.getString("role");}
	catch (Exception e) {
		msgop="Assign role";
		%><td>Yet to Assign<td>
		
		<form action="ProjectRole" method="post">
			      <label for="role"></label>
			  <select name="role" >
			    <option value="Team Member">Team Member</option>
			    <option value="Team Lead">Team Lead</option>
			   
			  </select>
			  <input type="hidden" name="email" value=<%=emailr%>>
			  <input type="hidden" name="home" value="CR">
			    <input type="submit" value=<%=msgop%>>
			    </form>
			    <%
		}			
%>
	
		<%if(role!=null){ %>
		<td><%=role%></td>
		<!-- Display role  at options-->
			 <td>Assigned as <%= role%><br>
			 <form action="ProjectRoleChange" method="post">
			      <label for="role"></label>
			  <select name="role" >
			    <option value="Team Member">Team Member</option>
			    <option value="Team Lead">Team Lead</option>
			  </select>
			  <input type="hidden" name="email" value=<%=emailr%>>
			  <input type="hidden" name="home" value="CR">
			    <input type="submit" value=<%=msgop%>>
			    <%if(role!=null){ %>
			</form>
			<form action="ProDelete" method="post">
				<input type="hidden" name="email" value=<%=emailr%>>
				<input type="submit" value="Remove">
				<input type="hidden" name="home" value="CR">
			</form></td>
			</tr>
		<%}
	}
	}
	conn.close();
}catch (Exception e) {
e.printStackTrace();
}
finally
{
    System.out.println("finally block executed");
}
%>
</table>

<br>
<div align="center">
<br>
<form  action="Projects.jsp" method="post">
            <input  type="submit" value="Back to Projects">
        </form>
        <br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
        </form>
        <br>
</body>
</html>