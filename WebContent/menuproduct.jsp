<%-- 
    Document   : menu Product
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Product</title>
</head>
<body>
<h2>Menu Product</h2>

  <ul class="menu">
    <li><a href="addproductform.jsp">Add Product</a></li>
    <li><a href="editproductform.jsp">Edit Product</a></li>
    <li><a href="removeproduct.jsp">Remover Product</a></li>       
    <li><a href="searchproduct.jsp">Search Product</a></li>
    <li><a href="listofproduct">List of Product</a></li>
  </ul>
<form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return to Main'/>
  </form>
</body>
</html>