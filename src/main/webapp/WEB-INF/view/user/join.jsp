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
		<label>아이디</label><input type="text" name="id" autocomplete="off" placeholder="id"><br/>
		<label>비밀번호</label><input type="password" name="pw" placeholder="pw"><br/>
		<label>전화번호</label><input type="tel" name="phone" autocomplete="off" placeholder="phone"><br/>
		<label>이메일</label><input type="email" name="email" autocomplete="off" placeholder="email"><br/>
		<!-- 
			id,pw,phone,email
		 -->
		<input type="submit">
	</form>
</body>
</html>