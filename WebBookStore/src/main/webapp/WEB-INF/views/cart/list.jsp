<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="cart-container">
			<div style="font-family: 'DM Serif Display', serif; font-size: 28px; margin-bottom: 20px;">장바구니</div>

			<c:choose>
				<c:when test="${not empty cartList}">
					<table class="cart-table">
						<thead>
							<tr>
								<th>도서 정보</th>
								<th>단가</th>
								<th>수량</th>
								<th>합계</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cart" items="${cartList}">
								<tr>
									<td>
										<div class="cart-book-info">
											<img src="${cart.image}" class="cart-book-img" alt="표지">
											<span class="cart-book-title">${cart.bookname}</span>
										</div>
									</td>

									<td>${cart.price}원</td>

									<td>${cart.amount}권</td>

									<td style="font-weight: 500;">${cart.totalPrice}원</td>

									<td><button class="btn btn-back"
											style="padding: 6px 12px; font-size: 11px;">삭제</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="cart-summary">
						총 결제 금액 : ${sumMoney} 원
					</div>

					<div
						style="text-align: center; margin-top: 40px; display: flex; justify-content: center; gap: 10px;">
						<a href="${pageContext.request.contextPath}/book/list" class="btn btn-back">쇼핑 계속하기</a>
						<a href="#" class="btn btn-buy" style="padding: 13px 40px;">결제하기</a>
					</div>
				</c:when>

				<c:otherwise>
					<div class="empty" style="padding: 60px 0;">장바구니가 비어 있습니다.</div>
					<div style="text-align: center;">
						<a href="${pageContext.request.contextPath}/book/list" class="btn btn-buy">도서 담으러 가기</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>