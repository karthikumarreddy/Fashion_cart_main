<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FashionCart</title>
    <style>
body {
	font-family: Arial, sans-serif;
}

a {
	margin-right: 15px;
	cursor: pointer;
	text-decoration: underline;
	color: blue;
}

.navbar {
	height: 90px;
	width: 100%;
	background-color: grey;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

#productSection {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	margin-top: 20px;
}

.product-card {
	border: 1px solid #ccc;
	border-radius: 8px;
	width: 200px;
	padding: 10px;
	box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.product-card img {
	width: 150px;
	height: 150px;
	object-fit: contain;
}

.available {
	color: green;
	font-weight: bold;
}

.unavailable {
	color: red;
	font-weight: bold;
}

<!--category dropdown -->

.dropdown {
	position: relative;
	display: inline-block;
}

.dropbtn {
	text-decoration: none;
	color: black;
	padding: 8px;
	cursor: pointer;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #ffffff;
	min-width: 180px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
	z-index: 1000;
}

.dropdown-content a {
	color: black;
	padding: 10px 14px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	font-weight: bold;
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
                <a onclick="loadProducts('mens'); return false;">Men</a>
                <a onclick="loadProducts('women')"> Women </a>
                <a onclick="loadProducts('children')">children</a>
            </div>
        </div>

<div>
<a href="<%=request.getContextPath()%>/cart.jsp">View Cart</a>
</div>
</nav>

<div id="productSection"></div>

<script>
function loadProducts(cat) {
	 const contextPath = '<%=request.getContextPath()%>';
    
    // Determine category dynamically in JS
    const url = contextPath + '/controller?command=listProducts&category=' + (cat ? cat : 'all');
    
    fetch(url)
        .then(res => res.json())
        .then(products => {

            let html = "";

            products.forEach(p => {
                html += `
                    <div class="product-card">
                        <img src="\${p.imagePath}" alt="\${p.name}">
                        <h4>Product Name: \${p.name}</h4>
                        <p>Price: $ \${p.price}</p>
                        <p class="\${p.availability === 'Available' ? 'available' : 'unavailable'}">
                            \${p.availability}
                        </p>
                        <button \${p.availability === 'IN_STOCK' ? '' : 'disabled'} 
                                onclick="addToCart(\${p.id})">
                            Add to Cart
                        </button>
                    </div>
                `;
            });

            document.getElementById("productSection").innerHTML = html;
        })
        .catch(err => console.error(err));
}

//Call loadProducts() with no argument to show all products by default
document.addEventListener("DOMContentLoaded", function() {
    loadProducts(); // no argument → defaults to 'all'
});

function addToCart(id) {
	fetch('<%= request.getContextPath()%>/controller?command=addToCart&id=' + id)
    .then(res => res.json())
    .then(data => {
    	window.location.href = '<%= request.getContextPath() %>/cart.jsp';
    })
    .catch(err => console.error(err));
    alert("Product " + id + " added to cart");
}

function cartProducts() {
    fetch('<%=request.getContextPath()%>/controller?command=viewCart')
        .then(res => res.json())
        .then(products => {

            let html = "";

            products.forEach(p => {
                html += `
                    <div class="product-card">
                        <img src="\${p.imagePath}" alt="\${p.name}">
                        <h4>Product Name: \${p.name}</h4>
                        <p>Price: $ \${p.price}</p>
                        <p class="\${p.availability === 'Available' ? 'available' : 'unavailable'}">
                            \${p.availability}
                        </p>
                        
                    </div>
                `;
            });

            document.getElementById("ViewCartSection").innerHTML = html;
        })
        .catch(err => console.error(err));
}

</script>
</body>
</html>
