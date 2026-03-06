<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>

<!-- Bootstrap 5 CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background-color: #f8f9fa;
}

.login-card {
	width: 100%;
	max-width: 400px;
}
</style>
</head>
<body>
	<div
		class="container d-flex justify-content-center align-items-center vh-100">
		<div class="card shadow login-card">
			<div class="card-body p-4">

				<h3 class="text-center mb-4">로그인</h3>

				<form action="${pageContext.request.contextPath}/member/login_proc"
					method="post">

					<!-- Username -->
					<div class="mb-3">
						<label for="username" class="form-label">아이디</label> <input
							type="text" class="form-control" id="username" name="username"
							placeholder="아이디를 입력하세요" required>
					</div>

					<!-- Password -->
					<div class="mb-3">
						<label for="password" class="form-label">비밀번호</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="비밀번호를 입력하세요" required>
					</div>

					<!-- Login Button -->
					<div class="d-grid mb-3">
						<button type="submit" class="btn btn-primary">로그인</button>
					</div>

					<!-- Links -->
					<div class="text-center">
						<a href="${pageContext.request.contextPath}/member/register"
							class="text-decoration-none me-2"> 회원가입 </a>
					</div>
				</form>

			</div>
		</div>
	</div>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>