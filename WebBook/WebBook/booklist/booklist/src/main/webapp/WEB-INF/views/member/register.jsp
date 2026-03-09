<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .register-card { width: 100%; max-width: 500px; }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow register-card">
        <div class="card-body p-4">
            <h3 class="text-center mb-4">회원가입</h3>
            
            <form action="${pageContext.request.contextPath}/member/register_proc" method="post">
                
                <div class="mb-3">
                    <label for="id" class="form-label">아이디</label>
                    <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요" required>
                </div>
                
                <div class="mb-3">
                    <label for="pw" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요" required>
                </div>

                <div class="mb-3">
                    <label for="hp" class="form-label">전화번호</label>
                    <input type="text" class="form-control" id="hp" name="hp" placeholder="010-0000-0000">
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="example@mail.com">
                </div>

                <div class="mb-3">
                    <label for="nickname" class="form-label">닉네임</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" placeholder="사용할 닉네임을 입력하세요">
                </div>
                
                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-success">가입하기</button>
                </div>
                
                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">이미 계정이 있으신가요? 로그인</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>