<%-- 
    Document   : Search Promotion
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Promotion</title>
</head>
<body>

<font size="5">Search Promotion</font><br />
<br /> 

<form action="searchpromotion" method="post">
  Code Promotion: <input type="text" name="code" /><br />
  <br />
    <input type="submit" value="Get Promotion" />
</form> 

<form name="return" action="menupromotion.jsp" method='post'>
    <input type='submit' value='Return Menu Promotion'/>
</form>
  
<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
</form>
  

</body>
</html>