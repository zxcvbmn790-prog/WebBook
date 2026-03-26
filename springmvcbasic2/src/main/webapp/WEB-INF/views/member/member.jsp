<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="member">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow signup-card">
        <div class="card-body p-4">

            <h3 class="text-center mb-4">회원가입</h3>

            <form action="/member" method="post">

                <!-- Username -->
                <div class="mb-3">
                    <label for="username" class="form-label">아이디</label>
                    <input type="text"
                           class="form-control"
                           id="username"
                           name="username"
                           placeholder="아이디를 입력하세요"
                           required>
                </div>

                <!-- Password -->
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password"
                           class="form-control"
                           id="password"
                           name="password"
                           placeholder="비밀번호를 입력하세요"
                           required>
                </div>

                <!-- Email -->
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="email"
                           class="form-control"
                           id="email"
                           name="email"
                           placeholder="이메일을 입력하세요"
                           required>
                </div>

                <!-- Phone -->
                <div class="mb-3">
                    <label for="hp" class="form-label">휴대폰 번호</label>
                    <input type="text"
                           class="form-control"
                           id="hp"
                           name="hp"
                           placeholder="010-0000-0000">
                </div>

                <!-- Nickname -->
                <div class="mb-3">
                    <label for="nickname" class="form-label">닉네임</label>
                    <input type="text"
                           class="form-control"
                           id="nickname"
                           name="nickname"
                           placeholder="닉네임을 입력하세요">
                </div>

                <!-- Submit Button -->
                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-primary">
                        회원가입
                    </button>
                </div>

                <!-- Login Link -->
                <div class="text-center">
                    이미 계정이 있으신가요?
                    <a href="/login" class="text-decoration-none">
                        로그인
                    </a>
                </div>

            </form>

        </div>
    </div>
</div>
</div>
