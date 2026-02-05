<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/fashioncart/cssFiles/delivery.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include><br>
	<h2 style="color:black">Enter your Details</h2>
	<div class="delivery">
	
	<form id="delivery-form"action="<%=request.getContextPath()%>/controller" method="post">

    <input type="hidden" name="command" value="saveDelivery">
	<div class="delivery-input">
		<div>
  	 	FullName:<span class="required">*</span> 
  	 	</div>
  	 	<input id="fullname" type="text" name="fullname" placeholder="Full Name">
  	 </div>
  	 
  	 <div class="delivery-input">
  	 <div>
  		 Address1:<span class="required">*</span> 
  		 </div>
  		 <input id="address1" type="text" name="address1" placeholder="Address Line 1">  		 
  	 </div>
  	 
  	 <div class="delivery-input">
  	 	Address2:
  	 	<input type="text" name="address2" placeholder="Address Line 2">
  	 </div>
  	 
  	 <div class="delivery-input">
  	 <div>
  	 	City:<span class="required">*</span>
  	 	</div>
  	 	<input id="city" type="text" name="city" placeholder="City"> 	 	
  	 </div>
  	 
  	 
  	 <div class="delivery-input">
  	 <div>
  	 	Pincode:<span class="required">*</span> 
  	 	</div><input id="pincode" type="number" name="pincode" placeholder="Pincode" >  	 	
  	 </div>
  	 
  	 
  	 <div class="delivery-input">
  	 <div>
  	 	MobileNumber:<span class="required">*</span>
  	 	</div>
  	 	<input id="mobilenumber" type="number" name="mobile" placeholder="Mobile Number" >	
	</div>
    <button type="submit">Submit Delivery Details</button>
</form>

<br>
			<form action="<%= request.getContextPath()%>/controller" method="post">
			<input type="hidden" name="command" value="showPayment">
			<button type="submit"><- Back</button>
			</form>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script>


	
	document.addEventListener("DOMContentLoaded",function(){
		const form=document.getElementById("delivery-form");
		const fullname=document.getElementById("fullname");
		const address1=document.getElementById("address1");
		const city=document.getElementById("city");
		const pincode=document.getElementById("pincode");
		const mobilenumber=document.getElementById("mobilenumber");
		
	
		//REGEX
		
		const FULLNAME_REGEX =/^(?=.*[a-zA-Z])[a-zA-Z0-9 ]{5,30}$/;
		const ADDRESS1_REGEX = /^[a-zA-Z0-9 ,./#-]{5,100}$/;
		const CITY_REGEX = /^[a-zA-Z ]{4,50}$/;
		const PINCODE_REGEX = /^[1-9][0-9]{5}$/;
		const MOBILE_REGEX = /^[6-9][0-9]{9}$/;
		
		
		
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
		
		fullname.addEventListener("input", () => removeError(fullname));
		address1.addEventListener("input", () => removeError(address1));
	    city.addEventListener("input", () => removeError(city));
	    pincode.addEventListener("input", () => removeError(pincode));
	    mobilenumber.addEventListener("input", () => removeError(mobilenumber));
	  
	    
	    
	    form.addEventListener("submit", function (e) {
	        let isValid = true;

	        // Fullname validation
	        if (fullname.value.trim() === "") {
	            showError(fullname, "Fullname is required");
	            isValid = false;
	        } else if(!FULLNAME_REGEX.test(fullname.value.trim())) {
	            showError(
	                    fullname,
	                    "Fullname must be at least 5 characters"
	                );
	                isValid = false;
	            }

	        // address1 validation
	        if (address1.value.trim() === "") {
	            showError(address1, "Address1 is required");
	            isValid = false;
	        } else if(!ADDRESS1_REGEX.test(address1.value.trim())) {
	            showError(
	                    address1,
	                    "Address must be at least 5 characters"
	                );
	                isValid = false;
	            }

	        // city validation
	        if (city.value.trim() === "") {
	            showError(city, "City is required");
	            isValid = false;
	        } else if(!CITY_REGEX.test(city.value.trim())) {
	            showError(
	                    city,
	                    "City must contain only alphabet and at least 4 characters "
	                );
	                isValid = false;
	            }
	     // pincode validation
	        if (pincode.value.trim() === "") {
	            showError(pincode, "pincode is required");
	            isValid = false;
	        } else if(!PINCODE_REGEX.test(pincode.value.trim())) {
	            showError(
	                    pincode,
	                    "pincode must contain only numbers and must be 6 numbers"
	                );
	                isValid = false;
	            }
	     
	        if (mobilenumber.value.trim() === "") {
	            showError(mobilenumber, "mobilenumber is required");
	            isValid = false;
	        } else if(!MOBILE_REGEX.test(mobilenumber.value.trim())) {
	            showError(
	                    mobilenumber,
	                    "Mobile number must be 10 numbers"
	                );
	                isValid = false;
	            }

	        if (!isValid) {
	            e.preventDefault();
	        }
	    });
	});

</script>
</html>