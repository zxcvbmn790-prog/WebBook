<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<header>
			<div class="logo">Booklist</div>
			<nav>
				<a href="${pageContext.request.contextPath}/user/list">전체 목록</a>

				<c:choose>
					<c:when test="${not empty sessionScope.loginUser}">
						<span style="font-size: 13px; color: var(--text); margin-right: 10px;">
							<strong>${sessionScope.loginUser}</strong>님 환영합니다!
						</span>

						<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
					</c:when>

					<c:otherwise>
						<a href="${pageContext.request.contextPath}/member/login">로그인</a>
					</c:otherwise>
				</c:choose>
			</nav>
		</header>