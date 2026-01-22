<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="com.fashioncart.dto.ProductDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/cssFiles/cart.css">

<style>
table {
    width: 80%;
    margin: auto;
    border-collapse: collapse;
}
th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center;
}
th {
    background-color: #f4f4f4;
}
.total {
    text-align: center;
    font-size: 20px;
    margin-top: 20px;
}
.buttons {
    text-align: center;
    margin-top: 20px;
}
button {
    padding: 10px 20px;
    margin: 10px;
}
</style>

</head>

<body>

<h1 style="text-align:center;">Shopping Cart</h1>

<%
    List<ProductDTO> cartList =
        (List<ProductDTO>) request.getAttribute("cartList");

    Double totalAmount =
        (Double) request.getAttribute("totalAmount");

    if (cartList == null || cartList.isEmpty()) {
%>

    <h3 style="text-align:center;">Your cart is empty</h3>

    <div class="buttons">
        <a href="<%=request.getContextPath()%>/home.jsp">
            <button>Continue Shopping</button>
        </a>
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
            <th>Subtotal</th>
        </tr>
    </thead>

    <tbody>
<%
        for (ProductDTO item : cartList) {
            double subtotal = item.getPrice() * item.getQuantity();
%>
        <tr>
            <td><%= item.getName() %></td>
            <td><%= item.getCategory() %></td>
            <td>₹ <%= String.format("%.2f", item.getPrice()) %></td>
            <td><%= item.getQuantity() %></td>
            <td>₹ <%= String.format("%.2f", subtotal) %></td>
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

    <a href="<%=request.getContextPath()%>/home.jsp">
        <button>Continue Shopping</button>
    </a>

    <form action="<%=request.getContextPath()%>/controller" method="get"
          style="display:inline;">
        <input type="hidden" name="command" value="showPayment">
        <button type="submit">Buy Now</button>
    </form>

</div>

<%
    }
%>

</body>
</html>
s