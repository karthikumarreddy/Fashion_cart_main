<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
</head>

<body>

<h2>Payment</h2>

<h3>Total Amount: ₹ <span id="totalAmount">0</span></h3>

<form action="<%=request.getContextPath()%>/controller" method="post">

    <input type="hidden" name="command" value="processPayment">

    <label>
        <input type="radio" name="paymentMode" value="CARD" checked>
        Card
    </label><br>

    <label>
        <input type="radio" name="paymentMode" value="UPI">
        UPI
    </label><br>

    <label>
        <input type="radio" name="paymentMode" value="COD">
        Cash on Delivery
    </label><br><br>

    <button type="submit">Pay & Continue</button>
</form>

<script>
window.onload = function () {
    fetch('<%=request.getContextPath()%>/controller?command=showPayment')
        .then(res => res.json())
        .then(total => {
            console.log("Total from server:", total);
            document.getElementById("totalAmount").innerText = total;
        })
        .catch(err => console.error(err));
};
</script>

</body>
</html>
