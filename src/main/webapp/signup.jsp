<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>

</head>

<body>

<div class="signup-box">
    <h2 style="text-align:center;">Create Account</h2>

    <form action="<%=request.getContextPath()%>/controller?command=signup" method="post">
        <input type="hidden" name="command" value="signup">

        <input type="text" name="userName"
               placeholder="Username" required>

        <input type="email" name="email"
               placeholder="Email" required>

        <input type="password" name="password"
               placeholder="Password" required>

        <button type="submit">Signup</button>
    </form>

    <p class="error">
        <%= request.getAttribute("error") != null
            ? request.getAttribute("error")
            : "" %>
    </p>

    <p style="text-align:center;">
        Already have an account?
        <a href="login.jsp">Login</a>
    </p>
</div>

</body>
</html>
