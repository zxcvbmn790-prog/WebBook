<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div style="text-align: center; margin-bottom: 30px;">
			<%-- 현재 선택된 카테고리가 무엇인지 표시해주면 좋습니다 --%>
				<h3>현재 카테고리 : <b>${category}</b></h3>
				<br>

				<%-- 카테고리 버튼들 (새 CSV에 있는 카테고리명으로 수정해서 쓰세요!) --%>
					<button onclick="location.href='${pageContext.request.contextPath}/book/list?category=전체'">전체
						카테고리</button>
					<button
						onclick="location.href='${pageContext.request.contextPath}/book/list?category=IT/컴퓨터'">IT/컴퓨터</button>
					<button
						onclick="location.href='${pageContext.request.contextPath}/book/list?category=소설/에세이'">소설/에세이</button>
					<button
						onclick="location.href='${pageContext.request.contextPath}/book/list?category=경제/경영'">경제/경영</button>
					<button
						onclick="location.href='${pageContext.request.contextPath}/book/list?category=수험서/외국어'">수험서/외국어</button>
					<button
						onclick="location.href='${pageContext.request.contextPath}/book/list?category=일반/교양'">일반/교양</button>
		</div>


		<div class="book-list-container" style="display: flex; flex-wrap: wrap; justify-content: center; gap: 20px;">

			<c:forEach var="book" items="${list}">
				<div class="book-card" style="border: 1px solid #ccc; padding: 10px; width: 200px; text-align: center;">
					<a href="${pageContext.request.contextPath}/book/view?isbn=${book.isbn}">
						<img src="${book.image}" alt="${book.bookname}" style="width: 100%; height: auto;">
					</a>
					<h5 style="margin-top: 10px;">${book.bookname}</h5>
					<p style="color: gray; font-size: 12px;">${book.category} | ${book.author}</p>
					<p><b>${book.price}원</b></p>
				</div>
			</c:forEach>

		</div>
		<div style="text-align: center; margin-top: 40px; margin-bottom: 50px;">
			<c:choose>
				<%-- (A) 전체 보기 상태일 때는 '접기' 버튼만 노출 --%>
					<c:when test="${viewAll}">
						<button
							onclick="location.href='${pageContext.request.contextPath}/book/list?category=${category}&page=1&viewAll=false'"
							style="padding: 10px 20px; font-weight: bold;">
							접기 (4개씩 보기)
						</button>
					</c:when>

					<%-- (B) 4개씩 보기 상태일 때 --%>
						<c:otherwise>
							<%-- 이전 버튼 (1페이지가 아닐 때만 노출) --%>
								<c:if test="${currentPage > 1}">
									<button
										onclick="location.href='${pageContext.request.contextPath}/book/list?category=${category}&page=${currentPage - 1}'"
										style="padding: 10px 20px;">
										◀ 이전
									</button>
								</c:if>

								<%-- 페이지 번호 표시 (선택사항) --%>
									<span style="margin: 0 15px; font-size: 18px;"><b>${currentPage}</b> 페이지</span>

									<%-- 다음 버튼 (다음 데이터가 있을 때만 노출) --%>
										<c:if test="${hasNext}">
											<button
												onclick="location.href='${pageContext.request.contextPath}/book/list?category=${category}&page=${currentPage + 1}'"
												style="padding: 10px 20px;">
												다음 ▶
											</button>
										</c:if>

										<%-- 전체보기 버튼 --%>
											<button
												onclick="location.href='${pageContext.request.contextPath}/book/list?category=${category}&viewAll=true'"
												style="padding: 10px 20px; margin-left: 20px; background-color: #eee;">
												전체 보기
											</button>
						</c:otherwise>
			</c:choose>
		</div>