<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<style>
			/* [1] 전체 배경 및 레이아웃 (상세페이지와 통일) */
			.admin-form-container {
				padding: 60px 20px;
				background-color: #f8f9fa;
				/* 상세페이지의 연한 회색 배경 */
				min-height: 80vh;
				display: flex;
				justify-content: center;
				align-items: flex-start;
			}

			/* [2] 둥근 모서리 카드 디자인 (상세페이지 카드 스타일 계승) */
			.form-card {
				width: 100%;
				max-width: 750px;
				background: #ffffff;
				padding: 50px;
				border-radius: 20px;
				/* 상세페이지의 부드러운 둥근 모서리 */
				box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
				border: none;
			}

			/* [3] 헤더 및 배지 스타일 */
			.form-header {
				margin-bottom: 40px;
				text-align: left;
			}

			.form-badge {
				color: #6366f1;
				/* 상세페이지 메인 포인트 블루 컬러 */
				font-size: 13px;
				font-weight: bold;
				letter-spacing: 1px;
				text-transform: uppercase;
				display: block;
				margin-bottom: 12px;
			}

			.form-title {
				font-size: 30px;
				font-weight: 800;
				color: #2d3436;
				margin: 0;
				letter-spacing: -0.5px;
			}

			/* [4] 입력 필드 및 라벨 정돈 */
			.field-wrap {
				margin-bottom: 22px;
				text-align: left;
			}

			.field-label {
				display: block;
				font-size: 14px;
				font-weight: 700;
				color: #2d3436;
				margin-bottom: 10px;
				padding-left: 2px;
			}

			.field-input {
				width: 100%;
				padding: 14px 18px;
				border: 1px solid #dee2e6;
				border-radius: 10px;
				font-size: 15px;
				background-color: #fdfcfb;
				outline: none;
				transition: all 0.2s ease;
				box-sizing: border-box;
			}

			.field-input:focus {
				border-color: #6366f1;
				background-color: #fff;
				box-shadow: 0 0 0 4px rgba(99, 102, 241, 0.08);
			}

			/* 읽기 전용 필드 (상세페이지 메타 정보 느낌) */
			.field-input[readonly] {
				background-color: #f1f3f5;
				color: #636e72;
				cursor: not-allowed;
				border-style: dashed;
			}

			/* [5] 하단 버튼 그룹 (상세페이지 액션 버튼과 일치) */
			.form-footer {
				display: flex;
				gap: 12px;
				margin-top: 40px;
				padding-top: 30px;
				border-top: 1px solid #eee;
			}

			.btn {
				flex: 1;
				height: 50px;
				display: flex;
				align-items: center;
				justify-content: center;
				border-radius: 10px;
				font-size: 15px;
				font-weight: bold;
				text-decoration: none;
				cursor: pointer;
				border: none;
				transition: 0.2s;
			}

			.btn-outline {
				background: #fff;
				color: #2d3436;
				border: 1px solid #dee2e6;
			}

			.btn-outline:hover {
				background: #f8f9fa;
				border-color: #ced4da;
			}

			.btn-primary {
				background: #333;
				color: #fff;
			}

			.btn-primary:hover {
				background: #000;
				transform: translateY(-1px);
			}
		</style>

		<div class="admin-form-container">
			<div class="form-card">
				<div class="form-header">
					<span class="form-badge">Inventory Control</span>
					<h1 class="form-title">
						<c:choose>
							<c:when test="${not empty admin}">도서 정보 수정</c:when>
							<c:otherwise>새로운 도서 등록</c:otherwise>
						</c:choose>
					</h1>
				</div>

				<form action="${pageContext.request.contextPath}/admin/${not empty admin ? 'update' : 'insert'}"
					method="post">

					<div class="field-wrap">
						<label class="field-label">ISBN Number</label>
						<c:choose>
							<c:when test="${not empty admin}">
								<input type="text" class="field-input" value="${admin.isbn}" readonly>
								<input type="hidden" name="isbn" value="${admin.isbn}">
							</c:when>
							<c:otherwise>
								<input type="number" name="isbn" class="field-input" placeholder="ISBN 번호를 입력하세요"
									required>
							</c:otherwise>
						</c:choose>
					</div>

					<div class="field-wrap">
						<label class="field-label">Book Title</label>
						<input type="text" name="bookname" class="field-input" value="${admin.bookname}" required>
					</div>

					<div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
						<div class="field-wrap">
							<label class="field-label">Author</label>
							<input type="text" name="author" class="field-input" value="${admin.author}" required>
						</div>
						<div class="field-wrap">
							<label class="field-label">Publisher</label>
							<input type="text" name="publisher" class="field-input" value="${admin.publisher}" required>
						</div>
					</div>

					<div class="field-wrap">
						<label class="field-label">Cover Image URL</label>
						<input type="text" name="image" class="field-input" value="${admin.image}"
							placeholder="/images/example.jpg">
					</div>

					<div class="field-wrap">
						<label class="field-label">Price (KRW)</label>
						<input type="text" name="price" class="field-input" value="${admin.price}" placeholder="숫자만 입력">
					</div>

					<div class="form-footer">
						<a href="${pageContext.request.contextPath}/book/list" class="btn btn-outline">취소</a>
						<button type="submit" class="btn btn-primary">
							<c:choose>
								<c:when test="${not empty admin}">수정 내용 저장</c:when>
								<c:otherwise>도서 등록 완료</c:otherwise>
							</c:choose>
						</button>
					</div>
				</form>
			</div>
		</div>