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

<script type="text/javascript">

$(function(){
	$("#delete_group").click(function(){
		//우선 팝업을 띄워서 비밀번호부터 입력하고 맞으면 다음으로 진행 틀리면 ㅂㅂ해야겠다.
		
		
			$.ajax({
				url : "/parcel_service/group/deleteGroup",
				contentType : "application/json",
				type : "post",
				data : JSON.stringify({
					idx : "${group.idx}",
					pw : "${group.pw}"
				}),
				success : function(data, status) {
					if (data == "실패") {
						alert("그룹삭제에 실패하였습니다");
					} else {
						alert("그룹을 삭제하였습니다.");
						location.href="";
					}
				}
			});
	})
})

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
		<br/>
			<div class="col-sm-9"><!-- 메인정보 -->
					<table class="table table-hover table-bordered">
						<tbody class="middle-align">
							<tr>
								<td class="success 15per">이름 : </td>
								<td>${product.public_name }</td>
								<td class="success 15per">개폐상태</td>
								<td>
									<c:if test="${product.is_open eq 0 }">사용가능</c:if>
									<c:if test="${product.is_open ne 0 }">사용중</c:if>
								</td>
							</tr>
							<tr>
								<td class="success 15per">등록자 : </td>
								<td>${product.registrant_name }</td>
								
								<td class="success 15per">등록일</td>
								<td>
									<fmt:formatDate value="${product.registration_date }" pattern="yyyy년 MM월 dd일"/>
								</td>
							</tr>
							<tr>
								<td class="success 15per">기종</td>
								<td>${product.machine_name }</td>
								<td class="success 15per">택배함 코드</td>
								<td>${product.machine_code }</td>
							</tr>
						</tbody>
					</table>
					<br/>
					
					
					<c:if test="${hasGroup eq false}">
						<button type="button" class="btn btn-info" data-toggle="modal" data-target="#group_add_modal">그룹 만들기</button><br/>
						그룹 없음
					</c:if>
					<c:if test="${hasGroup eq true}">
						<input type="button" value="그룹 없애기" id="delete_group"><br/>
						그룹명 : ${group.group_name }<br/>
						pw : ${group.pw }<br/>
						code : ${group.code }<br/>
						idx : ${group.idx }<br/>
						<div class="row">
							<div class="col-sm-2">그룹원</div>
							<div class="col-sm-3">
								<c:forEach var="user" varStatus="status" items="${group.groupUserList }">
									${status.count }.${user.name }<br/>
								</c:forEach>
							</div>
							<div class="col-sm-7"></div>
						</div>
					</c:if>
					
					
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
</body>
</html>