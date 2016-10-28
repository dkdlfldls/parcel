<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/public.css'/>">

<script type="text/javascript">
	var checkMessage = function(num) {
		$.ajax({
			url : "/parcel_service/message/check",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				idx : num
			}),
			success : function(data, status){
				if(data == "success") {
					$("#show" + num).text("확인함");
					$("#check" + num).text("");
				} else {
					alert("확인 실패");
				}
			}
			
		});
	}
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
				<c:if test="${isEmpty eq true }">받은 메시지가 없습니다.</c:if>
				<c:if test="${isEmpty eq false }">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>메시지</th>
								<th>받은시간</th>
								<th>확인여부</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" varStatus="status" items="${messageList }">
								<tr>
									<td>${status.count }. </td>
									<td>${i.message }</td>
									<td>${i.send_time }</td>
									<td id="show${i.idx }">
										<c:if test="${i.show eq 0}">미확인</c:if>
										<c:if test="${i.show ne 0}">확인함</c:if>
									</td>
									<td id="check${i.idx }">
										<c:if test="${i.show eq 0}"><a href="#" onclick="checkMessage(${i.idx})">확인</a></c:if>
										<c:if test="${i.show ne 0}"></c:if>
										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
</body>
</html>