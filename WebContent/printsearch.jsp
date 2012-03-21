<%-- 
    Document   : Print Search
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Search</title>
</head>
<body>
<%=request.getAttribute("Object").toString() %><br />

<br />

<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
</form>
</body>
</html>