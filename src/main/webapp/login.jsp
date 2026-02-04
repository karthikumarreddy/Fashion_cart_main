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

<!-- Error and success messages -->
<p class="error"><%= request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage") %></p>
<p class="acc-success"><%= request.getAttribute("SuccessFullMessage") == null ? "" : request.getAttribute("SuccessFullMessage") %></p>

<div class="page-wrapper">
    <div id="login-page">
        <h2 style="color:black;">Login Page</h2>

        <!-- Login form -->
        <form id="signup-form" action="<%=request.getContextPath()%>/controller" method="post">
            <input type="hidden" name="command" value="login">
            
            <div class="login-input">
                Username:<span class="required">*</span>
                <input id="username" type="text" name="userName" placeholder="Username">
            </div>
            
            <div class="login-input">
                Password:<span class="required">*</span>
                <input id="password" type="password" name="password" placeholder="Password">
            </div>
            
            <button type="submit">Login</button>
        </form>

        <!-- Signup link with hidden POST form -->
        <div  class="signup-text"">
        New User?
        <a href="#" onclick="postSignup(event)">Create Account</a>
        <form id="signupForm" action="<%=request.getContextPath()%>/controller" method="post" style="display: none;">
            <input type="hidden" name="command" value="signup">
        </form>
	</div>
        <!-- Generic error message -->
        <p class="error"><%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %></p>
    </div>
</div>

<script>
function postSignup(e) {
    e.preventDefault();
    document.getElementById("signupForm").submit();
}

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("signup-form");
    const username = document.getElementById("username");
    const password = document.getElementById("password");

    const backendUsernameError = "<%=request.getAttribute("usernameError") != null ? request.getAttribute("usernameError") : "" %>";
    const backendPasswordError = "<%=request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %>";

    function showError(input, message) {
        removeError(input);
        const error = document.createElement("p");
        error.className = "js-error";
        error.innerText = message;
        input.classList.add("input-error");
        input.parentElement.appendChild(error);
    }

    function removeError(input) {
        input.classList.remove("input-error");
        const error = input.parentElement.querySelector(".js-error");
        if (error) error.remove();
    }

    username.addEventListener("input", () => removeError(username));
    password.addEventListener("input", () => removeError(password));

    if (backendUsernameError !== "") showError(username, backendUsernameError);
    if (backendPasswordError !== "") showError(password, backendPasswordError);

    form.addEventListener("submit", function(e) {
        let isValid = true;

        if (username.value.trim() === "") {
            showError(username, "Username is required");
            isValid = false;
        }
        if (password.value.trim() === "") {
            showError(password, "Password is required");
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    });
});
</script>

</body>
</html>
