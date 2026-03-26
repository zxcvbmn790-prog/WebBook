<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="wrapper">
			<c:choose>
				<c:when test="${not empty book}">

						<div class="cover-frame">
							<c:choose>
								<c:when test="${not empty book.image}">
									<img src="${book.image}" alt="${book.bookname}">
								</c:when>
								<c:otherwise>
									<div class="no-cover">No Image</div>
								</c:otherwise>
							</c:choose>
						</div>

							<div class="info-col">
								<span class="tag">도서 상세</span>

								<div class="book-title">${book.bookname}</div>
								<div class="book-author">${book.author} 저</div>

								<hr class="divider">

								<div class="meta-list">
									<div class="meta-row">
										<span class="meta-label">출판사</span>
										<span class="meta-value">${book.publisher}</span>
									</div>
									<div class="meta-row">
										<span class="meta-label">ISBN</span>
										<span class="meta-value">${book.isbn}</span>
									</div>
								</div>

								<div class="price-block">
									<span class="price-label">판매가</span>
									<div class="price-value">
										<c:choose>
											<c:when test="${not empty book.price}">
												${book.price}<span class="price-unit">원</span>
											</c:when>
											<c:otherwise>
												<span
													style="font-size:20px; color:var(--text-muted); font-family:'DM Sans',sans-serif; font-weight:300;">가격
													미정</span>
											</c:otherwise>
										</c:choose>
									</div>
								</div>

								<form action="${pageContext.request.contextPath}/cart/insert" method="post"
									class="btn-group">
									<input type="hidden" name="isbn" value="${book.isbn}">
									<input type="hidden" name="userid" value="${sessionScope.loginUser}">
									<input type="hidden" name="amount" value="1">

									<a href="${pageContext.request.contextPath}/book/list" class="btn btn-back">← 목록</a>

										<c:choose>
											<c:when test="${sessionScope.loginUser eq 'admin'}">
												<a href="${pageContext.request.contextPath}/admin/updateform?isbn=${book.isbn}"
													class="btn btn-cart" style="text-decoration:none;">도서 수정</a>
												<a href="${pageContext.request.contextPath}/admin/delete?isbn=${book.isbn}"
													class="btn btn-buy" style="text-decoration:none;"
													onclick="return confirm('정말 이 책을 삭제하시겠습니까?');">도서 삭제</a>
											</c:when>

												<c:otherwise>
													<button type="submit" class="btn btn-cart">장바구니</button>
													<button type="button" class="btn btn-buy">바로구매</button>
												</c:otherwise>
										</c:choose>
								</form>
							</div>

				</c:when>
				<c:otherwise>
					<div class="not-found">
						<p>해당 도서를 찾을 수 없습니다.</p>
						<a href="${pageContext.request.contextPath}/book/list" class="btn btn-back">← 목록으로</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>