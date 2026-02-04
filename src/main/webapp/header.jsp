<%@ page contentType="text/html; charset=UTF-8" %>

<style>
.navbar {
    position: relatvive;
    top: 0;
    left: 0;
    width: 100%;
    height: 80px;
    background: #111;
    display: flex;
    align-items: center;
    justify-content:space-around;
    color: #fff;
    z-index: 9999; /* very important */
  	
}
h2{
	color:white;
}

#logout-btn button{
	background:linear-gradient(135deg, #ff3f6c, #e7335d);
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
