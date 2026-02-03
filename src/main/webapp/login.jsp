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

<p class="error"><%= request.getAttribute("errorMessage") == null ? "": request.getAttribute("errorMessage") %></p>
<div class="page-wrapper">
	<p><%= request.getAttribute("SuccessFullMessage") == null
													? "": request.getAttribute("SuccessFullMessage") %></p>
<div id="login-page">
    <h2 style="color:black;">Login Page</h2>

    <form onSubmit=" return validateForm()" action="<%=request.getContextPath()%>/controller" method="post">
        <input type="hidden" name="command" value="login">
		<div>
        Username:<span class="required">*</span>
        <input id="username" type="text" name="userName" placeholder="Username">
		<p id="checkusername" class="display-error"></p>
		</div>
		
		<div>
        Password:<span class="required">*</span>
        <input id="password" type="password" name="password" placeholder="Password" >
		<p id="checkpassword" class="display-error"></p>
		</div>
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
</div>

</body>

<script>
function validateForm(){
	var username=document.getElementById("username").value.trim();
	var password=document.getElementById("password").value.trim();
	
	var userError = document.getElementById("checkusername");
	var passwordError = document.getElementById("checkpassword");
	
	var isValid=true;

	userError.innerHTML = "";
    passwordError.innerHTML = "";
		
		if(username===""){
			console.log("username is null");
			userError.innerHTML="username is required";
			isValid=false;
		}
		if(password===""){
			console.log("password is null");
			passwordError.innerHTML="password is required";
			isValid=false;
		}
		return isValid;
	}
	
</script>
</html>
