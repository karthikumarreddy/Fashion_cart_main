<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>

	<link rel="stylesheet" href="/fashioncart/cssFiles/payment.css">

</head>

<body>

<div class="container">

<h2>Payment</h2>

<%
    Double totalAmount = (Double) session.getAttribute("totalAmount");
    if (totalAmount == null) {
        totalAmount = 0.0;
    }
%>

<h3>Total Amount: ₹ <%= String.format("%.2f", totalAmount) %></h3>
<form action="<%=request.getContextPath()%>/controller" method="post">
    <input type="hidden" name="command" value="processPayment">

    <h3>Select Payment Method</h3>

    <label><input type="radio" name="paymentMode" value="CARD" required> Credit Card</label><br>
    <label><input type="radio" name="paymentMode" value="UPI"> UPI</label><br>
    <label><input type="radio" name="paymentMode" value="COD"> Cash on Delivery</label><br><br>

    <button type="submit">Pay</button>
</form>

	
</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
