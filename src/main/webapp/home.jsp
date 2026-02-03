<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.fashioncart.dto.Product"%>

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
		<div style="display: flex; gap: 30px">
			<div class="dropdown">
				<a href="#" class="dropbtn" onclick="toggleDropdown(event)">Category
					▾</a>
				<div class="dropdown-content" id="dropdownMenu">

					<%
					String category = (String) session.getAttribute("category");
					%>

					<%
					if (category != null && (category.equalsIgnoreCase("mens") || category.equalsIgnoreCase("women")
							|| category.equalsIgnoreCase("children"))) {
					%>

					<form action="<%=request.getContextPath()%>/controller"
						method="post">
						<input type="hidden" name="command" value="listProducts">
						<input type="hidden" name="category" value="All">
						<button type="submit">All</button>
					</form>

					<%
					}
					%>

					<form action="<%=request.getContextPath()%>/controller"
						method="post">
						<input type="hidden" name="command" value="listProducts">
						<input type="hidden" name="category" value="mens">
						<button type="submit">Men</button>
					</form>

					<form action="<%=request.getContextPath()%>/controller"
						method="post">
						<input type="hidden" name="command" value="listProducts">
						<input type="hidden" name="category" value="women">
						<button type="submit">Women</button>
					</form>

					<form action="<%=request.getContextPath()%>/controller"
						method="post">
						<input type="hidden" name="command" value="listProducts">
						<input type="hidden" name="category" value="children">
						<button type="submit">Children</button>
					</form>

				</div>
			</div>

			<%
			if (session.getAttribute("loggedUser") == null) {
			%>
			<div id="login-btn">
				<form action="<%=request.getContextPath()%>/controller"
					method="post">
					<input type="hidden" name="command" value="login">
					<button type="submit">Login / SignUp</button>
				</form>
			</div>
			<%
			} else {
			%>
			<div id="logout-btn">
				<form action="<%=request.getContextPath()%>/controller"
					method="post">
					<input type="hidden" name="command" value="logout">
					<button type="submit">Logout</button>
				</form>
			</div>
			<%
			}
			%>

			<div id="viewcart">
				<form action="<%=request.getContextPath()%>/controller"
					method="post">
					<input type="hidden" name="command" value="viewCart">
					<button type="submit">
						View Cart -
						<%=session.getAttribute("cartCount") != null ? session.getAttribute("cartCount") : "0"%>
						
					</button>
				</form>

			</div>
			<div style="padding-top: 10px; font-size: bold;">
				<%
				if (session.getAttribute("username") != null) {
				%>
				Welcome!
				<%=session.getAttribute("username")%>
				<%
				}
				%>
			</div>
		</div>
	</nav>

	<div id="products1" style="display: flex; gap: 20px; margin-top: 10px">

		<%
		List<Product> products = (List<Product>) session.getAttribute("productList");

		if (products != null) {
			for (Product p : products) {
		%>

		<a
			href="<%=request.getContextPath()%>/controller?command=productPage&productId=<%=p.getId()%>"
			style="">
			<div class="product-card">
				<img
					src="<%=request.getContextPath()%>/images/<%=p.getImagePath()%>">
		</a>
		<h4><%=p.getName()%></h4>
		<p>₹<%=p.getPrice()%></p>
		<p><%=p.isAvailability().equalsIgnoreCase("IN_STOCK") ? "Available" : "Currently UnAvailable"%></p>

		<%
		if (p.isAvailability().equalsIgnoreCase("IN_STOCK")) {
		%>

		<form action="<%=request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="buynow"> 
			<input type="hidden" name="id" value="<%=p.getId()%>">
			<button type="submit">Buy Now</button>
		</form>

		<form action="<%=request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="addToCart"> 
			<input type="hidden" name="id" value="<%=p.getId()%>">
			<button type="submit">Add to Cart</button>
		</form>

		<%
		} else {
		%>

		<button style="cursor: not-allowed; background-color: grey" disabled>
			Buy Now</button>

		<button style="cursor: not-allowed; background-color: grey" disabled>
			Add to Cart</button>

		<%
		}
		%>
	</div>

	<%
	}
	}
	%>

	<div class="pagination-container">

		<%
		Integer currentPage = (Integer) request.getAttribute("currentPage");
		Integer totalPages = (Integer) request.getAttribute("totalPages");
		String categoryParam = (String) session.getAttribute("category");

		if (totalPages != null && totalPages > 1) {
		%>

		<%
		if (currentPage > 1) {
		%>
		
		<form action="<%=request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="listProducts">
			<input type ="hidden" name="category" value="<%=categoryParam%>">
			<input type ="hidden" name="page" value="<%=currentPage-1%>">
			<input type=submit value="Prev">
		</form>
		
		<%
		}
		%>

		<%
		for (int i = 1; i <= totalPages; i++) {
		%>
		<%
			if (i == currentPage) {
		%>
			<b><%=i%></b>
		<%
		} else {
		%>
		
		<form action="<%=request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="listProducts">
			<input type ="hidden" name="category" value="<%=categoryParam%>">
			<input type ="hidden" name="page" value="<%=i%>">
			<input type=submit value="<%=i%>">
		</form>
		
		<%
		}
		%>
		<%
		}
		%>

		<%
		if (currentPage < totalPages) {
		%>
		
		<form action="<%=request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="listProducts">
			<input type ="hidden" name="category" value="<%=categoryParam%>">
			<input type ="hidden" name="page" value="<%=currentPage + 1%>">
			<input type=submit value="Next">
		</form>
		
		<%
		}
		}
		%>

		

	</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
	</div>

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const dropdownMenu = document.getElementById("dropdownMenu");

			window.toggleDropdown = function(event) {
				event.preventDefault();
				event.stopPropagation();
				dropdownMenu.classList.toggle("show");
			};

			document.addEventListener("click", function() {
				dropdownMenu.classList.remove("show");
			});

			dropdownMenu.addEventListener("click", function(e) {
				e.stopPropagation();
			});
		});
	</script>

</body>
</html>
