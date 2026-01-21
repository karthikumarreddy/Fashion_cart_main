<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
<link rel="stylesheet" href="/fashioncart/cssFiles/cart.css">
</head>

<body>

	<h1>Shopping Cart</h1>

	<table id="cartTable">
		<thead>
			<tr>
				<th>Product Name</th>
				<th>Category</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
		</thead>
		<tbody id="cartBody"></tbody>
	</table>

	<div class="total">
		<p>TotalCartAmount:</p>
		<p id="totalAmount"><p>
	
	</div>

	<div class="buttons">
		<button onclick="continueShopping()">Continue Shopping</button>
		<button onclick="buyNow()">Buy Now</button>
	</div>

	
	</body>
	<script>
		const contextPath = '<%=request.getContextPath()%>';
	</script>
	
	<script type="text/javascript" src="/fashioncart/jsFiles/cart.js"></script>
</html>
