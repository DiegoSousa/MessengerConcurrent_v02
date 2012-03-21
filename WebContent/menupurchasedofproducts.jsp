<%-- 
    Document   : Menu Purchases Of Products
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Purchases Of Products</title>
</head>
<body>

<h2>Purchases Of Products</h2>

  <ul class="menu">
    <li><a href="buyproduct.jsp">Buy Product</a></li>
    <li><a href="productspurchasedbycustomerform.jsp">Search Products Purchased by the Customer</a></li>           
  </ul>
<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return to Main'/>
  </form>
</body>
</html>