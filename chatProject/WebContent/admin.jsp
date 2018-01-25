<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.RoomBean"%>
<%@page import="bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEVENTY</title>
<style>
div.container {
	text-align: center;
	height: 840px;
	width: 100%;
}

.logo {
	color: skyblue;
	width: 60%;
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

div.banner {
	float: left;
	width: 5%;
	height: 40px;

}

.shadow-left {
	box-shadow: -2px 2px 6px grey;
}
.shadow-left:hover {
	box-shadow: -4px 4px 6px grey;
}
.shadow-right {
	box-shadow: 2px 2px 6px grey;
}
.shadow-right:hover {
	box-shadow: 4px 4px 6px grey;
}

div.banner button {
	height: 100%;
	width: 100%;
}

div.pannel {
	text-align: center;
	height: 80%;
	margin-left: 20%;
	margin-right:20%;
	background-color: white;
	border: 3px solid skyblue;
}

.chatArea {
	margin-top: 5%;
	margin-bottom: 5%;
	height: 90%;
	width: 95%;
	border: none;
	resize: none;
	overflow-y: scroll;
	overflow-x: hidden;
}



</style>
</head>
<body>
	<div class="container">
		<h1 class="logo">
			S<span class="logo-in">E</span>V<span class="logo-in">E</span>NT<span
				class="logo-in">Y</span>
		</h1>
		<div class="banner shadow-left" style="margin-top: 40px; 	margin-left: 15%">
			<button class="button" onclick="selectAll('userAll')">모든 회원</button>
		</div>
		<div class="banner shadow-left" style="margin-top: 100px; margin-left: -5%;">
		<button class="button" onclick="selectAll('userList')">접속 회원</button>
		</div>
		<div class="banner shadow-left" style="margin-top: 160px; margin-left: -5%;">
		<button class="button" onclick="selectAll('chatRoom')">개설 목록</button>
		</div>
		<div class="pannel">
			<div id="chatArea" class="chatArea" ></div>
		</div>
	</div>

	<script type="text/javascript">
		var xhttp;
	
		function selectAll(v) {
			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("chatArea").innerHTML= this.responseText;
				}
			};
			xhttp.open("POST", "admin?command="+v, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("command="+v);
		
		}




	</script> 
</body>
</html>