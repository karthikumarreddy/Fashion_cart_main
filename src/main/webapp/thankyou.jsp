<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Thank you for your purchase!</h2>

<p>Your order will be delivered soon.</p>

<p><b>Order ID:</b> <%=request.getAttribute("orderId")%></p>
<p><b>Delivery City:</b> <%=request.getAttribute("city")%></p>
<p><b>Total Amount:</b> ₹ <%=request.getAttribute("totalAmount")%></p>

<a href="<%=request.getContextPath()%>/home.jsp">Continue Shopping</a>

</body>
</html>