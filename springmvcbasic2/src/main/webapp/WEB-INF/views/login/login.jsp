<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="login">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow login-card">
        <div class="card-body p-4">
            
            <h3 class="text-center mb-4">로그인</h3>
            
            <form action="/login" method="post">
                
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
                
                <!-- Login Button -->
                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-primary">
                        로그인
                    </button>
                </div>
                
                <!-- Links -->
                <div class="text-center">
                    <a href="/member" class="text-decoration-none me-2">
                        회원가입
                    </a>
                    |
                    <a href="/find-account" class="text-decoration-none ms-2">
                        아이디/패스워드 찾기
                    </a>
                </div>
                
            </form>
            
        </div>
    </div>
</div>
</div>
