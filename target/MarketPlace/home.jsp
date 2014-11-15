<!DOCTYPE html>
<html lang="en">
<!-- HEAD  -->
<head>
<!-- Script -->

<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" type="text/css" />
<link href="style.css" rel="stylesheet" media="screen" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>

<script type="text/javascript">
	function placeOrder(form) {
		var quantity = form.elements['quantityCart'].value;
		var itemId = form.elements['itemId'].value;
		var itemPrice = form.elements['itemPrice'].value;
		var userId = window.localStorage.getItem('userId');
		$.ajax({
			url : "rest/market/addtocart",
			type : "POST",
			data : "userId=" + userId + "&quantity=" + quantity + "&itemId=" + itemId + "&itemPrice=" + itemPrice,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				alert("success");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
				window.location.href = "home.jsp";
			}
		});
	}
	
	function getDetails() {
		console.log(localStorage.getItem('userId'));
		console.log(window.localStorage.getItem('firstName'));
		console.log(window.localStorage.getItem('lastName'));
		console.log(window.localStorage.getItem('lastLogin'));

		var firstName = window.localStorage.getItem('firstName');
		var lastName = window.localStorage.getItem('lastName');
		var lastLogin = window.localStorage.getItem('lastLogin');

		var userId = window.localStorage.getItem('userId');
		$.ajax({
			url : "rest/market/productlist",
			type : "GET",
			data : "userId=" + userId,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				$(document).ready(
						function() {
							var content = "";
							
							$(data).each(
									
									function(i, data) {
										
										var formData = " <form action=\"#\" method=\"post\"> " + " 	<input type=\"text\" name=\"quantityCart\" id=\"quantityCart\" value=\"1\" size=\"3\" align=\"middle\"/> "
												+ " 	<input type=\"hidden\" name=\"itemId\" id=\"itemId\" value=" + data.id + "> " + " 	<input type=\"hidden\" name=\"itemPrice\" id=\"itemPrice\" value=" + data.price + "> "
												+ " 	<br/><input type=\"button\" class=\"btn btn-primary\" value=\"Add to Cart\" onclick=\"javascript:placeOrder(this.form);\"> " + " </form> "

												content = content +"<div class='itemContainer'><div class='itemStyle'><li><b>Name : </b>"+data.name +
												"</li><li><b>Description : </b>"+ data.description
												+"</li><li><b>Price : </b>"+ data.price
												+"</li><li><b>Quantity : </b>"+ data.count
												+"</li>"+
												"<li>"+formData+"</li></div></div>"
												$("#pujari").html(content);		
												
												
									});
						});
				$('#id3').html("Last Logged in " + window.localStorage.getItem('lastLogin'));
				
				document.getElementById("usernameNav").innerHTML = window.localStorage.getItem('firstName') + " " + window.localStorage.getItem('lastName');
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
				window.location.href = "login.jsp";
			}
		});
	}
	function submitCartForm(cartForm) {
		$.ajax({
			url : "rest/market/cartpage",
			type : "GET",
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				window.location.href = "cart.jsp";
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
				window.location.href = "login.jsp";
			}
		});
	}
</script>
<!-- script end -->
</head>
<!--  head end-->
<!--  body start-->
<body onload="getDetails();">

<div id="header">
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div id="headerNav" class="navbar-inner">
			<a class="brand" href="index.jsp"><span style="color: white; margin: 0px 10px 0px 10px;">Jr.Amazon</span></a>
			<ul id="loginMenu" class="nav" style=" float:right;">
				<li><a id="usernameNav" style="color: white;"></a></li>
				<li><a id="history" href="history.jsp" style="color: white;">History</a></li>
				<li><a href="#" onclick="javascript:submitCartForm();"> <img src="img/shopping.jpg" alt="" height="22" width="24"></a></li>
				<li><button type="button" class="btn btn-primary" href="logout.jsp">Logout</button></li>
			</ul>
		</div>
	</div>
</div>














<br/><br/><br/>








	<!-- Links -->
	<div>
		<table style="width: 100%;">
			<tr>
				<td align="left" width="50%"><a href="#" onclick="javascript:submitHistoryForm();"></a>
					<form action="showUserHistory" method="post" name="userHistory" id="userHistory"></form></td>
				<td align="right" width="50%"><div id="id3"></div></td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<!--  Links-->
	<!-- jquery table -->
	<div id="pujari"></div>
	<!-- jquery table end -->
</body>
<!-- body end -->
</html>