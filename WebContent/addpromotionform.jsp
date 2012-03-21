<%-- 
    Document   : add Promotion Form
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Promotion Form</title>
</head>
<body>

<font size="5">Add Promotion Form</font><br />
<br />
  
  <form action="addpromotionform" method="post">
    Code Product: <input type="text" name="codeProduct" /><br /> 
    Discounted Price: <input type="text" name="discountedPrice" /><br /> 
    Promotion Code: <input type="text" name="promotionCode" /><br /> 
    <br/> 
    <input type="submit" value="Add Promotion" />       
  </form>     
  <form name="return" action="menupromotion.jsp" method='post'>
    <input type='submit' value='Return Menu Promotion'/>
  </form>
    <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>
  
</body>
</html>