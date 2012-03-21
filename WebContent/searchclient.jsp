<%-- 
    Document   : Search Client
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Client</title>
</head>
<body>

<font size="5">Search Client</font><br />
<br /> 
  
  <form action="searchclient" method="post">
  Mail: <input type="text" name="mail" /><br />
  <br />
    <input type="submit" value="Get Client" />
  </form>
  
<form name="return" action="menuclient.jsp" method='post'>
    <input type='submit' value='Return Menu Client'/>
</form>

<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
</form>

</body>
</html>