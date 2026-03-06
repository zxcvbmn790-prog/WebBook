<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>도서 목록</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg:           #f5f0e8;
            --surface:      #faf7f2;
            --border:       #e2d9cc;
            --text:         #2c2520;
            --text-sub:     #8a7e74;
            --text-muted:   #b5aba0;
            --accent:       #5c4a3a;
            --accent-light: #e8ddd3;
            --red:          #9e3d2b;
        }

        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

        body {
            font-family: 'DM Sans', sans-serif;
            background: var(--bg);
            color: var(--text);
            min-height: 100vh;
        }

        /* ── HEADER ── */
        header {
            position: fixed;
            top: 0; left: 0; right: 0;
            z-index: 100;
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 52px;
            background: var(--bg);
            border-bottom: 1px solid var(--border);
        }

        .logo {
            font-family: 'DM Serif Display', serif;
            font-size: 18px;
            color: var(--accent);
            letter-spacing: 0.5px;
        }

        nav { display: flex; gap: 28px; }

        nav a {
            font-size: 13px;
            font-weight: 400;
            color: var(--text-sub);
            text-decoration: none;
            transition: color 0.2s;
        }

        nav a:hover { color: var(--text); }

        /* ── PAGE TITLE ── */
        .page-header {
            padding-top: 60px;
            text-align: center;
            padding-bottom: 0;
        }

        .page-header-inner {
            padding: 52px 0 40px;
            border-bottom: 1px solid var(--border);
        }

        .page-tag {
            display: inline-block;
            font-size: 11px;
            font-weight: 500;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: var(--text-sub);
            background: var(--accent-light);
            padding: 4px 10px;
            border-radius: 2px;
            margin-bottom: 16px;
        }

        .page-title {
            font-family: 'DM Serif Display', serif;
            font-size: 36px;
            color: var(--text);
            font-weight: 400;
        }

        /* ── GRID ── */
        .book-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 28px;
            max-width: 1100px;
            margin: 0 auto;
            padding: 48px 52px 72px;
        }

        /* ── CARD ── */
        .book-card {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: 4px;
            overflow: hidden;
            text-decoration: none;
            color: inherit;
            display: block;
            transition: transform 0.22s ease, box-shadow 0.22s ease;
        }

        .book-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 12px 32px rgba(44,37,32,0.10);
        }

        .img-wrap {
            width: 100%;
            height: 230px;
            background: var(--accent-light);
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .img-wrap img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.4s ease;
        }

        .book-card:hover .img-wrap img {
            transform: scale(1.04);
        }

        .no-img {
            font-size: 11px;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: var(--text-muted);
        }

        .card-info {
            padding: 16px 18px 18px;
            border-top: 1px solid var(--border);
        }

        .card-title {
            font-family: 'DM Serif Display', serif;
            font-size: 15px;
            font-weight: 400;
            line-height: 1.4;
            color: var(--text);
            margin-bottom: 6px;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }

        .card-author {
            font-size: 12px;
            font-weight: 300;
            color: var(--text-muted);
            margin-bottom: 10px;
        }

        .card-price {
            font-size: 14px;
            font-weight: 500;
            color: var(--red);
        }

        /* ── EMPTY ── */
        .empty {
            grid-column: 1 / -1;
            text-align: center;
            padding: 80px 0;
            font-family: 'DM Serif Display', serif;
            font-size: 22px;
            color: var(--text-muted);
        }
    </style>
</head>
<body>

<header>
    <div class="logo">Booklist</div>
    <nav>
        <a href="${pageContext.request.contextPath}/user/views">전체 목록</a>
        
        <c:choose>
            <%-- 세션에 nickname이 있는지 확인 --%>
            <c:when test="${not empty sessionScope.nickname}">
                <span style="font-size: 13px; color: var(--text); margin-right: 10px;">
                    <strong>${sessionScope.nickname}</strong>님 환영합니다!
                </span>
                <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
            </c:when>
            <c:otherwise>
                <%-- 로그인하지 않은 경우 --%>
                <a href="${pageContext.request.contextPath}/member/login">로그인</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>

<div class="page-header">
    <div class="page-header-inner">
        <div class="page-tag">All Books</div>
        <div class="page-title">전체 도서 목록</div>
    </div>
</div>

<div class="book-grid">
    <c:choose>
        <c:when test="${not empty list}">
            <c:forEach var="book" items="${list}">
                <a class="book-card" href="${pageContext.request.contextPath}/user/view?isbn=${book.isbn}">
                    <div class="img-wrap">
                        <c:choose>
                            <c:when test="${not empty book.image}">
                                <img src="${pageContext.request.contextPath}${book.image}" alt="${book.bookname}">
                            </c:when>
                            <c:otherwise>
                                <span class="no-img">No Image</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="card-info">
                        <div class="card-title">${book.bookname}</div>
                        <div class="card-author">${book.author} · ${book.publisher}</div>
                        <div class="card-price">
                            <c:choose>
                                <c:when test="${not empty book.price}">${book.price}원</c:when>
                                <c:otherwise>가격 미정</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="empty">등록된 도서가 없습니다.</div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
