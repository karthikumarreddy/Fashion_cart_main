<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="com.fashioncart.dto.ProductqtyDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cssFiles/cart.css">
</head>
<body>

<h1 style="text-align:center;">Shopping Cart</h1>

<%
List<ProductqtyDTO> cartList = (List<ProductqtyDTO>) request.getAttribute("cartList");

    Double totalAmount = (Double) session.getAttribute("totalAmount");

    if (cartList == null || cartList.isEmpty()) {
%>

    <h3 style="text-align:center;">Your cart is empty</h3>
    <div style="margin-left:42.5%">
    <form action="<%=request.getContextPath()%>/controller" method="post"
          style="display:inline;">
        <input type="hidden" name="command" value="listProducts">
        <button type="submit">continueShopping</button>
    </form>
    </div>
    
<%
} else {
%>

<table>
    <thead>
        <tr>
            <th>Product Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>SubTotal</th>
            <th>DeleteItems</th>
        </tr>
    </thead>

    <tbody>
			<%
			for (ProductqtyDTO item : cartList) {
				double subtotal = item.getPrice() * item.getQuantity();
				
			%>
			<tr>
				<td><%=item.getName()%></td>
				<td><%=item.getCategory()%></td>
				<td>₹ <%=String.format("%.2f", item.getPrice())%></td>

				<!-- Quantity buttons -->
				<td>
					<form action="<%=request.getContextPath()%>/controller"
						method="post" style="display: inline;">
						<input type="hidden" name="command" value="decreaseQty"> <input
							type="hidden" name="productId" value="<%=item.getId()%>">
						<button type="submit">-</button>
					</form> <strong><%=item.getQuantity()%></strong>

					<form action="<%=request.getContextPath()%>/controller"
						method="post" style="display: inline;">
						<input type="hidden" name="command" value="increaseQty"> <input
							type="hidden" name="productId" value="<%=item.getId()%>">
						<button type="submit">+</button>
					</form>
				</td>

				<td>₹ <%=String.format("%.2f", subtotal)%></td>

				<!-- Remove button -->
				<td>
					<form action="<%=request.getContextPath()%>/controller"
						method="post">
						<input type="hidden" name="command" value="removeFromCart">
						<input type="hidden" name="productId" value="<%=item.getId()%>">
						<button type="submit">Remove</button>
					</form>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
</table>



<div class="total">
    <strong>
        Total Amount: ₹ <%= String.format("%.2f", totalAmount) %>
    </strong>
</div>

<div class="buttons">
     <form action="<%=request.getContextPath()%>/controller" method="post"
          style="display:inline;">
        <input type="hidden" name="command" value="listProducts">
        <button type="submit">continueShopping</button>
    </form>

    <form action="<%=request.getContextPath()%>/controller" method="post"
          style="display:inline;">
        <input type="hidden" name="command" value="showPayment">
        <button type="submit">Buy Now</button>
    </form>
    
    <jsp:include page="/footer.jsp"></jsp:include>
</div>

<%
    }
%>

</body>
</html>
