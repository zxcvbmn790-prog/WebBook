<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="logo">
			<a href="${pageContext.request.contextPath}/book/list"
				style="text-decoration:none; color:inherit;">BookList</a>
		</div>

		<nav>
			<a href="${pageContext.request.contextPath}/book/list">전체 목록</a>

			<c:choose>
					<c:when test="${not empty sessionScope.loginUser}">

						<c:choose>
								<c:when test="${sessionScope.loginUser eq 'admin'}">
									<a href="${pageContext.request.contextPath}/admin/insertform"
										style="color: var(--red); font-weight: 500;">
										<i class="fa-solid fa-gear"></i> 도서 등록
									</a>
								</c:when>

									<c:otherwise>
										<a href="${pageContext.request.contextPath}/cart/list"
											style="color: var(--accent); font-weight: 500;">
											<i class="fa-solid fa-cart-shopping"></i> 장바구니
										</a>
									</c:otherwise>
						</c:choose>

						<span style="font-size: 13px; color: var(--text);"><b>${sessionScope.loginUser}</b>님
							환영합니다!</span>
						<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>

					</c:when>

						<c:otherwise>
							<a href="${pageContext.request.contextPath}/member/login">로그인</a>
						</c:otherwise>
			</c:choose>
		</nav>