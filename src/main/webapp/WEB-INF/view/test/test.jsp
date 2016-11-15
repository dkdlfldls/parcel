<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/public.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/parcelInfo.css'/>">


</head>
<body>



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/>
		</div>
		<div class="row">
		<br/>
			<div class="col-sm-9"><!-- 메인정보 -->
					TEST
					
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

<jsp:include page="../include/group_add_modal.jsp"></jsp:include>
<div style="display: none;" id="popover_content">
		<div class="form-group">
			<label for="machine_code">택배함 비밀번호 확인 :</label> 
			<input type="password" class="form-control" id="check_pw_for_group_delete">
		</div>
		<div>
			<input type="button" class="btn btn-info" value="그룹 삭제" onclick="group_delete_request()">
			<input type="button" class="btn btn-danger" value="삭제 취소" onclick="popover_hide_function()">
		</div>
</div>
</body>
</html>