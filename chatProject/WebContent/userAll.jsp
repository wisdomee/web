<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="util.TimeParser"%>





<table  style="width:100%">
	<tr>
		<th>ID</th><th>PW</th><th>NICKNAME</th><th>ACESSTIME</th><th>LEFTTIME</th>
	</tr>
	<c:forEach items="${requestScope.userAll}" var="dataAll"> 
 		<tr>
 			<td>${dataAll.id}</td>
 			<td>${dataAll.pw}</td>
 			<td>${dataAll.nickName}</td>
 			<td>TimeParser.getsignUpDate(${dataAll.lastAccessTime})</td>
 			<td>TimeParser.getLeftHour(${dataAll.lastAccessTime})
 		</tr>
 	</c:forEach> 

</table>


