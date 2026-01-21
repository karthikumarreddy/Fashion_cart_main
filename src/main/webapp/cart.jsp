<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>

<style>
body {
    font-family: Arial, sans-serif;
}

table {
    width: 80%;
    border-collapse: collapse;
    margin-top: 20px;
}

th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center;
}

th {
    background-color: #f2f2f2;
}

.total {
    font-size: 18px;
    font-weight: bold;
    margin-top: 20px;
}

.buttons {
    margin-top: 30px;
}

button {
    padding: 10px 20px;
    margin-right: 15px;
    font-size: 16px;
    cursor: pointer;
}
</style>
</head>

<body>

<h1>Shopping Cart</h1>

<table id="cartTable">
    <thead>
        <tr>
            <th>Product Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
    </thead>
    <tbody id="cartBody"></tbody>
</table>

<div class="total" id="totalAmount"></div>

<div class="buttons">
    <button onclick="continueShopping()">Continue Shopping</button>
    <button onclick="buyNow()">Buy Now</button>
</div>

<script>
window.onload = function () {
	fetch('<%=request.getContextPath()%>/controller?command=viewCart')
    .then(res => res.json())
    .then(data => {

        const products = data.cartList;
        const total = data.total;

        let html = "";

        products.forEach(p => {
            let quantity = 1;
            let subtotal = p.price * quantity;

            html += `
            	<tr>
            	    <td>\${p.name}</td>
            	    <td>\${p.category}</td>
            	    <td>\${p.price}</td>
            	    <td>\${quantity}</td>
            	    <td>\${subtotal}</td>
            	</tr>
            	`;
        });

        document.getElementById("cartBody").innerHTML = html;
        document.getElementById("totalAmount").innerText = total;
    })
    .catch(err => console.error(err));

};

function continueShopping() {
    window.location.href = '<%=request.getContextPath()%>/home.jsp';
}

function buyNow() {
    window.location.href = '<%=request.getContextPath()%>/payment.jsp';
}
</script>

</body>
</html>
