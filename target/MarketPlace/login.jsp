<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen" type="text/css">
<link href="bootstrap/bootstrap.css" rel="stylesheet" media="screen" type="text/css">
<link href="bootstrap/bootstrap-responsive.css" rel="stylesheet" media="screen" type="text/css">
<link href="bootstrap/bootstrap-responsive.min.css" rel="stylesheet" media="screen" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-dropdown.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-alert.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-button.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-carousel.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-scrollspy.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-modal.js" /></script>

<script type="text/javascript">
	function userLogin() {
		var email = $('#email').val();
		var password = $('#password').val();
		$.ajax({
			url : "rest/users/login",
			type : "POST",
			data : "email=" + email + "&password=" + password,
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				window.localStorage.setItem('userId', data.userId);
				window.localStorage.setItem('firstName', data.firstName);
				window.localStorage.setItem('lastName', data.lastName);
				window.localStorage.setItem('lastLogin', data.loginTime);
				
				console.log(window.localStorage.getItem('userId'));
				console.log(window.localStorage.getItem('firstName'));
				console.log(window.localStorage.getItem('lastName'));
				console.log(window.localStorage.getItem('lastLogin'));
				
				window.location.href = "home.jsp";
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
	<%@include file="layout/header.jsp"%>
	<div id="container" style="padding-top: 40px;" align="center">
		<div class="container-fluid">
			<div id="loginOptions" style="margin: 80px 0px 0px 50px;">
				<div></div>
				<div class="span5" style="margin-right: -30px;">
					<div
						style="background-color: ghostwhite; -webkit-box-shadow: 3px 0px 5px #888888; -moz-box-shadow: 3px 0px 5px #888888; box-shadow: 3px 0px 5px #888888; padding: 30px;">
						<h3>Sign In</h3>
						<table>
							<tr>
								<td><label for="inputEmail3" class="col-sm-2 control-label">Email</label></td>
								<td><div class="col-sm-10">
										<input type="email" class="form-control" id="email" placeholder="Your registered email"
											>
									</div></td>
							</tr>
							<tr>
								<td><label for="inputPassword3" class="col-sm-2 control-label">Password</label></td>
								<td><div class="col-sm-10">
										<input type="password" class="form-control" id="password" placeholder="Password">
									</div></td>
							</tr>
							<tr>
								<td></td>
								<td><div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary" style="margin-left: 45px" id="login" onclick="userLogin()">Login</button>
									</div></td>
							</tr>
						</table>
					</div>
				</div>

				<div class="divider-vertical"></div>
				<div class="span1" style="margin-top: 80px;">
					<h3>OR</h3>
				</div>
				<div class="divider-vertical"></div>

				<div class="span5" style="height: 240px">
					<div
						style="background-color: ghostwhite; height: 180px; -webkit-box-shadow: 3px 0px 5px #888888; -moz-box-shadow: 3px 0px 5px #888888; box-shadow: 3px 0px 5px #888888; padding: 30px;">
						<h4>Don't have an account!</h4>
						<button class="btn btn-large btn-success" type="button" onclick="window.location.href='signup.jsp'">Sign
							up</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="cnt" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã—</button>
			<h3 id="myModalLabel">Modal header</h3>
		</div>
		<div class="modal-body">
			<p>One fine body¦</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary">Save changes</button>
		</div>
	</div>
</body>
</html>



