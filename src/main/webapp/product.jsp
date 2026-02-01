<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.fashioncart.dto.Product" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Product product =(Product)request.getAttribute("productDetails");%>
<h1><%=product.getName() %></h1>
</body>
</html>