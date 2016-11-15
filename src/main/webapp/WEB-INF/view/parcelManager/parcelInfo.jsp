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


var popover_hide_function = function(){
	$("[data-toggle=popover]").popover("hide");
}

var group_delete_request = function() {
	$.ajax({
		url : "../group/deleteGroup",
		contentType : "application/json",
		type : "post",
		data : JSON.stringify({
			idx : "${group.idx}",
			pw : $("#check_pw_for_group_delete").val()
		}),
		success : function(data, status) {
			if (data == 2) {
				alert("그룹을 삭제하였습니다.");
				location.href= "";
				
			} else if (data == 1) {
				alert("에러가 발생하였습니다.");
			} else {
				alert("비밀번호를 잘못 입력하였습니다.");
			}
		}
	});
}

$(function(){
	
	$("[data-toggle=popover]").popover({
	     html : true,
	     content: $("#popover_content").html()
	 });
	
	
	$("#do_lock").click(function(){
		//1. ajax로 잠그기 요청 (자신idx, product idx 전송)
		$.ajax({
			url : "lock",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				idx : "${userEntity.idx}",
				productIdx : "${product.idx}",
				hasGroup : "${hasGroup}",
				productName : "${product.public_name }"
			}),
			success: function(data, status){
				if (data == true) {
					alert("잠그기 성공");
					location.href="";
				} else {
					alert("잠그기 실패");
				}
			}
		});
		//2.잘 잠겼다고 답 오면 페이지 재접속
	});
	$("#do_open").click(function(){
		//1. ajax로 열기 요청 (자신idx, product idx 전송)
		$.ajax({
			url : "open",
			contentType : "application/json",
			type : "post",
			data : JSON.stringify({
				idx : "${userEntity.idx}",
				productIdx : "${product.idx}",
				hasGroup : "${hasGroup}",
				productName : "${product.public_name }"
			}),
			success : function(data, status) {
				if (data == true) {
					alert("열기 성공");
					location.href="";
				} else {
					alert("열기 실패");
				}
			}
		});
			//2.잘 열렸다고 답 오면 페이지 재접속
		});

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
						<button type="button" class="btn btn-info" data-toggle="modal" data-target="#group_add_modal">그룹 만들기</button>
						<c:if test="${product.is_open eq 0 }"><input type="button" id="do_lock" class="btn btn-info" value="잠그기"></c:if>
						<c:if test="${product.is_open ne 0 }"><input type="button" id="do_open" class="btn btn-info" value="열기"></c:if>
						<br/>
						그룹 없음
					</c:if>
					<c:if test="${hasGroup eq true}">
						<c:if test="${product.registrant_name eq userEntity.name}">
							<input type="button" id="delete_group" class="btn btn-info"
								data-toggle="popover" title="그룹 없애기 확인" value="그룹 없애기"
								data-content="" html="true">
						</c:if>
						<c:if test="${product.is_open eq 0 }"><input type="button" id="do_lock" class="btn btn-info" value="잠그기"></c:if>
						<c:if test="${product.is_open ne 0 }"><input type="button" id="do_open" class="btn btn-info" value="열기"></c:if>
						<br/>
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