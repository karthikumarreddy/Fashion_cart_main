	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	
	<%@ page import="java.util.*" %>
	<%@ page import="com.fashioncart.dto.CartItemView" %>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>My Cart</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/cssFiles/cart.css">
	</head>
	
	<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page-wrapper">
	<%=request.getAttribute("errorMessge")!=null?request.getAttribute("errorMessage"):"" %>
	<%
	List<CartItemView> cartList = (List<CartItemView>) request.getAttribute("cartList");
	
	    Double totalAmount = (Double) session.getAttribute("totalAmount");
	   
	
	    if (cartList == null || cartList.isEmpty()) {
	%>
	
	
	
	    <h3 style="text-align:center;">Your cart is empty</h3>
	    <div style="margin-left:42.5%">
	    
	    <%
	    Integer currentPage = (Integer) request.getAttribute("currentPage");
		Integer totalPages = (Integer) request.getAttribute("totalPages");
		String categoryParam = (String) session.getAttribute("category");
	    %>
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
	       	 	<th>Product_image</th>
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
				for (CartItemView item : cartList) {
						double subtotal = item.getSubTotal();
						
					if(item.getImagePath()==null){
						System.out.println("imagePath : "+null);
					}
					
						
				%>
				<tr>
					<td><img src="<%=request.getContextPath()%>/images/<%=item.getImagePath()%>" height="100px" width="100px" alt="loading"></td>
					<td><%=item.getName()%></td>
					<td><%=item.getCategory()%></td>
					<td>₹ <%=String.format("%.2f", item.getPrice())%></td>
	
					<!-- Quantity buttons -->
					<td>
						<form action="<%=request.getContextPath()%>/controller"
							method="post" style="display: inline;">
							<input type="hidden" name="command" value="viewCart"> 
							<input type="hidden" name="action" value="dec"> 
							<input type="hidden" name="productId" value="<%=item.getId()%>">
							<button type="submit">-</button>
						</form> <strong><%=item.getQuantity()%></strong>
	
						<form action="<%=request.getContextPath()%>/controller"
							method="post" style="display: inline;">
							<input type="hidden" name="command" value="viewCart">
							<input type="hidden" name="action" value="inc">
							 <input type="hidden" name="productId" value="<%=item.getId()%>">
							<button type="submit">+</button>
						</form>
					</td>
	
					<td>₹ <%=String.format("%.2f", subtotal)%></td>
	
					<!-- Remove button -->
					<td>
						<form action="<%=request.getContextPath()%>/controller"
							method="post">
							<input type="hidden" name="command" value="viewCart">
							<input type="hidden" name="action" value="remove">
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
	
	
		</form>
	     <form action="<%=request.getContextPath()%>/controller" method="post"
	          style="display:inline;">
	        <input type="hidden" name="command" value="listProducts">
	        <button type="submit">Continue Shopping</button>
	    </form>
	
	    <form action="<%=request.getContextPath()%>/controller" method="post"
	          style="display:inline;">
	        <input type="hidden" name="command" value="showPayment">
	        <button type="submit">CheckOut</button>
	    </form>
	    
	    <jsp:include page="/footer.jsp"></jsp:include>
	</div>
	
	<%
	    }
	%>
	</div>
	</body>
	</html>
