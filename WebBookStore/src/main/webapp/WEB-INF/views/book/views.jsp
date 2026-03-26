<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="search-container">
			<form action="${pageContext.request.contextPath}/book/list" method="GET" class="search-form">
				<select name="searchType" class="search-select">
					<option value="title" ${param.searchType=='title' ? 'selected' : '' }>도서명</option>
					<option value="author" ${param.searchType=='author' ? 'selected' : '' }>저자</option>
				</select>

				<input type="text" name="keyword" class="search-input" placeholder="검색어를 입력하세요"
					value="${param.keyword}">

				<button type="submit" class="btn-search">검색</button>
			</form>
		</div>

		<div class="book-grid">
			<c:choose>
				<c:when test="${not empty list}">
					<c:forEach var="book" items="${list}">
						<a class="book-card" href="${pageContext.request.contextPath}/book/view?isbn=${book.isbn}">
							<div class="img-wrap">
								<c:choose>
									<c:when test="${not empty book.image}">
										<img src="${book.image}" alt="${book.bookname}">
									</c:when>
									<c:otherwise>
										<span class="no-img">No Image</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="card-info">
								<div class="card-title">${book.bookname}</div>
								<div class="card-author">${book.author} · ${book.publisher}</div>
								<div class="card-price">
									<c:choose>
										<c:when test="${not empty book.price}">${book.price}원</c:when>
										<c:otherwise>가격 미정</c:otherwise>
									</c:choose>
								</div>
							</div>
						</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="empty">등록된 도서가 없습니다.</div>
				</c:otherwise>
			</c:choose>
		</div>

		<script>
			function logoutSubmit() {
				if (confirm("로그아웃 하시겠습니까?")) {
					document.getElementById('logoutForm').submit();
				}
			}
		</script>