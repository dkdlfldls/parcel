<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/public.css'/>">

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
					<table class="table table-hover">
						<tbody class="middle-align">
							<tr>
								<td colspan="1">이름 : </td>
								<td colspan="3">현관문 택배함</td>
								<td colspan="1">개폐상태</td>
								<td colspan="1">사용가능</td>
							</tr>
							<tr>
								<td colspan="1">등록자 : </td>
								<td colspan="1">홍길동</td>
								<td colspan="1">기종</td>
								<td colspan="1">XXL사이즈 택배함</td>
								<td colspan="1">등록일</td>
								<td colspan="1">2016년 8월 17일</td>
							</tr>
							<tr>
								<td colspan="1">택배함 코드</td>
								<td colspan="5">1285ybavw89o5yxa9w3yn5fcsdx87detr</td>
							</tr>
						</tbody>
					</table>
					<br/>
					<input type="button" value="그룹만들기">
					<br/>
					<table class="table table-hover">
						<tbody class="middle-align">
							<tr>
								<td colspan="1">그룹명 :</td>
								<td colspan="3">우리집</td>
							</tr>
							<tr>
								<td colspan="1">아버지 : </td>
								<td colspan="3">홍길동</td>
							</tr>
							<tr>
								<td colspan="1">어머니</td>
								<td colspan="3">김길동</td>
							</tr>
						</tbody>
					</table>
				</div>
			<div class="col-sm-3">
				<div><!-- 사용자 메뉴 -->
						<div class="panel panel-info">
							<div class="panel-heading">로그인 정보</div>
							<div class="panel-body">
								이름 : 홍길동<br/>
								메시지 : 5건<br/>
								로그아웃하기
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-2"></div>
</div>
</body>
</html>