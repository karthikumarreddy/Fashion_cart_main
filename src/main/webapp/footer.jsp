<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<hr>

<style>
	.footer {
	width:100%;
	display:flex;
	flex-direction:column;
    background-color: grey;
    padding: 15px;
    text-align: center;
    font-size: 14px;
    color: #333;
    margin-top:10px;
}

.footer p {
	font-size:bold;
	
    margin: 5px 0;
}

.footer p {
    color: #333;
    text-decoration: none;
}

.footer p:hover {
    text-decoration: underline;
    color:red;
}
	
</style>


<div class="footer" style="background-color:#f0f0f0; padding:15px;text-align:center;">
	<p>
	Contact us: <%=application.getInitParameter("fashioncartEmail") %> | 9342685603
	</p>
	
	<p>
	&copy; 2026 fashionCart.All rights reserved.
	</p>
</div>