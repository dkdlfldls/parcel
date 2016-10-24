<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>join page</h1>
	<form action="/parcel_service/user/join" method="post">
		<label>id</label><input type="text" autocomplete="off" placeholder="id"><br/>
		<label>id</label><input type="password" placeholder="pw"><br/>
		<!-- 
			id,pw,phone,email
		 -->
		<input type="submit">
	</form>
</body>
</html>