<%-- 
    Document   : Show List of Product
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show List of Product</title>
</head>
<body>
	
<font size="5">List Of Product</font><br />
<br />  
  <table border="1">
    <tr>
      <td>Name</td>
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
  <form name="return" action="menuproduct.jsp" method='post'>
    <input type='submit' value='Return to Menu Product' />
  </form>

  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>	
	
</body>
</html>