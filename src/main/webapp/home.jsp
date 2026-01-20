<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FashionCart</title>
    <style>
        body { font-family: Arial, sans-serif; }
        a {
            margin-right: 15px;
            cursor: pointer;
            text-decoration: underline;
            color: blue;
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
            box-shadow: 2px 2px 8px rgba(0,0,0,0.1);
            text-align: center;
        }
        .product-card img {
            width: 150px;
            height: 150px;
            object-fit: contain;
        }
        .available { color: green; font-weight: bold; }
        .unavailable { color: red; font-weight: bold; }
    </style>
</head>

<body>

<h2>FashionCart</h2>

<a onclick="loadProducts('all')">All Products</a>
<a onclick="loadProducts('mens')">Men</a>
<a onclick="loadProducts('women')">Women</a>
<a onclick="loadProducts('children')">Children</a>
<a href="<%=request.getContextPath()%>/controller?command=viewCart" onclick="cartProoducts()">view cart</a>

<div id="productSection"></div>

<script>
function loadProducts(cat) {
    fetch('<%= request.getContextPath() %>/controller?command=listProducts&category=' + cat)
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

function addToCart(id) {
	fetch('<%= request.getContextPath() %>/controller?command=addToCart&id=' + id)
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
