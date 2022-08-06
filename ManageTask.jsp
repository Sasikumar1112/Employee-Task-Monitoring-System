<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Task</title>
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
String task_name=(String)session.getAttribute("task_name");
String task_id=(String)session.getAttribute("task_id");
String project_id=(String)session.getAttribute("project_id");
System.out.println("project_id:"+project_id);
%>
<h1 align="center">Project Name:<%=session.getAttribute("project_name")%></h1>
<h2 align="center">Task Name:<%=task_name%></h2>
<%
try{ 
	System.out.println("At Manage Task block");
	Connection conn=DriverManager.getConnection(url,uname,pw);
			//take deadline and project id
	String query1="select * from tasks1 where task_name="+"'"+task_name+"';";
	Statement st1=conn.createStatement();
	ResultSet rs1=st1.executeQuery(query1);
	System.out.println("Query: "+query1);
	rs1.next();
	String date=rs1.getString("deadline_t");
	
				///project dead
	String query2="select * from projects1 where project_id="+project_id;
	Statement st2=conn.createStatement();
	ResultSet rs2=st2.executeQuery(query2);
	System.out.println("Query: "+query2);
	rs2.next();
	String datep=rs2.getString("deadline");
	String msg="set";%> <h2 align="left">Project Deadline: <%=datep %></h2><%
	if(date!=null){
		msg="change";
	%>
	
	<h2 align="left">Deadline: <%=date %></h2>
				<!-- Deadline show-->
	<%}
	String invalid=null;
	if (request.getAttribute("invalid") != null) {
		invalid = (String)request.getAttribute("invalid");
       		out.println("<h4>"+invalid + "<h4>");
      }%>

	<div align="left">
<form  action="FixDeadlineTask" method="post">
<ul>Deadline: </ul>
<ul><input type="date" id="appt" name="date"> 
        <input type="hidden" name="redir" value="manage"></ul>

<ul><input type="submit" value=<%=msg %>></ul>
        </form></div>
	
	        <!-- Table -->
<table align="center" cellpadding="20" cellspacing="5" border="1">
<tr>
</tr>
<tr bgcolor="#A52A2A">
<td><b>Name</b></td>
<td><b>Email</b></td>
<td><b>Status</b></td>
<td><b>Options</b></td>
</tr>

<%
	String query="select * from handles where project_id="+project_id;
	Statement st=conn.createStatement();
	ResultSet rs=st.executeQuery(query);
	System.out.println("Query: "+query);
	while(rs.next()){ 
	String emailr=rs.getString("email");
	String project_idr=rs.getString("project_id");
						//first name and last name
	String query4="select * from employee where email="+"'"+emailr+"' ";
	Statement st4=conn.createStatement();
	ResultSet rs4=st4.executeQuery(query4);
	System.out.println("Query: "+query4);
	rs4.next();
	%>
		<tr bgcolor="#DEB887" style="color:#120708">
		<td ><%=rs4.getString("first_name")%> <%=rs4.getString("last_name")%></td>
		<td><%=emailr%></td>
	<%String query3="select * from assigned where email="+"'"+emailr+"' and project_id='"+project_id+"' and task_id="+task_id;
	Statement st3=conn.createStatement();
	ResultSet rs3=st3.executeQuery(query3);
	System.out.println("Query: "+query3);
	try{rs3.next();
		if(rs3.getString(1)!=null)
			%> <td>Assigned </td>
			<td><form action="Unassign" method="post">
				<input type="hidden" name="email" value=<%=emailr%>>
				<input type="hidden" name="email" value=<%=project_idr %>>
				<input type="hidden" name="redir" value="manage">
				<input type="submit" value="Remove Assign">
			</form></td>
			</tr><%
		}
	catch (Exception e) {
		%><td>Yet to Assign</td>
<td><form action="AssignTask" method="post">
				<input type="hidden" name="email" value=<%=emailr%>>
				<input type="hidden" name="email" value=<%=project_idr %>>
				<input type="hidden" name="redir" value="mange">
				<input type="submit" value="Assign">
			</form>
<br></td>
			</tr>
		<%}
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
<%String role=(String)session.getAttribute("role");
if(role.equals("Super Admin")){ %>
<form  action="SATasks.jsp" method="post">
            <input  type="submit" value="Back to Tasks">
        </form>
        <%}else{%>
<form  action="TeamLeader.jsp" method="post">
<input  type="submit" value="Back to Tasks">
</form>
<%}%>
        <br>
  <form class="logout" action="LogOutServlet" method="post">
            <input  type="submit" value="Log out">
        </form>
        <br>
</body>
</html>