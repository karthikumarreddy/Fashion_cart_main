<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="util.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FashionCart</title>

<link rel="stylesheet"
    href="<%=request.getContextPath()%>/cssFiles/home.css">

<style>
.product-card {
    border: 1px solid #ccc;
    padding: 15px;
    margin: 15px;
    width: 220px;
    float: left;
    text-align: center;
}
.product-card img {
    width: 150px;
    height: 150px;
}
</style>

</head>

<body>

<nav class="navbar">

    <div id="logoname">
        <h2>FashionCart</h2>
    </div>

    <div class="dropdown">
        <a href="#" class="dropbtn">Category ▾</a>
        <div class="dropdown-content">

            <a href="<%=request.getContextPath()%>/controller?command=listProducts&category=mens">Men</a>
            <a href="<%=request.getContextPath()%>/controller?command=listProducts&category=women">Women</a>
            <a href="<%=request.getContextPath()%>/controller?command=listProducts&category=children">Children</a>
        </div>
    </div>

    <div id="viewcart">
        <form action="<%=request.getContextPath()%>/controller" method="get">
            <input type="hidden" name="command" value="viewCart">
            <button type="submit">View Cart</button>
        </form>
    </div>

</nav>

<div style="margin-top:30px; text-align:center;">
<%
    List<Product> products =
        (List<Product>)request.getAttribute("productList");

    if(products!=null)
        for (Product p : products) {
%>

    <div class="product-card">
        <img src="<%= p.getImagePath() %>" alt="<%= p.getName() %>">
        <h4><%= p.getName() %></h4>
        <p>₹ <%= p.getPrice() %></p>
        <p><%= p.isAvailability() %></p>

        <a href="<%=request.getContextPath()%>/controller?command=addToCart&id=<%=p.getId()%>&category=<%=request.getAttribute("category")%>">
    <button>Add to Cart</button>
</a>
    </div>

<%
        }
    
%>
</div>

<div style="clear:both;"></div>

</body>
</html>
