<%-- 
    Document   : Edit Product Form
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Product Form</title>
</head>
<body>

<font size="5">Edit Product Form</font><br />
<br /> 

<font color="FF0000">* Code can not be changed.</font><br />
<br /> 

<form action="editproductform" method="post">
    Name: <input type="text" name="name" /><br /> 
    Code*: <input type="text" name="code" /><br /> 
    Price: <input type="text" name="price" /><br />     
    Quantity: <input type="text" size="1" name="quantity" /><br />
    <br />         
    <input type="submit" value="Edit Product" />   
  </form>     
  
  <form name="return" action="menuproduct.jsp" method='post'>
    <input type='submit' value='Return Menu Product'/>
  </form>
  
  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
</form>
</body>
</html>