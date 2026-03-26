<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="search-container" style="margin-top: 20px; margin-bottom: 20px;">
			<form action="${pageContext.request.contextPath}/book/list" method="get" class="search-form">
				<select name="searchType" class="search-select">
					<option value="title" ${param.searchType=='title' ? 'selected' : '' }>도서명</option>
					<option value="author" ${param.searchType=='author' ? 'selected' : '' }>저자</option>
				</select>
				<input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요"
					class="search-input">
				<button type="submit" class="btn-search">검색</button>
			</form>
		</div>

		<c:choose>
			<c:when test="${not empty list}">
				<div class="book-grid">
					<c:forEach var="book" items="${list}">
						<a href="${pageContext.request.contextPath}/book/view?isbn=${book.isbn}" class="book-card">
							<div class="img-wrap">
								<c:choose>
									<c:when test="${not empty book.image}">
										<img src="${book.image}" alt="${book.bookname}">
									</c:when>
									<c:otherwise>
										<div class="no-img">No Image</div>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="card-info">
								<div class="card-title">${book.bookname}</div>
								<div class="card-author">${book.author} · ${book.publisher}</div>
								<div class="card-price">${book.price}원</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<div class="empty">등록된 도서가 없습니다.</div>
			</c:otherwise>
		</c:choose>