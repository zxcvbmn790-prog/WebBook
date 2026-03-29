<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<style>
				.checkout-container {
					max-width: 900px;
					margin: 50px auto;
					padding: 0 20px;
				}

				.checkout-card {
					background: #fff;
					padding: 40px;
					border-radius: 12px;
					box-shadow: 0 5px 20px rgba(0, 0, 0, 0.05);
				}

				.checkout-title {
					font-size: 24px;
					font-weight: bold;
					margin-bottom: 30px;
					border-bottom: 2px solid #333;
					padding-bottom: 10px;
				}

				.order-table {
					width: 100%;
					border-collapse: collapse;
					margin-bottom: 40px;
				}

				.order-table th {
					padding: 15px;
					border-bottom: 1px solid #ddd;
					background: #f9f9f9;
					text-align: center;
				}

				.order-table td {
					padding: 15px;
					border-bottom: 1px solid #eee;
					text-align: center;
				}

				.form-section {
					margin-top: 40px;
				}

				.form-group {
					margin-bottom: 20px;
				}

				.form-group label {
					display: block;
					margin-bottom: 8px;
					font-weight: bold;
				}

				.form-control {
					width: 100%;
					padding: 12px;
					border: 1px solid #ddd;
					border-radius: 4px;
					box-sizing: border-box;
				}

				.pay-btn {
					width: 100%;
					height: 55px;
					background: #333;
					color: #fff;
					font-size: 18px;
					font-weight: bold;
					border: none;
					border-radius: 4px;
					cursor: pointer;
					margin-top: 30px;
				}

				.pay-btn:hover {
					background: #000;
				}
			</style>

			<div class="checkout-container">
				<div class="checkout-card">
					<h2 class="checkout-title">주문서 작성</h2>

					<table class="order-table">
						<thead>
							<tr>
								<th>이미지</th>
								<th>도서명</th>
								<th>가격</th>
								<th>수량</th>
								<th>합계</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${cartList}">
								<tr>
									<td><img src="${item.image}" width="60"></td>
									<td style="text-align: left;">${item.bookname}</td>
									<td>
										<fmt:formatNumber value="${item.price}" type="number" />원
									</td>
									<td>${item.amount}개</td>
									<td><strong>
											<fmt:formatNumber value="${item.totalPrice}" type="number" />원
										</strong></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="form-section">
						<h3 style="margin-bottom:20px;">배송 정보</h3>
						<form action="${pageContext.request.contextPath}/order/pay" method="post">

							<%-- ★ 바로구매 정보 전송 파라미터 (중요) --%>
								<c:if test="${isBuyNow}">
									<input type="hidden" name="isBuyNow" value="true">
									<input type="hidden" name="isbn" value="${cartList[0].isbn}">
									<input type="hidden" name="amount" value="${cartList[0].amount}">
								</c:if>

								<div class="form-group">
									<label>수령인</label>
									<input type="text" name="receiver" class="form-control" placeholder="성함을 입력하세요"
										required>
								</div>
								<div class="form-group">
									<label>연락처</label>
									<input type="text" name="phone" class="form-control" placeholder="010-0000-0000"
										required>
								</div>
								<div class="form-group">
									<label>배송지 주소</label>
									<input type="text" name="address" class="form-control" placeholder="전체 주소를 입력하세요"
										required>
								</div>

								<div style="background: #f9f9f9; padding: 20px; border-radius: 8px; margin-top: 30px;">
									<div style="display: flex; justify-content: space-between; font-size: 20px;">
										<span>최종 결제 금액</span>
										<span style="color: #d9534f; font-weight: bold;">
											<fmt:formatNumber value="${sumMoney}" type="number" />원
										</span>
									</div>
								</div>

								<button type="submit" class="pay-btn">결제하기</button>
						</form>
					</div>
				</div>
			</div>