<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Delivery page</h2>
	<div class="delivery">
	<form action="<%=request.getContextPath()%>/controller" method="post">

    <input type="hidden" name="command" value="saveDelivery">

    <input type="text" name="fullname" placeholder="Full Name" required><br>
    <input type="text" name="address1" placeholder="Address Line 1" required><br>
    <input type="text" name="address2" placeholder="Address Line 2"><br>
    <input type="text" name="city" placeholder="City" required><br>
    <input type="number" name="pincode" placeholder="Pincode" required><br>
    <input type="number" name="mobile" placeholder="Mobile Number" required><br>

    <button type="submit">Submit Delivery Details</button>
</form>
	
	</div>
</body>
</html>