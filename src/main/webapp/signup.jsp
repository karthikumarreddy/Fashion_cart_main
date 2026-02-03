<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
<link rel="stylesheet" href="/fashioncart/cssFiles/signup.css">
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="page-wrapper">
<div class="signup-box">
    <h2 style="text-align:center;">Create Account</h2>

    <form id="signup-form" action="<%=request.getContextPath()%>/controller" method="post">
    		
       <input type="hidden" name="command" value="signup">
       
       
	  <div style="display:flex;flex-direction:column;">
	  <div>
       UserName:<span class="required">*</span>
       <input id ="username"type="text" name="userName" placeholder="Username" value="<%=request.getParameter("username")!=null?request.getParameter("username"):"" %>">
       </div>
       </div>
       
       <div>
       Email:<span class="required">*</span> 
       <input id="email" type="text" name="email" placeholder="Enter your Email">
      </div>
      
       <div class="password-wrapper">
      	 Password:<span class="required">*</span> <input id="password" type="password" name="enterPassword" placeholder="Password">
				<div class="tooltip">
					Password must contain:<br> • At least 8 characters<br> •
					One uppercase letter<br> • One number<br> • One special
					character
				</div>	
      </div>
		
		<div>
      		Confirm Password:<span class="required">*</span> 
      		<input id="confirm-password" type="password" name="confirmPassword" placeholder="Password">
		</div>
        <button type="submit">SignUp</button>
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
</div>
</body>



<script>

document.addEventListener("DOMContentLoaded",function(){
	
	const form=document.getElementById("signup-form");
	const username=document.getElementById("username");
	const email=document.getElementById("email");
	const password = document.getElementById("password");
	const confirmpassword=document.getElementById("confirm-password");
	
	const backendUsernameError="<%=request.getAttribute("usernameError")!=null ? request.getAttribute("usernameError"):""%>";
	const backendEmailError="<%=request.getAttribute("emailError")!=null ? request.getAttribute("emailError"):""%>";
	const  USERNAME_REGEX = /^[a-zA-Z].{5,29}$/;
    const EMAIL_REGEX = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-zA-Z]+$/;
    const PASSWORD_PATTERN = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\S+$).{8,}$/;
    
    
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
    email.addEventListener("input", () => removeError(email));
    password.addEventListener("input", () => removeError(password));
    confirmpassword.addEventListener("input", () => removeError(confirmpassword));
    
    if (backendUsernameError !== "") {
        showError(username, backendUsernameError);
    }
    if (backendEmailError !== "") {
        showError(email, backendEmailError);
    }
   
    form.addEventListener("submit", function (e) {
        let isValid = true;

        // Username validation
        if (username.value.trim() === "") {
            showError(username, "Username is required");
            isValid = false;
        } else if (!USERNAME_REGEX.test(username.value.trim())) {
            showError(
                username,
                "Username must start with a letter and be 5–29 characters"
            );
            isValid = false;
        }

        // Email validation
        if (email.value.trim() === "") {
            showError(email, "Email is required");
            isValid = false;
        } else if (!EMAIL_REGEX.test(email.value.trim())) {
            showError(email, "Enter a valid email address");
            isValid = false;
        }

        // Password validation
        if (password.value.trim() === "") {
            showError(password, "Password is required");
            isValid = false;
        } else if (!PASSWORD_PATTERN.test(password.value.trim())) {
            showError(
                password,
                "Password must be at least 8 characters with uppercase, lowercase & number"
            );
            isValid = false;
        }
     // Confirm password validation
        if (confirmpassword.value.trim() === "") {
            showError(confirmpassword, "Confirm password is required");
            isValid = false;
        } else if (password.value.trim() !== confirmpassword.value.trim()) {
            showError(confirmpassword, "Passwords do not match");
            isValid = false;
        }

        if (!isValid) {
            e.preventDefault();
        }
    });

});

</script>

</html>
