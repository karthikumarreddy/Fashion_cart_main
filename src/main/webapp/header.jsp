<style>
/* ===== RESET (same as cart.jsp) ===== */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Segoe UI", Arial, sans-serif;
}

/* ===== NAVBAR ===== */
.navbar {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
	
	
    background: #111;
    padding: 15px 30px;
    color: #fff;
    box-shadow: 0 4px 10px rgba(0,0,0,0.3);
}

/* Logo */
#logoname h2 {
    margin: 0;
    font-size: 24px;
    letter-spacing: 1px;
    color: #fff;
}

/* Logout button (same style as cart buttons) */
#logout-btn button {
    padding: 10px 20px;
    border: none;
    border-radius: 8px;
    background: linear-gradient(135deg, #ff3f6c, #e7335d);
    color: #fff;
    font-size: 14px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
}

#logout-btn button:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(0,0,0,0.2);
}
</style>

<nav class="navbar">
    <div id="logoname">
        <h2>FashionCart</h2>
    </div>

    <% if (session.getAttribute("loggedUser") != null) { %>
        <div id="logout-btn">
            <form action="<%=request.getContextPath()%>/controller" method="post">
                <input type="hidden" name="command" value="logout">
                <button type="submit">Logout</button>
            </form>
        </div>
    <% } %>
</nav>
