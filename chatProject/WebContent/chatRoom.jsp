<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<table style="width: 100%">
	<tr>
		<th>RoomId</th>
		<th>UserNum</th>
		<th>UserList</th>
	
	</tr>
	<c:forEach items="${requestScope.chatRoom}" var="dataAll">
		<tr>
			<td>${dataAll.roomId}</td>
			<td>${dataAll.userNum}</td>
			<c:forEach items="${dataAll.userList}" var="listAll">
			<td>${listAll.id}</td>
			</c:forEach>
		</tr>
	</c:forEach>


</table>
