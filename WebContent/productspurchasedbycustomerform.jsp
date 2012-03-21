<%-- 
    Document   : Product Purchased by Customer Form
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Purchased by customer</title>
</head>
<body>

  <font size="5">Product Purchased Form</font><br />
  <br />  
  <form action="productpurchasedbycustomer" method="post">
    Mail Client: <input type="text" name="mail" /><br />     
    <br />    
    <input type="submit" value="ok" />        
  </form>       
  <form name="return" action="menupurchasedofproducts.jsp" method='post'>
    <input type='submit' value='Return Menu Purchase'/>
  </form>
  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>
  
</body>
</html>