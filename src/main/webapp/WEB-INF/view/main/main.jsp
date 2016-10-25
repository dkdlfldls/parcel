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
			<jsp:include page="../include/main_menu_nev.jsp"/> <!-- 메뉴 추가 -->
		</div>
		<div class="row">
		<br/>
			<div class="col-sm-9"><!-- 메인정보 -->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>전체선택 <input type="checkbox" value="" class="check-box-align"></th>
								<th>사용자지정 이름</th>
								<th>공동 사용자 수</th>
								<th>현재 개폐여부</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<tr>
								<td>1. <input type="checkbox" value="" class="check-box-align"></td>
								<td class="main-name">이름1</td>
								<td>3명</td>
								<td>사용가능</td>
							</tr>
							<tr>
								<td>2. <input type="checkbox" value="" class="check-box-align"></td>
								<td class="main-name">이름3</td>
								<td>0명</td>
								<td>잠금</td>
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
				<div><!-- 빠른메뉴 -->
					<div class="panel panel-success">
							<div class="panel-heading">빠른 메뉴</div>
							<div class="panel-body">
								택배함 등록하기<br/>
								선택한 택배함 그룹 열기<br/>
								선택한 택배함 그룹 닫기
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