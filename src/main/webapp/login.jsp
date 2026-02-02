<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" href="/fashioncart/cssFiles/login.css">
</head>

<body>

<jsp:include page="header.jsp"></jsp:include>

<p class="error">
    <%= request.getAttribute("errorMessage") == null
        ? ""
        : request.getAttribute("errorMessage") %>
</p>

<div id="login-page">
    <h2>Login Page</h2>

    <form action="<%=request.getContextPath()%>/controller" method="post">
        <input type="hidden" name="command" value="login">

        Username:
        <input type="text" name="userName" placeholder="Username" required>

        Password:
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit">Login</button>

        <p>
            New User?
            <a href="<%=request.getContextPath()%>/controller?command=signup">
                Create Account
            </a>
        </p>
    </form>

    <p class="error">
        <%= request.getAttribute("error") == null
            ? ""
            : request.getAttribute("error") %>
    </p>
</div>

</body>
</html>
