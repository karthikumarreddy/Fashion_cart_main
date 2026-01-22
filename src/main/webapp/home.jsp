<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="util.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FashionCart</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/cssFiles/home.css">

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

	<div id="products1" style="display:flex;gap:20px;margin-top:10px">
		<%
    List<Product> products =
        (List<Product>)session.getAttribute("productList");

    if(products!=null)
        for (Product p : products) {
%>

		<div class="product-card">
			<img src="<%=p.getImagePath()%>" alt="<%=p.getName()%>">
			<h4><%=p.getName()%></h4>
			<p>
				₹
				<%=p.getPrice()%></p>
			<p><%=p.isAvailability()%></p>

			
			<a href="<%=request.getContextPath()%>/controller?command=addToCart&id=<%=p.getId()%>">
				<button onclick="addtocart('<%=p.getId()%>')">Add to Cart</button>	
			</a>
		</div>

		<%
		}
		%>
	</div>

	

</body>

<script>
	
	function addtocart(id){
		console.log(id);
		alert("Product " + id + " added to cart");
	}
	
</script>
</html>
