<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fashioncart.dto.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details page</title>
<link rel=stylesheet href="/fashioncart/cssFiles/product.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
	Product product = (Product) request.getAttribute("product");
	%>
	<div class="container">
	<h1 style="padding-left:36.5%"><%=product.getName()%></h1>
	<div id="img-des">
	 <img src="<%=request.getContextPath()%>/images/<%=product.getImagePath()%>">
	 <div class="product-description">
	 	
		<p><%=product.getDescription()%></p>	
		<h3>Price: ₹<%=product.getPrice() %></h3>
		<h4>Availbality:<%=product.isAvailability().equalsIgnoreCase("IN_Stock")?"Available":"Unavailable" %></h4>
		</div>
	</div>

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
	</div>

    <input type="hidden" name="command" value="productPage">
    <input type="hidden" name="add" value="addcart">
    <input type="hidden" name="productId" value="<%=product.getId()%>">
    <button type="submit">Add to Cart</button>
</form>

</body>
</html>