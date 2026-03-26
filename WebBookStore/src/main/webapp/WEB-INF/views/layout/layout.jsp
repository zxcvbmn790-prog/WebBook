<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
			<link rel="stylesheet" href="/css/style.css">
		</head>

		<body>
			<header>
				<jsp:include page="/WEB-INF/views/common/header.jsp" />
			</header>

			<main>
				<c:choose>
					<c:when test="${not empty contentPage}">
						<jsp:include page="${contentPage}" />
					</c:when>
					<c:otherwise>
						<div
							style="padding: 150px; text-align: center; color: var(--red); font-family: 'DM Sans', sans-serif;">
							<p style="margin-top: 20px; color: var(--text); font-size: 16px;">
								<strong><code>model.addAttribute("contentPage", "/WEB-INF/views/...");</code></strong><br>
							</p>
						</div>
					</c:otherwise>
				</c:choose>
			</main>

			<footer class="d-flex align-items-center justify-content-center">
				<jsp:include page="/WEB-INF/views/common/footer.jsp" />
			</footer>

			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
		</body>

		</html>