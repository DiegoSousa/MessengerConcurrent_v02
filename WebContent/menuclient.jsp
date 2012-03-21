<%-- 
    Document   : Menu Client
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Client</title>
</head>
<body>

<h2>Menu Client</h2>

  <ul class="menu">
    <li><a href="addclientform.jsp">Add Client</a></li>
    <li><a href="editclientform.jsp">Edit Client</a></li>
    <li><a href="removeclient.jsp">Remover Client</a></li>
    <li><a href="searchclient.jsp">Search Client</a></li>
    <li><a href="listofclient">List of Client</a></li>        
  </ul>
<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return to Main'/>
  </form>
</body>
</html>