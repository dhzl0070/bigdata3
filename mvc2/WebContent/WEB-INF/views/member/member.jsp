<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<style type="text/css">
	body{
		font-size: 12px;
	}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
-회원가입-
	<form action="${cpath}/insert.do" method="post">
		<table border = "0">
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name" /></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type = "text" name = "phone" /></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type = "text" name = "addr" size = "50" />
					<input type ="button" value="위도경도구하기"/> 
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "가입"/>
					<input type = "reset" value = "취소"/>
				</td>
			
			</tr>
		</table>
	
	</form>
</body>
</html>