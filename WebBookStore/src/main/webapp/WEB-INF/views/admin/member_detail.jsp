<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<style>
			.detail-wrap {
				max-width: 1100px;
				/* 전체 너비를 조금 더 여유있게 조정 */
				margin: 40px auto;
				display: grid;
				gap: 30px;
				grid-template-columns: 320px 1fr;
				align-items: start;
			}

			/* 왼쪽 프로필 카드 유지 */
			.profile-card {
				background: #fff;
				padding: 40px 25px;
				border-radius: 8px;
				box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
				text-align: center;
				width: 100%;
				box-sizing: border-box;
			}

			.info-list-wrap {
				width: 100%;
				text-align: left;
				margin-top: 30px;
				border-top: 1px solid #f0f0f0;
			}

			.info-row {
				display: flex;
				justify-content: space-between;
				padding: 15px 0;
				border-bottom: 1px solid #f0f0f0;
				font-size: 14px;
			}

			.info-label {
				color: #888;
				flex-shrink: 0;
				width: 80px;
			}

			.info-value {
				color: #333;
				font-weight: bold;
				text-align: right;
				word-break: break-all;
			}

			/* 오른쪽 카드 공통 */
			.history-card {
				background: #fff;
				padding: 30px;
				border-radius: 8px;
				box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
				margin-bottom: 24px;
			}

			.history-header {
				font-size: 18px;
				font-weight: bold;
				color: #2c2520;
				border-bottom: 2px solid #2c2520;
				padding-bottom: 15px;
				margin-bottom: 20px;
			}

			/* ★ 표 찌그러짐 방지 핵심 CSS ★ */
			.history-table {
				width: 100%;
				border-collapse: collapse;
				font-size: 14px;
				table-layout: fixed;
				/* 테이블 너비 고정 */
			}

			.history-table th,
			.history-table td {
				padding: 15px 10px;
				border-bottom: 1px solid #eee;
				vertical-align: middle;
				line-height: 1.5;
			}

			.history-table th {
				background: #fcfcfc;
				color: #555;
				font-weight: 600;
				border-bottom: 2px solid #ddd;
			}

			/* 열 너비 비율 고정 (총 100%) */
			/* 장바구니 테이블 전용 */
			.cart-table .col-info {
				width: 75%;
			}

			.cart-table .col-amount {
				width: 25%;
			}

			/* 주문 내역 테이블 전용 */
			.order-table .col-no {
				width: 10%;
			}

			/* 주문번호 */
			.order-table .col-info {
				width: 45%;
			}

			/* 도서정보 */
			.order-table .col-price {
				width: 15%;
			}

			/* 금액 */
			.order-table .col-status {
				width: 12%;
			}

			/* 상태 */
			.order-table .col-date {
				width: 18%;
			}

			/* 날짜 */

			/* 긴 제목 처리 */
			.text-truncate {
				display: block;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: normal;
				word-break: keep-all;
				/* 단어 단위로 줄바꿈하여 찌그러짐 방지 */
			}

			/* 배송상태 배지 찌그러짐 방지 */
			.status-badge {
				display: inline-block;
				white-space: nowrap;
				/* 글자 쪼개짐 방지 */
				padding: 4px 10px;
				border-radius: 20px;
				font-size: 12px;
				background: #e8f5e9;
				color: #2e7d32;
				font-weight: 600;
			}
		</style>

		<div class="detail-wrap">
			<div class="profile-card">
				<i class="fa-solid fa-circle-user" style="font-size:60px; color:#5c4a3a;"></i>
				<div style="font-size:24px; font-weight:bold; margin-top:15px;">${member.nickname != null ?
					member.nickname : '닉네임 미설정'}</div>
				<div style="color:#8a7e74;">@${member.username}</div>
				<div class="info-list-wrap">
					<div class="info-row"><span class="info-label">이메일</span><span
							class="info-value">${member.email}</span></div>
					<div class="info-row"><span class="info-label">연락처</span><span
							class="info-value">${member.phone}</span></div>
				</div>
				<a href="${pageContext.request.contextPath}/admin/member/list" class="action-btn"
					style="display:block; margin-top:20px; padding:12px; border:1px solid #ddd; text-decoration:none; color:#333; border-radius:4px;">목록으로</a>
			</div>

			<div>
				<div class="history-card">
					<div class="history-header"><span>🛒 장바구니 내역</span></div>
					<c:choose>
						<c:when test="${not empty cartList}">
							<table class="history-table cart-table">
								<thead>
									<tr>
										<th class="col-info" style="text-align: left;">도서 정보 (ISBN)</th>
										<th class="col-amount">수량</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cart" items="${cartList}">
										<tr>
											<td style="text-align: left;">
												<strong class="text-truncate">${cart.bookname}</strong>
												<span style="color:#888; font-size:12px;">ISBN: ${cart.isbn}</span>
											</td>
											<td>${cart.amount}개</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<div class="history-placeholder">장바구니가 비어있습니다.</div>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="history-card">
					<div class="history-header"><span>📦 주문 및 결제 내역</span></div>
					<c:choose>
						<c:when test="${not empty orderList}">
							<table class="history-table order-table">
								<thead>
									<tr>
										<th class="col-no">번호</th>
										<th class="col-info" style="text-align: left;">주문 도서</th>
										<th class="col-price">결제금액</th>
										<th class="col-status">상태</th>
										<th class="col-date">주문일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="order" items="${orderList}">
										<tr>
											<td style="font-weight:bold; color:#4f46e5;">${order.orderId}</td>
											<td style="text-align: left;">
												<strong class="text-truncate">${order.bookname}</strong>
												<div style="font-size:12px; color:#999;">수량: ${order.amount}개</div>
											</td>
											<td style="font-weight:bold; white-space:nowrap;">${order.totalPrice}원</td>
											<td><span class="status-badge">${order.status}</span></td>
											<td style="color:#888; font-size:12px;">${order.orderDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<div class="history-placeholder">주문 내역이 없습니다.</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>