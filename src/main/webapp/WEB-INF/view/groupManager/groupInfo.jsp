<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/public.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/groupInfo.css'/>">

</head>
<body>



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/>
		</div>
		<div class="row">
				<div class="col-sm-9">
					<!-- 메인정보 -->
					<div class="panel-default">
						<div class="panel-heading">
							<ul class="list-group">
								<li class="list-group-item group-info-list">
									<table class="table table-bordered group-info-table">
										<tbody>
											<tr>
												<td>그룹 이름 : 그룹 1</td>
												<td>정보</td>
												<td>관계</td>
												<td>그룹 제거</td>
											</tr>
											<tr>
												<td>그룹 이름 : 그룹 2</td>
												<td>정보</td>
												<td>관계</td>
												<td>그룹 제거</td>
											</tr>
										</tbody>
									</table>
								</li>
							</ul>
						</div>
					</div>
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