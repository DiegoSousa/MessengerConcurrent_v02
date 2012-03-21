<%-- 
    Document   : Show List of Client
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="br.ufpb.threadControl.MessengerConcurrent.Entity.Client"%>
<%@page import="java.util.List"%>
<%@page
	import="br.ufpb.threadControl.MessengerConcurrent.Servlet.AddClientServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show List Of Client</title>
</head>
<body>

<font size="5">List Of Client</font><br />
  <br />  

	<table border="1">
		<tr>
			<td>Name</td>
			<td>Mail</td>
			<td>Phone</td>
			<td>Day</td>
			<td>Month</td>
			<td>Year</td>
		</tr>

		<c:forEach var="item" items="${ListClient}">
			<tr>
				<td><c:out value="${item.name}" /></td>
				<td><c:out value="${item.mail}" /></td>
				<td><c:out value="${item.phone}" /></td>
				<td><c:out value="${item.birthday}" /></td>
				<td><c:out value="${item.monthOfBirth}" /></td>
				<td><c:out value="${item.yearOfbirth}" /></td>
			</tr>

		</c:forEach>
	</table>
  <br />
	<form name="return" action="menuclient.jsp" method='post'>
		<input type='submit' value='Return to Menu Client' />
	</form>

  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>

</body>
</html>