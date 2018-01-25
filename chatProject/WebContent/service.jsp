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
	margin-top: 40px;
	margin-left: 15%;
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

div.chatInput {
	margin-left: 20%;
	margin-right: 20%;
	height: 10%;
	padding-top: 5px;
}

.chatArea {
	margin-top: 5%;
	margin-bottom: 5%;
	height: 90%;
	width: 95%;
	border: none;
	resize: inherit;
	/* overflow-y: scroll; */
	overflow-x: hidden;
}

.userList {
	float: right;
	height: 40%;
	width: 15%;
	margin-top: 5%;
	margin-right: 3%;
	background-color: white;
	color: skyblue;
	border: none;
}
</style>
</head>
<body onload="init()">
	<div class="container">
		<h1 class="logo">
			S<span class="logo-in">E</span>V<span class="logo-in">E</span>NT<span
				class="logo-in">Y</span>
		</h1>
		<div class="banner shadow-left">
			<button class="button" onclick="suffleRTalk()">banner</button>
		</div>
		<div id="userList" class="userList">
		</div>
		<div class="pannel">
			<textarea id="chatArea" class="chatArea" rows="70"></textarea>
		</div>
		<div class="chatInput">
			<form style="height: 100%; width: 100%">
				<input type="text" class="shadow-right" style="height: 30px; width: 65%;" id="msg">
				<input type="submit" class="shadow-right button" style="height: 35px; width: 30%;" class="button" value="Enter" onclick="enrollMsg()">
			</form>
		</div>
		<button onclick="refreshChats()">test</button>
	</div>

	<script type="text/javascript">
		function init() {
			document.getElementById("msg").focus();
			refreshChats();
		};
	
		function suffleRTalk() {
			xhttp1 = new XMLHttpRequest();
			xhttp1.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("chatArea").value = "";
				}
			};
			xhttp1.open("GET", "rtalk", true);
			xhttp1.send();
		};

		function enrollMsg() {
			xhttp2 = new XMLHttpRequest();
			xhttp2.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
				}
			};
			xhttp2.open("POST", "enroll", true);
			xhttp2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp2.send("msg=" + document.getElementById("msg").value);
		};
		
		function refreshChats() {
			xhttp3 = new XMLHttpRequest();
			xhttp3.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					jsonData = eval("(" + this.responseText + ")");
					chatArray = jsonData.chats;
					for (idx in chatArray) {
						document.getElementById("chatArea").value += "[" + chatArray[idx].userNick + "] : " + chatArray[idx].msg + "\n";
					}
				}
			};
			xhttp3.open("GET", "refresh", true);
			xhttp3.send();
		}
	</script> 
</body>
</html>