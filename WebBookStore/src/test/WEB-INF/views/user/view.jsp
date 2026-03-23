
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${book.bookname} - 상세보기</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg:        #f5f0e8;
            --surface:   #faf7f2;
            --border:    #e2d9cc;
            --text:      #2c2520;
            --text-sub:  #8a7e74;
            --text-muted:#b5aba0;
            --accent:    #5c4a3a;
            --accent-light: #e8ddd3;
            --red:       #9e3d2b;
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

        /* ── LAYOUT ── */
        main {
            padding-top: 60px;
            min-height: 100vh;
            display: flex;
            align-items: center;
        }

        .wrapper {
            width: 100%;
            max-width: 1000px;
            margin: 0 auto;
            padding: 64px 52px;
            display: grid;
            grid-template-columns: 280px 1fr;
            gap: 64px;
            align-items: start;
        }

        /* ── COVER ── */
        .cover-frame {
            width: 100%;
            aspect-ratio: 2/3;
            background: var(--accent-light);
            border-radius: 4px;
            overflow: hidden;
            box-shadow: 0 8px 32px rgba(44,37,32,0.12);
        }

        .cover-frame img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            display: block;
            transition: transform 0.5s ease;
        }

        .cover-frame:hover img { transform: scale(1.04); }

        .no-cover {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 12px;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: var(--text-muted);
        }

        /* ── INFO ── */
        .info-col {
            padding-top: 4px;
            animation: fadeUp 0.5s ease both;
        }

        @keyframes fadeUp {
            from { opacity: 0; transform: translateY(16px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        .tag {
            display: inline-block;
            font-size: 11px;
            font-weight: 500;
            letter-spacing: 1.5px;
            text-transform: uppercase;
            color: var(--text-sub);
            background: var(--accent-light);
            padding: 4px 10px;
            border-radius: 2px;
            margin-bottom: 20px;
        }

        .book-title {
            font-family: 'DM Serif Display', serif;
            font-size: 38px;
            line-height: 1.25;
            color: var(--text);
            margin-bottom: 10px;
        }

        .book-author {
            font-size: 14px;
            font-weight: 300;
            color: var(--text-sub);
            margin-bottom: 36px;
        }

        .divider {
            border: none;
            border-top: 1px solid var(--border);
            margin-bottom: 32px;
        }

        .meta-list {
            display: flex;
            flex-direction: column;
            gap: 16px;
            margin-bottom: 40px;
        }

        .meta-row {
            display: flex;
            align-items: baseline;
            gap: 12px;
        }

        .meta-label {
            font-size: 11px;
            font-weight: 500;
            letter-spacing: 1px;
            text-transform: uppercase;
            color: var(--text-muted);
            width: 64px;
            flex-shrink: 0;
        }

        .meta-value {
            font-size: 14px;
            color: var(--text);
        }

        /* 가격 */
        .price-block {
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: 6px;
            padding: 20px 24px;
            margin-bottom: 32px;
            display: flex;
            align-items: baseline;
            justify-content: space-between;
        }

        .price-label {
            font-size: 12px;
            font-weight: 500;
            letter-spacing: 1px;
            text-transform: uppercase;
            color: var(--text-muted);
        }

        .price-value {
            font-family: 'DM Serif Display', serif;
            font-size: 36px;
            color: var(--red);
            letter-spacing: -0.5px;
        }

        .price-unit {
            font-family: 'DM Sans', sans-serif;
            font-size: 16px;
            font-weight: 300;
            color: var(--text-sub);
            margin-left: 2px;
        }

        /* 버튼 */
        .btn-group {
            display: flex;
            gap: 10px;
        }

        .btn {
            padding: 13px 22px;
            font-family: 'DM Sans', sans-serif;
            font-size: 13px;
            font-weight: 500;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: all 0.2s;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            letter-spacing: 0.3px;
        }

        .btn-back {
            background: var(--surface);
            border: 1px solid var(--border);
            color: var(--text-sub);
        }

        .btn-back:hover {
            background: var(--accent-light);
            color: var(--text);
        }

        .btn-cart {
            flex: 1;
            background: var(--accent-light);
            color: var(--accent);
            border: 1px solid var(--border);
        }

        .btn-cart:hover {
            background: var(--border);
            color: var(--text);
        }

        .btn-buy {
            flex: 1;
            background: var(--accent);
            color: #faf7f2;
        }

        .btn-buy:hover {
            background: #3e3028;
        }

        /* not found */
        .not-found {
            grid-column: 1 / -1;
            text-align: center;
            padding: 100px 0;
        }

        .not-found p {
            font-family: 'DM Serif Display', serif;
            font-size: 24px;
            color: var(--text-muted);
            margin-bottom: 28px;
        }
    </style>
</head>
<body>

<header>
    <div class="logo">Booklist</div>
    <nav>
        <a href="${pageContext.request.contextPath}/user/views">전체 목록</a>
        <a href="${pageContext.request.contextPath}/member/login">로그인</a>
    </nav>
</header>

<main>
    <div class="wrapper">
        <c:choose>
            <c:when test="${not empty book}">

                <%-- 왼쪽: 표지 --%>
                <div class="cover-frame">
                    <c:choose>
                        <c:when test="${not empty book.image}">
                            <img src="${pageContext.request.contextPath}${book.image}" alt="${book.bookname}">
                        </c:when>
                        <c:otherwise>
                            <div class="no-cover">No Image</div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <%-- 오른쪽: 정보 --%>
                <div class="info-col">
                    <span class="tag">도서 상세</span>

                    <div class="book-title">${book.bookname}</div>
                    <div class="book-author">${book.author} 저</div>

                    <hr class="divider">

                    <div class="meta-list">
                        <div class="meta-row">
                            <span class="meta-label">출판사</span>
                            <span class="meta-value">${book.publisher}</span>
                        </div>
                        <div class="meta-row">
                            <span class="meta-label">ISBN</span>
                            <span class="meta-value">${book.isbn}</span>
                        </div>
                    </div>

                    <div class="price-block">
                        <span class="price-label">판매가</span>
                        <div class="price-value">
                            <c:choose>
                                <c:when test="${not empty book.price}">
                                    ${book.price}<span class="price-unit">원</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="font-size:20px; color:var(--text-muted); font-family:'DM Sans',sans-serif; font-weight:300;">가격 미정</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="btn-group">
                        <a href="${pageContext.request.contextPath}/user/list" class="btn btn-back">← 목록</a>
                        <a href="#" class="btn btn-cart">장바구니</a>
                        <a href="#" class="btn btn-buy">바로구매</a>
                        
                        <input type="hidden" name="userid" value="<%= session.getAttribute("userid") %>">
                    </div>
                </div>

            </c:when>
            <c:otherwise>
                <div class="not-found">
                    <p>해당 도서를 찾을 수 없습니다.</p>
                    <a href="${pageContext.request.contextPath}/user/views" class="btn btn-back">← 목록으로</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</main>

</body>
</html>