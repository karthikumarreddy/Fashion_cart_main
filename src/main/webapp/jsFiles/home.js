function loadProducts(cat) {
	
    
    // Determine category dynamically in JS
    const url = contextPath + '/controller?command=listProducts&category=' + (cat ? cat : 'all');
    
    fetch(url)
        .then(res => res.json())
        .then(products => {

            let html = "";

            products.forEach(p => {
                html += `
                    <div class="product-card">
                        <img src="${p.imagePath}" alt="${p.name}">
                        <h4>Product Name: ${p.name}</h4>
                        <p>Price: $ ${p.price}</p>
                        <p class="${p.availability === 'Available' ? 'available' : 'unavailable'}">
                            ${p.availability}
                        </p>
                        <button ${p.availability === 'IN_STOCK' ? '' : 'disabled'} 
                                onclick="addToCart(${p.id})">
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
	fetch(contextPath+'/controller?command=addToCart&id=' + id)
    .then(res => res.json())
    .then(data => {
    	window.location.href = contextPath+'/cart.jsp';
    })
    .catch(err => console.error(err));
    alert("Product " + id + " added to cart");
}

function cartProducts() {
    fetch(contextPath+'/controller?command=viewCart')
        .then(res => res.json())
        .then(products => {

            let html = "";

            products.forEach(p => {
                html += `
                    <div class="product-card">
                        <img src="${p.imagePath}" alt="\${p.name}">
                        <h4>Product Name: ${p.name}</h4>
                        <p>Price: $ ${p.price}</p>
                        <p class="${p.availability === 'Available' ? 'available' : 'unavailable'}">
                            ${p.availability}
                        </p>
                        
                    </div>
                `;
            });

            document.getElementById("ViewCartSection").innerHTML = html;
        })
        .catch(err => console.error(err));
}