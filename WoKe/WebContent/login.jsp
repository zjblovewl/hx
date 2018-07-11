<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>沃客营销后台登录系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->

<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/login.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/tooltips.js"></script>
</head>
<body>
	<div class="page-container">
		<div class="main_box">
			<div class="login_box">
				<div class="login_logo">
					<img src="images/logo.png">
				</div>
				<div class="login_form">
					<form action="login" id="login_form" method="post">
						<div class="form-group">
							<label for="j_username" class="t">账&nbsp 号：</label> <input name="username"
								type="text" class="form-control x319 in" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="j_username" class="t">密 &nbsp码：</label> <input name="password"
								type="password" class="form-control x319 in" autocomplete="off">
						</div>
						<div class="form-group space">
							<label class="t"></label>
							<button type="submit" 
								class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp</button>&nbsp
							<input type="reset" value="&nbsp;重&nbsp;置&nbsp;"
								class="btn btn-default btn-lg">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Javascript -->

	<script src="js/supersized.3.2.7.min.js"></script>
	<script src="js/supersized-init.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>