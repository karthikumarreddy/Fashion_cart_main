<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FashionCart</title>
  	<link rel="stylesheet" href="/fashioncart/cssFiles/home.css">
</head>

<body>
<nav class="navbar">
<div id="logoname">
<h2>FashionCart</h2>
</div>
		<div class="dropdown">
            <a href="#" class="dropbtn">Category ▾</a>
            <div class="dropdown-content">
                <a onclick="loadProducts('mens'); return false;">Men</a>
                <a onclick="loadProducts('women')"> Women </a>
                <a onclick="loadProducts('children')">children</a>
            </div>
        </div>

<div id="viewcart">
<a href="<%=request.getContextPath()%>/cart.jsp">View Cart</a>
</div>
</nav>

<div id="productSection"></div>


</body>
<script>
	const contextPath = '<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="/fashioncart/jsFiles/home.js"></script>
</html>
