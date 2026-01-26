<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="util.Product" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FashionCart</title>

<link rel=stylesheet href="/fashioncart/cssFiles/home.css">
</head>

<body>

<nav class="navbar">

    <div id="logoname">
        <h2>FashionCart</h2>
    </div>

  <div class="dropdown">
    <a href="#" class="dropbtn" onclick="toggleDropdown(event)">Category ▾</a>
    <div class="dropdown-content" id="dropdownMenu">
        <a href="<%=request.getContextPath()%>/controller?command=listProducts&category=mens">Men</a>
        <a href="<%=request.getContextPath()%>/controller?command=listProducts&category=women">Women</a>
        <a href="<%=request.getContextPath()%>/controller?command=listProducts&category=children">Children</a>
    </div>
  </div>
	<%
 	if(session.getAttribute("loggedUser")==null){
 		%>	
 		<div id="login-btn">
		<form action="<%=request.getContextPath()%>/controller" method="get">
            <input type="hidden" name="command" value="login">
            <button type="submit">Login</button>
        </form>
	</div>	
 	<% }else{%>
		<div id="logout-btn">
			<form action="<%=request.getContextPath()%>/controller" method="get">
				<input type="hidden" name="command" value="logout">
				<button type="submit">Logout</button>
			</form>
		</div>
		<%}%>
	

   <div id="viewcart">
    <form action="<%=request.getContextPath()%>/controller" method="get">
        <input type="hidden" name="command" value="viewCart">
        <button type="submit">
            View Cart (<%= session.getAttribute("cartCount") != null 
                ? session.getAttribute("cartCount") 
                : 0 %>)
        </button>
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
			<img src="<%= request.getContextPath()%>/images/<%= p.getImagePath()%>">
			<h4><%=p.getName()%></h4>
			<p>₹<%=p.getPrice()%></p>
			<p><%=p.isAvailability()%></p>
			
			<a href="<%=request.getContextPath()%>/controller?command=addToCart&id=<%=p.getId()%>">
				<button>Add to Cart</button>	
			</a>
		</div>
		<%}%>
	</div>
</body>

<script>

const isLoggedIn = <%= (session.getAttribute("loggedUser") != null) %>;

	document.addEventListener("DOMContentLoaded", function () {

	    const dropdownMenu = document.getElementById("dropdownMenu");

	    window.toggleDropdown = function (event) {
	        event.preventDefault();
	        event.stopPropagation();
	        dropdownMenu.classList.toggle("show");
	    };

	    // Close dropdown when clicking outside
	    document.addEventListener("click", function () {
	        dropdownMenu.classList.remove("show");
	    });

	    // Prevent close when clicking inside dropdown
	    dropdownMenu.addEventListener("click", function (e) {
	        e.stopPropagation();
	    });

	});
	

</script>
</html>
