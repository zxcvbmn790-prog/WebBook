<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=DM+Serif+Display&display=swap"
	rel="stylesheet">

<style>
.login-container {
	width: 100%; /* 🌟 이 줄을 추가해 주세요! (가로 전체 영역 차지) */
	min-height: calc(100vh - 60px);
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 40px 20px;
	background-color: var(- -bg);
}

.login-card {
	width: 100%;
	max-width: 420px;
	background: var(- -surface);
	border: 1px solid var(- -border);
	border-radius: 4px;
	/* 책 카드에 쓰인 그림자 효과와 통일 */
	box-shadow: 0 12px 32px rgba(44, 37, 32, 0.08);
	padding: 48px 40px;
}

.login-title {
	font-family: 'DM Serif Display', serif;
	font-size: 32px;
	color: var(- -text);
	text-align: center;
	margin-bottom: 36px;
}

.custom-label {
	font-size: 11px;
	font-weight: 500;
	letter-spacing: 1.5px;
	text-transform: uppercase;
	color: var(- -text-sub);
	margin-bottom: 8px;
	display: block;
}

.custom-input {
	width: 100%;
	background: var(- -bg);
	border: 1px solid var(- -border);
	color: var(- -text);
	font-family: 'DM Sans', sans-serif;
	font-size: 14px;
	padding: 13px 16px;
	border-radius: 4px;
	margin-bottom: 24px;
	transition: border-color 0.2s, background-color 0.2s;
}

.custom-input:focus {
	outline: none;
	border-color: var(- -accent);
	background: var(- -surface);
}

.custom-input::placeholder {
	color: var(- -text-muted);
}

/* style.css의 .btn-buy 스타일을 로그인 버튼에 적용 */
.btn-login {
	width: 100%;
	background: #3e3028;
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
	margin-bottom: 24px;
}

.btn-login:hover {
	background: #3e3028;
}

.login-links {
	text-align: center;
}

.login-links a {
	font-size: 13px;
	color: var(- -text-sub);
	text-decoration: none;
	transition: color 0.2s;
}

.login-links a:hover {
	color: var(- -text);
	text-decoration: underline;
}
</style>

<div class="login-container">
	<div class="login-card">
		<h3 class="login-title">Login</h3>

		<form action="${pageContext.request.contextPath}/member/login_proc"
			method="post">

			<div>
				<label for="username" class="custom-label">ID</label> <input
					type="text" class="custom-input" id="username" name="username"
					placeholder="아이디를 입력하세요" required>
			</div>

			<div>
				<label for="password" class="custom-label">Password</label> <input
					type="password" class="custom-input" id="password" name="password"
					placeholder="비밀번호를 입력하세요" required>
			</div>

			<button type="submit" class="btn-login">로그인</button>

			<div class="login-links">
				<a href="${pageContext.request.contextPath}/member/register">회원가입</a>
			</div>
		</form>

	</div>
</div>