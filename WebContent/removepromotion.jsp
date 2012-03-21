<%-- 
    Document   : Remove Promotion
    Created on : Mar 15, 2012
    Author     : diego@diegosousa.com, www.diegosousa.com
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remove Promotion</title>
</head>
<body>

  <font size="5">Remove Promotion</font><br />
  <br />  
  <form action="removepromotion" method="post">
    Promotion Code: <input type="text" name="promotionCode" /><br />     
   <br />    
    <input type="submit" value="Remove Promotion" />        
  </form>       
  <form name="return" action="menupromotion.jsp" method='post'>
    <input type='submit' value='Return Menu promotion'/>
  </form>
  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>
  
</body>
</html>