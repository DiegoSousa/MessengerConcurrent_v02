<%-- 
    Document   : Show List of Promotion
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
<title>Show List Of Promotion</title>
</head>
<body>

<font size="5">List Of Promotion</font><br />
  <br />  

  <table border="1">
    <tr>
      <td>Name Product</td>
      <td>Discounted Price</td>
      <td>Promotion Code</td>      
    </tr>

    <c:forEach var="item" items="${ListPromotion}">
      <tr>
        <td><c:out value="${item.product.name}" /></td> 
        <td><c:out value="${item.discountedPrice}" /></td>
        <td><c:out value="${item.promotionCode}" /></td>        
      </tr>

    </c:forEach>
  </table>
  <br />
  <form name="return" action="menupromotion.jsp" method='post'>
    <input type='submit' value='Return to Menu Promotion' />
  </form>

  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>

</body>
</html>