<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" type="text/css" />
<link href="style.css" rel="stylesheet" media="screen" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>


<script type="text/javascript">
	function addItem() {
		var productName = document.getElementById("productName").value;
		alert(productName);
		var productCategory = document.getElementById("productCategory").value;
		alert(productCategory);
		var productPrice = document.getElementById("productPrice").value;
		alert(productPrice);
		var productDesc = document.getElementById("productDesc").value;
		alert(productDesc);
		var productCount = document.getElementById("productCount").value;
		alert(productCount);
		$.ajax({
			url : "rest/market/addItem",
			type : "POST",
			data : "productName=" + productName + "&productCategory=" + productCategory + "&productPrice=" + productPrice+ "&productDesc=" + productDesc + "&productCount=" + productCount,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				alert("success");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
				window.location.href = "login.jsp";
			}
		});
	}
</script>
</head>
<body>

	<div id="header">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div id="headerNav" class="navbar-inner">
				<a class="brand" href="index.jsp"><span style="color: white; margin: 0px 10px 0px 10px;">Jr.Amazon</span></a>
				<ul id="loginMenu" class="nav" style="float: right;">
					<li><a id="usernameNav" style="color: white;"></a></li>
					<li><a id="" style="color: white;">Delete Items</a></li>
					<li><button type="button" class="btn btn-primary" onclick="">Logout</button></li>
				</ul>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div align="center">
		<table class="table" align="center" style="width: 55%">
			<tr align="center">
				<td align="center">Product Name :</td>
				<td><input type="text" size="12" width="50px;" id="productName"/></td>
			</tr>
			<tr align="center">
				<td align="center">Product Category :</td>
				<td><input type="text" size="12" width="50px;" id="productCategory"/></td>
			</tr>
			<tr align="center">
				<td align="center">Product Count :</td>
				<td><input type="text" size="12" width="50px;" id="productCount"/></td>
			</tr>
			<tr align="center">
				<td align="center">Product Price :</td>
				<td><input type="text" size="12" width="50px;" id="productPrice"/></td>
			</tr>
			<tr align="center">
				<td align="center">Product Description :</td>
				<td><input type="text" size="12" width="50px;" id="productDesc"/></td>
			</tr>
			<tr align="center" style="height:90px">
				<td >
					
				</td>
				<td >
					<input type="button" class="btn btn-success btn-lg btn-block" value="Add Item" onclick="javascript:addItem(this.form);" style="height:40px;width: 34%;" />
				</td>
			</tr>
		</table>

	</div>
</body>
</html>