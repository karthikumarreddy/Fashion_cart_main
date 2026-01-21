window.onload = function () {
    fetch(contextPath+'/controller?command=viewCart')
        .then(res => res.json())
        .then(data => {
            const products = data.cartList;
            const total=data.total;
            let result = [];

            products.forEach(p => {
                let found = false;

                for (let i = 0; i < result.length; i++) {
                    if (result[i].id === p.id) {
                        result[i].quantity++;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    p.quantity = 1;
                    result.push(p);
                }
            });

            let html = "";

            result.forEach(p => {
                let subtotal = p.price * p.quantity;

                html += `
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.category}</td>
                        <td>${p.price}</td>
                        <td>${p.quantity}</td>
                        <td>${subtotal}</td>
                    </tr>
                `;
            });

            document.getElementById("cartBody").innerHTML = html;
            document.getElementById("totalAmount").innerHTML=total;
            
        });
};

function continueShopping() {
    window.location.href = contextPath+'/home.jsp';
}

function buyNow() {
    window.location.href = contextPath+'/payment.jsp';
}