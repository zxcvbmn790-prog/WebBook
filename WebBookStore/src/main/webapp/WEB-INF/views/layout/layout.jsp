<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<title>BOOK FOREST</title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		</head>

		<body>

			<header class="site-header">
				<jsp:include page="/WEB-INF/views/common/header.jsp" />
			</header>

			<main class="site-main">
				<c:choose>
					<c:when test="${not empty contentPage}">
						<jsp:include page="${contentPage}" />
					</c:when>
					<c:otherwise>
						<div class="empty-page">
							페이지가 준비되지 않았습니다.
						</div>
					</c:otherwise>
				</c:choose>
			</main>

			<!-- 사용자 팝업 채팅 include -->
			<jsp:include page="/WEB-INF/views/chat/user_chat_popup.jsp" />
			<footer class="site-footer">
				<jsp:include page="/WEB-INF/views/common/footer.jsp" />
			</footer>

		</body>
		<script>
			/* layout.jsp 하단 */
			function directBuy(isbn, amountId) {
				const loginUser = '${sessionScope.loginUser}';

				let amount = 1;
				if (amountId) {
					const qtyInput = document.getElementById(amountId);
					if (qtyInput) amount = qtyInput.value;
				}

				if (!loginUser || loginUser === "") {
					alert("로그인이 필요한 서비스입니다.");
					location.href = "${pageContext.request.contextPath}/member/login";
					return;
				}

				location.href = "${pageContext.request.contextPath}/order/checkout?isbn=" + isbn + "&amount=" + amount + "&buyNow=true";
			}
		</script>

		</html>