<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>

<style>
.product-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}

.product-card {
    width: 240px;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 12px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    text-align: center;
}

.product-card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
    border-radius: 6px;
}

.product-card h4 {
    margin: 8px 0;
}

.product-card p {
    margin: 4px 0;
    font-size: 14px;
}

.price {
    font-weight: bold;
}

.in-stock {
    color: green;
    font-weight: bold;
}

.out-stock {
    color: red;
    font-weight: bold;
}

.add-btn {
    margin-top: 10px;
    width: 100%;
    padding: 8px;
    background-color: #2874f0;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.add-btn:disabled {
    background-color: #999;
    cursor: not-allowed;
}
</style>

</head>
<body>

<!-- Category links -->
<a onclick="loadProducts('ALL'); return false;">All</a>
<a onclick="loadProducts('MEN'); return false;">MEN</a>
<a onclick="loadProducts('WOMEN'); return false;">WOMEN</a>
<a onclick="loadProducts('CHILDREN'); return false;">CHILDREN</a>

<hr>

<!-- Product cards container -->
<div id="productSection" class="product-container"></div>

<script>
function loadProducts(category) {
    fetch('<%= request.getContextPath() %>/controller?command=listProducts&category=' + category)
        .then(response => response.json())
        .then(products => {
            let html = "";

            products.forEach(p => {
            	console.log(p)
                let inStock = p.availability === "IN_STOCK";

                html += `
                    <div class="product-card">
                        <img src="<%= request.getContextPath() %>/${p.imagePath}" alt="${p.name}">
                        <h4>${p.name}</h4>
                        <p><strong>Category:</strong> ${p.category}</p>
                        <p class="price">₹ ${p.price}</p>
                        <p class="${inStock ? 'in-stock' : 'out-stock'}">${p.availability}</p>
                        <button class="add-btn" ${inStock ? 'disabled' : ''} onclick="/controller?command=addToCart&productId='${p.id}')">Add to Cart</button>
                    </div>
                `;
            });

            document.getElementById("productSection").innerHTML = html;
        })
        .catch(error => {
            console.error("Error loading products:", error);
            document.getElementById("productSection").innerHTML = "<p style='color:red;'>Failed to load products</p>";
        });
}

</script>


</body>
</html>
