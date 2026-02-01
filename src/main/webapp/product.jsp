<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fashioncart.dto.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Product product = (Product) request.getAttribute("product");
	%>
	<h1><%=product.getName()%></h1>


	<form action="<%=request.getContextPath()%>/controller" method="post"
		style="display: inline;">
		<input type="hidden" name="command" value="listProducts">
		<button type="submit">continueShopping</button>
	</form>

	<form action="<%=request.getContextPath()%>/controller" method="post">
		<input type="hidden" name="command" value="buynow"> <input
			type="hidden" name="id" value="<%=product.getId()%>">
		<button type="submit">Buy Now</button>
	</form>

	<form action="<%=request.getContextPath()%>/controller" method="post">
		<input type="hidden" name="command" value="addToCart"> <input
			type="hidden" name="id" value="<%=product.getId()%>">
		<button type="submit">Add to Cart</button>
	</form>
</body>
</html>