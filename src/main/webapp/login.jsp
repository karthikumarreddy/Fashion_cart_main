<form action="<%=request.getContextPath()%>/controller" method="post">
    <input type="hidden" name="command" value="login">

    <input type="text" name="userName" placeholder="userName" required>
    <input type="text" name="email" placeholder="email" required>
    <input type="password" name="password" placeholder="password" required>

    <button type="submit">Login</button>
</form>

<p style="color:red;">
    <%= request.getAttribute("error") != null
        ? request.getAttribute("error")
        : "" %>
</p>

<a href="<%=request.getContextPath()%>/signup.jsp">New user? Signup</a>
