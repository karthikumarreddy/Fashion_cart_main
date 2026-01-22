<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/fashioncart/cssFiles/delivery.css">
</head>
<body>
	<h2>Delivery page</h2>
	<div class="delivery">
	<form action="<%=request.getContextPath()%>/controller" method="post">

    <input type="hidden" name="command" value="saveDelivery">
	<div class="delivery-input">
  	 	FullName: <input type="text" name="fullname" placeholder="Full Name" required>
  	 </div>
  	 
  	 <div class="delivery-input">
  		 Address1: <input type="text" name="address1" placeholder="Address Line 1" required>
  	 </div>
  	 <div class="delivery-input">
  	 	Address2: <input type="text" name="address2" placeholder="Address Line 2">
  	 </div>
  	 
  	 <div class="delivery-input">
  	 	City: <input type="text" name="city" placeholder="City" required>
  	 </div>
  	 <div class="delivery-input">
  	 	Pincode: <input type="number" name="pincode" placeholder="Pincode" required><br>
  	 </div>
  	 <div class="delivery-input">
  	 	MobileNumber: <input type="number" name="mobile" placeholder="Mobile Number" required><br>
	</div>
    <button type="submit">Submit Delivery Details</button>
</form>
	
	</div>
</body>
</html>