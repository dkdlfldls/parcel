<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value="/resources/html/include.html"/>">

<script type="text/javascript">

$(function(){
	
	$("#join").click(function(){
		location.href="join";
	})
	
});

</script>

</head>
<body>
	<h1>login page</h1>
	<form action="/parcel_service/user/login" method="post">
		<label>id</label><input type="text" name="id" autocomplete="off" placeholder="id"><br/>
		<label>pw</label><input type="password" name="pw" placeholder="pw"><br/>
		<input type="submit" value="로그인"><input type="button" id="join" value="가입">
	</form>
	
</body>
</html>