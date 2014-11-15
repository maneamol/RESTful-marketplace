<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Payment Page</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	function setValues() {
		document.getElementById("userNameText").value = window.localStorage.getItem('userId');
		document.getElementById("totalAmountText").value = window.localStorage.getItem('totalAmount');
	}
	
	function validateCreditCard(number) {
		var regex = new RegExp("^[0-9]{16}$");
	    if (!regex.test(number))
	        return false;
	    return true;
	}
	
	function validateCSV(number) {
		var regex = new RegExp("^[0-9]{3}$");
	    if (!regex.test(number))
	        return false;
	    return true;
	}
	
	function submitForm(form) {
		alert("insubmit");
		var ccNumber = form.elements['carditCardText'].value;
		var csvNumber = form.elements['csvNumberText'].value;
		var totalAmount = document.getElementById("totalAmountText").value;
		var userId = window.localStorage.getItem('userId');
		
		if (validateCreditCard(ccNumber) && validateCSV(csvNumber)) {
			$.ajax({
				url : "rest/market/placeorder",
				type : "POST",
				data : "userId=" + userId + "&ccNumber=" + ccNumber + "&csvNumber=" + csvNumber + "&totalAmount=" + totalAmount,
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					alert("success");
					window.location.href = "home.jsp";
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert('Could not process request.. ' + errorThrown);
					window.location.href = "home.jsp";
				}
			});
		} else {
			alert("Please Enter valied credit card info!");
		}
	}
</script>
</head>
<body onload="setValues();">
	<div align="center">
		<form action="#" name="paymentForm" id="paymentForm" method="post">
			User Name          : <input type="text" name="userNameText" id="userNameText" disabled="disabled"/><br><br><br>
			Total Payment      : <input type="text" name="totalAmountText" id="totalAmountText" disabled="disabled"/><br><br><br>
			Card type          : <input type="text" name="cardType" id="cardType" value="Visa" disabled="disabled"/><br><br><br>
			Credit Card Number : <input type="text" name="carditCardText" id="carditCardText" maxlength="16" /><br><br><br>
			CSV Number         : <input type="text" name="csvNumberText" id="csvNumberText" maxlength="3" onblur="javascript:validateCreditCard(this.form)"/><br><br><br>
			<input type="button" name="makePayment" id="makePayment" value="Make Payment" onclick="javascript:submitForm(this.form)"/>
		</form>
	</div>
</body>
</html>