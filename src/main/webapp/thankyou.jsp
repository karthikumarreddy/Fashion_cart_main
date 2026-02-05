<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thank you</title>
<link rel="stylesheet" href="/fashioncart/cssFiles/thankyou.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<h2 id="thank-you">Thank you for your purchase!</h2>

<p>Your order will be delivered soon.</p>
<div class="order-details">
<p><b>Order ID:</b> <%=request.getAttribute("orderId")%></p>
<p><b>Delivery City:</b> <%=request.getAttribute("city")%></p>
<p><b>Total Amount:</b> ₹ <%=session.getAttribute("totalAmount")%></p>
</div>

<form action="<%=request.getContextPath()%>/controller" method="post"
          style="display:inline;">
        <input type="hidden" name="command" value="listProducts">
        <button type="submit">continueShopping</button>
    </form>


	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>