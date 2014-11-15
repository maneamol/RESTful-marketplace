<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Cart</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" type="text/css" />
<link href="style.css" rel="stylesheet" media="screen" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>
<script type="text/javascript">
	function getCart() {
		var userId = window.localStorage.getItem('userId');
		$.ajax({
			url : "rest/market/cartitems",
			type : "GET",
			data : "userId=" + userId,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				$(document).ready(
						function() {
							var table = $('<table border="1" align="center" style="width:60%" class="table-hover"/>').appendTo($('#amol'));
							$('<tr align="center" style="height:60px;"/>')
									.appendTo(table)
									.append("<td ><h2>Item Details</h2></td>")
									.append("<td><h2>Price</h2></td>");
							
							var totalAmount = 0;
							
							var content = "";
							
							$(data).each(
									function(i, data) {
										totalAmount = totalAmount + data.price;
										$('<tr align="center" style="height:40px;"/>')
										.appendTo(table)
										.append("<td>" + "Item Name : " + data.name + "<br/>"
														+ "Description : " + data.description + "<br/>"
														+ "Quantity : " + data.count + "<br/>"
														+ "</td>")
										.append("<td><div float='right'>"+data.price+"<button class='btn btn-primary' style='margin-left:10px;'>Delete</button></div></td>")
										

										
									});
							$('<tr align="center" style="height:40px;"/>')
							.appendTo(table)
							.append("<td ><h3>Total Amount</h3></td>")
							.append("<td>"+ totalAmount +"</td>");
							
							
							
							
							var paymentForm = " <form action=\"#\" method=\"post\"> " 
							+ " 	<input type=\"hidden\" name=\"totalAmount\" id=\"totalAmount\" value=" + totalAmount + "> " 
							+ " 	<input type=\"button\" class=\"btn btn-success btn-lg btn-block\" value=\"Place Order\" onclick=\"javascript:makePayment(this.form);\" style=\"height:60px\"> " + " </form> "
							
							var paymentTable = $('<table align="center" class="table" style="width: 60%;height:90px"/>')
							.appendTo($('#payment'));
							$('<tr align="center" style="height:90px;"/>')
							.appendTo(paymentTable)
							.append("<td >" + paymentForm + "</td>");
						});
				
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
				window.location.href = "login.jsp";
			}
		});
	}
	function makePayment(form) {
		var userId = window.localStorage.getItem('userId');
		var totalAmount = form.elements['totalAmount'].value;
		window.localStorage.setItem('totalAmount', totalAmount);
		$.ajax({
			url : "rest/market/paymentpage",
			type : "GET",
			data : "userId=" + userId + "&totalAmount=" + totalAmount,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				window.location.href = "makePayment.jsp";
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
				window.location.href = "login.jsp";
			}
		});
	}
</script>
</head>
<body onload="getCart();">
<div id="header">
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div id="headerNav" class="navbar-inner">
			<a class="brand" href="index.jsp"><span style="color: white; margin: 0px 10px 0px 10px;">Jr.Amazon</span></a>
			<ul id="loginMenu" class="nav" style=" float:right;">
				<li><a id="usernameNav" style="color: white;"></a></li>
				<li><a id="" style="color: white;">History</a></li>
				<li><a href="#" onclick="javascript:submitCartForm();"> <img src="img/shopping.jpg" alt="" height="22" width="24"></a></li>
				<li><button type="button" class="btn btn-primary" onclick="">Logout</button></li>
			</ul>
		</div>
	</div>
</div>
<br/><br/><br/><br/>
<div id="pujari"></div>
	<div id="amol" style="width: 100%;"></div>
	<div id="payment" style="width: 100%;"></div>
</body>
</html>