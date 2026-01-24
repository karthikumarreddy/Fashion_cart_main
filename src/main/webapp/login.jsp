

<style>
body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    background: linear-gradient(135deg, #667eea, #764ba2);
    height: 100vh;
    display: flex;
    flex-direction:column;
    justify-content: center;
    align-items: center;
}
h2{
	text-align: center;
    margin-bottom: 25px;
    color: white;
    font-size: 26px;
    font-weight: bold;
    letter-spacing: 1px;
}
/* Container */
#login-page {
    background: #fff;
    padding: 30px 35px;
    width: 380px;
    border-radius: 10px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
}

/* Form */
#login-page form {
    display: flex;
    flex-direction: column;
     font-size: 14px;
    color: #333;
}

/* Inputs */
#login-page input[type="text"],
#login-page input[type="password"] {
    width: 100%;
    padding: 10px;
    margin: 6px 0 15px 0;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
}

#login-page input:focus {
    outline: none;
    border-color: #667eea;
    box-shadow: 0 0 4px rgba(102, 126, 234, 0.6);
}

/* Button */
#login-page button {
    margin-top: 10px;
    padding: 12px;
    background: #667eea;
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 15px;
    font-weight: bold;
    cursor: pointer;
}

#login-page button:hover {
    background: #5a67d8;
}

/* Signup text */
#login-page p {
    margin-top: 15px;
    text-align: center;
    font-size: 14px;
}

#login-page a {
    color: #667eea;
    text-decoration: none;
    font-weight: bold;
    margin-left: 5px;
}

#login-page a:hover {
    text-decoration: underline;
}

/* Error message */
p[style*="color:red"] {
    margin-top: 15px;
    text-align: center;
    font-weight: bold;
}


</style>
<h2>Login Page</h2><br>
<div id="login-page">
	
<form action="<%=request.getContextPath()%>/controller" method="post">
    <input type="hidden" name="command" value="login">

   Username: <input type="text" name="userName" placeholder="userName" required>
   Email: <input type="text" name="email" placeholder="email" required>
   Password: <input type="password" name="password" placeholder="password" required>
   
    <button type="submit">Login</button>
    <p>New user?<a href="<%=request.getContextPath()%>/controller?command=signup">Signup</a></p>
</form>

<p style="color:red;">
    <%= request.getAttribute("error") != null
        ? request.getAttribute("error")
        : "" %>
</p>
</div>




