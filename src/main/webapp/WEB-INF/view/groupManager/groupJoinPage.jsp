<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/public.css'/>">

<script type="text/javascript">

$(function(){
	if ("${msg}" != "") {
		alert("${msg}");
	}
	
});

</script>

</head>
<body>



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/>
		</div>
		<div class="row">
			<div class="col-sm-9"><!-- 메인정보 -->
					<br/>
					<form action="/parcel_service/group/joinGroup" method="post">
						<div class="form-group">
							<label for="code">그룹 코드:</label> 
							<input type="text" class="form-control" id="code" name="code" autocomplete="off" placeholder="그룹 코드">
						</div>
						<div class="form-group">
							<label for="pw">그룹 비밀번호:</label> 
							<input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호">
						</div>
						<div class="form-group right-align">
							<button type="submit" class="btn btn-default">가입하기</button>
							<a href="/parcel_service/main" class="btn btn-default">돌아가기</a>
						</div>
					</form>
				</div>
			<div class="col-sm-3">
				<div><!-- 사용자 메뉴 -->
					<jsp:include page="../include/simpleUserInfo.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-2"></div>
</div>
</body>
</html>