<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<style>
			/* 전체 배경과 정렬 */
			.admin-form-container {
				display: flex;
				justify-content: center;
				align-items: center;
				padding: 60px 20px;
				min-height: 80vh;
			}

			/* 카드 스타일 컨테이너 */
			.form-card {
				width: 100%;
				max-width: 580px;
				background: #ffffff;
				padding: 50px;
				border-radius: 16px;
				box-shadow: 0 20px 40px rgba(0, 0, 0, 0.04);
				border: 1px solid #f0ece6;
			}

			/* 상단 배지 및 제목 */
			.form-header {
				text-align: center;
				margin-bottom: 45px;
			}

			.form-badge {
				display: inline-block;
				font-size: 11px;
				font-weight: 700;
				letter-spacing: 2px;
				text-transform: uppercase;
				color: #8a7e74;
				/* [cite: 85] */
				background: #f5f0e8;
				/* [cite: 85] */
				padding: 7px 16px;
				border-radius: 50px;
				margin-bottom: 15px;
			}

			.form-title {
				font-family: 'DM Serif Display', serif;
				/* [cite: 86] */
				font-size: 32px;
				color: #2c2520;
				/* [cite: 86] */
				margin: 0;
			}

			/* 입력 필드 레이아웃 */
			.field-wrap {
				margin-bottom: 25px;
			}

			.field-label {
				display: block;
				font-size: 12px;
				font-weight: 600;
				color: #b5aba0;
				/* [cite: 87] */
				text-transform: uppercase;
				letter-spacing: 1px;
				margin-bottom: 10px;
				padding-left: 4px;
			}

			.field-input {
				width: 100%;
				padding: 15px 18px;
				font-family: 'DM Sans', sans-serif;
				/* [cite: 88] */
				font-size: 15px;
				color: #2c2520;
				/* [cite: 88] */
				background: #fafafa;
				border: 1.5px solid #e2d9cc;
				/* [cite: 88] */
				border-radius: 10px;
				outline: none;
				transition: all 0.25s ease;
			}

			/* 포커스 효과 */
			.field-input:focus {
				border-color: #5c4a3a;
				/*  */
				background: #fff;
				box-shadow: 0 0 0 4px rgba(92, 74, 58, 0.08);
			}

			/* 읽기 전용 필드 (ISBN) */
			.field-input[readonly] {
				background: #f1ede7;
				/*  */
				color: #8a7e74;
				cursor: not-allowed;
				border-style: dashed;
			}

			/* 하단 버튼 그룹 */
			.form-footer {
				display: flex;
				gap: 14px;
				margin-top: 45px;
				/*  */
			}

			.btn {
				flex: 1;
				height: 56px;
				display: flex;
				align-items: center;
				justify-content: center;
				font-size: 15px;
				font-weight: 600;
				border-radius: 10px;
				cursor: pointer;
				transition: all 0.2s;
				text-decoration: none;
			}

			/* 취소 버튼 */
			.btn-outline {
				background: #fff;
				color: #8a7e74;
				border: 1.5px solid #e2d9cc;
			}

			.btn-outline:hover {
				background: #fcfaf8;
				border-color: #b5aba0;
				color: #2c2520;
			}

			/* 확인/저장 버튼 */
			.btn-primary {
				background: #2c2520;
				color: #faf7f2;
				border: none;
				box-shadow: 0 4px 15px rgba(44, 37, 32, 0.2);
			}

			.btn-primary:hover {
				background: #4a3f36;
				transform: translateY(-2px);
				box-shadow: 0 8px 20px rgba(44, 37, 32, 0.25);
			}

			.btn-primary:active {
				transform: translateY(0);
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