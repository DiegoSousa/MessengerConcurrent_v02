<%-- 
    Document   : Edit Client Form
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Client Form</title>
</head>
<body>

<font size="5">Edit Client Form</font><br />
<br /> 

<font color="FF0000">* Mail can not be changed.</font>

<form action="editclientform" method="post">
<br />
    Name: <input type="text" name="name" /><br /> 
    Phone: <input type="text" name="phone" /><br /> 
    Mail*: <input type="text" name="mail" /><br /> 
    Date Of Birthday:<br /> 
    Day: <input type="text" size="1" name="day" /> 
    Month: <input type="text" size="1" name="month" /> 
    Year: <input type="text" size="1" name="year" /><br /> 
    <br />
    <input type="submit" value="Edit Client" />
 </form>
  
<form name="return" action="menuclient.jsp" method='post'>
  <input type='submit' value='Return Menu Client'/>
</form>

<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
</form>

</body>
</html>