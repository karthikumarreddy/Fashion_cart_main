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
	<h2 style="color:black">Enter your details</h2>
	<div class="delivery">
	
	<form id="delivery-form"  onsubmit="return validateForm()"action="<%=request.getContextPath()%>/controller" method="post">

    <input type="hidden" name="command" value="saveDelivery">
	<div class="delivery-input">
		<div>
  	 	FullName:<span class="required">*</span> 
  	 	</div>
  	 	<input id="fullname" type="text" name="fullname" placeholder="Full Name">
  	 	<p id="checkfullname"></p>
  	 	
  	 </div>
  	 
  	 <div class="delivery-input">
  	 <div>
  		 Address1:<span class="required">*</span> 
  		 </div>
  		 <input id="address1" type="text" name="address1" placeholder="Address Line 1">
  		 <p id="checkaddress1"></p>
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
  	 	<p id="checkcity"></p>
  	 </div>
  	 
  	 
  	 <div class="delivery-input">
  	 <div>
  	 	Pincode:<span class="required">*</span> 
  	 	</div><input id="pincode" type="number" name="pincode" placeholder="Pincode" >
  	 	<p id="checkpincode"></p>
  	 </div>
  	 
  	 
  	 <div class="delivery-input">
  	 <div>
  	 	MobileNumber:<span class="required">*</span>
  	 	</div>
  	 	<input id="mobilenumber" type="number" name="mobile" placeholder="Mobile Number" >
  	 	<p id="checkmobilenumber"></p>
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

function validateForm(){
	
	document.addEventListener("DOMContentLoaded",function()){
		const form=document.getElementById("delivery-form");
	}
	
	
	var fullname=document.getElementById("fullname").value.trim();
	var address1=document.getElementById("address1").value.trim();
	var city=document.getElementById("city").value.trim();
	var pincode=document.getElementById("pincode").value.trim();
	var mobilenumber=document.getElementById("mobilenumber").value.trim();
	
	
	var fullnameError=document.getElementById("checkfullname");
	var address1Error=document.getElementById("checkaddress1");
	var cityError=document.getElementById("checkcity");
	var pincodeError=document.getElementById("checkpincode");
	var mobilenumberError=document.getElementById("checkmobilenumber");
	
	
	fullnameError.innerHTML="";
	address1Error.innerHTML="";
	cityError.innerHTML="";
	pincodeError.innerHTML="";
	mobilenumberError.innerHTML="";
	
	var isValid=true;
	
	if(fullname===""){	
		fullnameError.innerHTML="Name is required";
		isValid=false;
	}
	
	if(address1===""){	
		address1Error.innerHTML="address1 is required";
		isValid=false;
	}
	
	if(city===""){	
		cityError.innerHTML="city is required";
		isValid=false;
	}
	
	if(pincode===""){	
		pincodeError.innerHTML="city is required";
		isValid=false;
	}
	if(mobilenumber===""){	
		mobilenumberError.innerHTML="city is required";
		isValid=false;
	}
	
	return isValid;
}


</script>
</html>