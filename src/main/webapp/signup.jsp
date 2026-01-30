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

<div class="signup-box">
    <h2 style="text-align:center;">Create Account</h2>

    <form  onSubmit=" return validateForm()" action="<%=request.getContextPath()%>/controller" method="post">
    		
       <input type="hidden" name="command" value="signup">
	   <div style="display:flex;flex-direction:column;">
       UserName: <input id ="username"type="text" name="userName" placeholder="Username">
       <p id="checkusername" class="display-error"></p>
       </div>
       
       <div>
       Email: <input id="email"type="email" name="email" placeholder="Enter your Email">
       <p id="checkemail" class="display-error"></p>
      </div>
       <div class="password-wrapper">
      	 Password: <input id="password" type="password" name="enterPassword" placeholder="Password">
				<div class="tooltip">
					Password must contain:<br> • At least 8 characters<br> •
					One uppercase letter<br> • One number<br> • One special
					character
				</div>
				<p id="checkpassword" class="display-error"></p>
      </div>
		
		<div>
      		Confirm Password: <input type="password" name="confirmPassword" placeholder="Password">
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

</body>

<script>
function validateForm() {
    var username = document.getElementById("username").value.trim();
    var email = document.getElementById("email").value.trim();
	var password=document.getElementById("password").value.trim();
   	var userError = document.getElementById("checkusername");
    var emailError = document.getElementById("checkemail");
    var passwordError=document.getElementById("checkpassword");

    // reset messages
//    userError.innerHTML = "";
  //  emailError.innerHTML = "";

    var isValid = true;

    if (username === "") {
        userError.innerHTML = "Username is required";
        isValid = false;
    }
    
    if (email === "") {
        emailError.innerHTML = "Email is required";
        isValid = false;
    }
    if(password===""){
    		passwordError.innerHTML= "Password is required";
			isValid=false;
    }

    return isValid;
}
</script>


</html>
