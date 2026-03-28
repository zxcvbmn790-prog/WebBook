<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="ko">

	<head>
		<meta charset="UTF-8">
		<title>회원가입 - BookList</title>

		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link
			href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=DM+Serif+Display&display=swap"
			rel="stylesheet">

		<style>
			/* 로그인 페이지와 통일된 인증(Auth) 컨테이너 디자인 */
			/* 부모 레이아웃의 간섭을 완전히 무시하는 무적의 중앙 정렬 코드 */
			.auth-container {
				position: absolute;
				top: 60px;
				/* 헤더 높이(60px)만큼 위에서 띄움 */
				left: 0;
				width: 100vw;
				/* 화면 전체 가로 길이 강제 할당! */
				min-height: calc(100vh - 60px);
				display: flex;
				justify-content: center;
				/* 완벽한 가로 중앙 정렬 */
				align-items: center;
				/* 완벽한 세로 중앙 정렬 */
				background-color: var(- -bg, #f5f0e8);
				z-index: 10;
				/* 다른 요소들에 밀리지 않게 위로 띄움 */
				padding: 40px 20px;
			}

			.auth-card {
				width: 100%;
				max-width: 460px;
				/* 로그인보다 입력란이 많으므로 살짝 넓게 설정 */
				background: var(- -surface, #faf7f2);
				border: 1px solid var(- -border, #e2d9cc);
				border-radius: 4px;
				box-shadow: 0 12px 32px rgba(44, 37, 32, 0.08);
				padding: 48px 40px;
			}

			.auth-title {
				font-family: 'DM Serif Display', serif;
				font-size: 32px;
				color: var(- -text, #2c2520);
				text-align: center;
				margin-bottom: 36px;
			}

			.custom-label {
				font-size: 11px;
				font-weight: 500;
				letter-spacing: 1.5px;
				text-transform: uppercase;
				color: var(- -text-sub, #8a7e74);
				margin-bottom: 8px;
				display: block;
			}

			.custom-input {
				width: 100%;
				background: var(- -bg, #f5f0e8);
				border: 1px solid var(- -border, #e2d9cc);
				color: var(- -text, #2c2520);
				font-family: 'DM Sans', sans-serif;
				font-size: 14px;
				padding: 13px 16px;
				border-radius: 4px;
				margin-bottom: 20px;
				/* 로그인(24px)보다 살짝 좁혀서 스크롤 최소화 */
				transition: border-color 0.2s, background-color 0.2s;
			}

			.custom-input:focus {
				outline: none;
				border-color: var(- -accent, #5c4a3a);
				background: var(- -surface, #faf7f2);
			}

			.custom-input::placeholder {
				color: var(- -text-muted, #b5aba0);
			}

			.btn-auth {
				width: 100%;
				background: var(--accent);
				color: #faf7f2;
				border: none;
				padding: 14px 22px;
				font-family: 'DM Sans', sans-serif;
				font-size: 14px;
				font-weight: 500;
				border-radius: 4px;
				cursor: pointer;
				transition: background 0.2s;
				letter-spacing: 0.5px;
				margin-top: 12px;
				margin-bottom: 24px;
			}

			.btn-auth:hover {
				background: #3e3028;
				/* hover 시 기존 버튼들처럼 어두운 색상 */
			}

			.auth-links {
				text-align: center;
			}

			.auth-links a {
				font-size: 13px;
				color: var(- -text-sub, #8a7e74);
				text-decoration: none;
				transition: color 0.2s;
			}

			.auth-links a:hover {
				color: var(- -text, #2c2520);
				text-decoration: underline;
			}
		</style>
	</head>

	<body>

		<div class="auth-container">
			<div class="auth-card">
				<h3 class="auth-title">Update Profile</h3>

				<form action="${pageContext.request.contextPath}/member/update" method="post">

					<div>
						<label for="username" class="custom-label">별명</label>
						<input type="text" class="custom-input" id="nickname" name="nickname" 
						placeholder="별명을 입력하세요" value="${sessionScope.loginNickname}"
							required>
					</div>
					
					<div>
						<label for="username" class="custom-label">ID</label>
						<input type="text" class="custom-input" id="username" name="username" 
						placeholder="아이디를 입력하세요" value="${sessionScope.loginUser}"
							required>
					</div>

					<div>
						<label for="password" class="custom-label">Password</label>
						<input type="password" class="custom-input" id="password" name="password"
							placeholder="비밀번호를 입력하세요" value="${sessionScope.loginPW}" required>
					</div>

					<div>
						<label for="username" class="custom-label">Phone</label>
						<input type="text" class="custom-input" id="phone" name="phone" 
						value="${sessionScope.loginPhone}" placeholder="010-0000-0000">
					</div>

					<div>
						<label for="username" class="custom-label">Email</label>
						<input type="email" class="custom-input" id="email" name="email" 
						value="${sessionScope.loginEmail}" placeholder="example@mail.com">
					</div>

					<button type="submit" class="btn-auth">수정하기</button>

					<div class="auth-links">
						<a href="${pageContext.request.contextPath}/book/list">취소하고 돌아가기</a>
					</div> 
				</form>
			</div>
		</div>

	</body>

	</html>