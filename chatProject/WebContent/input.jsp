<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SEVENTY</title>
	<style>
	div.container {
		height: 50%;
		width: 100%;
		padding-top: 15%;
	}
	div.login {
	    text-align: center;
		height: 100%;
		width: 50%;
		padding-left: 25%;
		padding-right: 25%;
	}
	.login-form {
		width: 30%;
		height: 30px;
	    box-shadow: 2px 2px 6px grey;
	}
	.login-form:hover {
	    box-shadow: 4px 4px 6px grey;
	}
	.logo {
		color: skyblue;
		width: 60%;
		height: 20%;
		margin-left: 20%;
		margin-right: 20%;
		cursor: context-menu;
	}
	.logo-in {
		color: #87ceeb82;
	}
	.button {
		background-color: skyblue;
		color: white;
		border: none;
	}
	#error {
		color: #f59b5a;
		width: 40%;
		height: 20%;
		margin-left: 30%;
		margin-right: 30%;
		cursor: context-menu;
		display: none;
	}
	</style>
</head>
<body>
	<div class="container">
		<div class="login">
			<h1 class="logo">S<span class="logo-in">E</span>V<span class="logo-in">E</span>NT<span class="logo-in">Y</span></h1>
			<form action="login" method="post">
				<input type="text" id="login-id" class="login-form" name="id" placeholder=" ID" required><br><br>
				<input type="password" id="login-pw" class="login-form" name="pw" placeholder=" Password" required><br><br>
				<input type="submit" class="login-form button" value="ENTER">
			</form>
			<h2 id="error" style="display:${sessionScope.user}">INVALID PASSWORD</h2>
			<div id="test"></div>
		</div>
	</div>
</body>
</html>