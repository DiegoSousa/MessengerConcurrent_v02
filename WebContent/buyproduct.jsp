<%-- 
    Document   : Buy Product
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buy Product</title>
</head>
<body>

  <font size="5">Buy Product</font><br />
  <br />  
  <form action="buyproduct" method="post">
    Mail Client: <input type="text" name="mailClient" /><br /> 
    Code Product: <input type="text" name="codeProduct" /><br /> 
    Quantity Of Products: <input type="text" name="quantityOfProducts" /><br />     
    <br />    
    <input type="submit" value="Buy Product" />        
  </form>       
  <form name="return" action="menupurchasedofproducts.jsp" method='post'>
    <input type='submit' value='Return Menu Shopping'/>
  </form>
  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>
  
</body>
</html>