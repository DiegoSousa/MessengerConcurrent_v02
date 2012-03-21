<%-- 
    Document   : Show List Products Purchased By Customer
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Products Purchased by Customer</title>
</head>
<body>

<font size="5">List Products Purchased</font><br />
  <br />  
<font size="5">Client: ${Client.name}</font><br />
<br />  
  <table border="1">
    <tr>      
      <td>Product</td>
      <td>Code</td>
      <td>Price</td>
      <td>Quantity</td>      
    </tr>

    <c:forEach var="item" items="${ListProduct}">
      <tr>        
        <td><c:out value="${item.name}" /></td>
        <td><c:out value="${item.code}" /></td>
        <td><c:out value="${item.price}" /></td>
        <td><c:out value="${item.quantity}" /></td>             
      </tr>
      
    </c:forEach>
  </table>
  <br />
  <form name="return" action="menupurchasedofproducts.jsp" method='post'>
    <input type='submit' value='Return to Menu Purchased' />
  </form>

  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form> 


</body>
</html>