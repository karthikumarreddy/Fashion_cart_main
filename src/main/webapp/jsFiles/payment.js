window.onload = function () {
    fetch(contextPath+'/controller?command=showPayment')
        .then(res => res.json())
        .then(total => {
            console.log("Total from server:", total);
            document.getElementById("totalAmount").innerText = total;
        })
        .catch(err => console.error(err));
};