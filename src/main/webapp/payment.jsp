<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
<link rel=stylesheet href="/fashioncart/cssFiles/payment.css">
</head>
<style>

</style>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="paymentpage">

		<div class="container">
			<h2 style="color:black">Payment</h2>

			<%
			Double totalAmount = (Double) session.getAttribute("totalAmount");
			if (totalAmount == null) {
				totalAmount = 0.0;
			}
			%>

			<h3>Total Amount: ₹<%=String.format("%.2f", totalAmount)%></h3>
			
			
			<form action="<%=request.getContextPath()%>/controller" method="post">
				<input type="hidden" name="command" value="processPayment">

				<h3>Select Payment Method</h3>

				<label><input type="radio" name="paymentMode" value="CARD"
					required> Credit Card</label><br> <label><input
					type="radio" name="paymentMode" value="UPI" required> UPI</label><br> 
					<label>
					<input type="radio" name="paymentMode" value="COD" required> Cash on
					Delivery</label><br>
					
					<button  type="submit">Pay</button>	
				</form>	
				
				
		
			<form action="<%= request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="viewCart">
			<button type="submit"><- Back</button>
			</form>
		</div>
		</div>
		<jsp:include page="/footer.jsp"></jsp:include>
	
</body>
</html>
