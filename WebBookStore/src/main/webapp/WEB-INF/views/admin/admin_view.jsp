<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* 관리자 헤더: 다크 배경 오버라이드 */
    header {
        background: var(--accent) !important;
        border-bottom-color: rgba(255,255,255,0.1) !important;
    }
    header .logo { color: #faf7f2 !important; }
    nav a { color: var(--accent-light) !important; }
    nav a:hover { color: #fff !important; }

    /* detail 전용 layout */
    main {
        padding-top: 60px;
        min-height: 100vh;
        display: flex;
        align-items: center;
    }
    .detail-wrap {
        width: 100%;
        max-width: 1000px;
        margin: 0 auto;
        padding: 64px 52px;
        display: grid;
        grid-template-columns: 280px 1fr;
        gap: 64px;
        align-items: start;
    }

    /* 관리자 수정/삭제 버튼 */
    .btn-update {
        flex: 1;
        background: var(--accent-light);
        color: var(--accent);
        border: 1px solid var(--border);
    }
    .btn-update:hover { background: var(--accent); color: #faf7f2; }

    .btn-delete {
        flex: 1;
        background: var(--surface);
        color: var(--red);
        border: 1px solid var(--border);
    }
    .btn-delete:hover { background: #fff5f3; color: #c0392b; border-color: #f5c6c0; }

    /* admin badge */
    .admin-badge {
        font-size: 11px; font-weight: 500;
        letter-spacing: 1.5px; text-transform: uppercase;
        background: var(--accent-light); color: var(--accent);
        padding: 3px 10px; border-radius: 2px; margin-left: 10px;
        vertical-align: middle;
    }

    @media (max-width: 720px) {
        .detail-wrap { grid-template-columns: 1fr; padding: 36px 20px; gap: 36px; }
        .cover-frame { max-width: 220px; margin: 0 auto; }
    }
</style>

<div class="detail-wrap">
    <c:choose>
        <c:when test="${not empty admin}">

            <%-- 왼쪽: 표지 --%>
            <div class="cover-frame">
                <c:choose>
                    <c:when test="${not empty admin.image}">
                        <img src="${pageContext.request.contextPath}${admin.image}" alt="${admin.bookname}">
                    </c:when>
                    <c:otherwise>
                        <div class="no-cover">No Image</div>
                    </c:otherwise>
                </c:choose>
            </div>

            <%-- 오른쪽: 정보 --%>
            <div class="info-col">
                <span class="tag">
                    도서 상세 <span class="admin-badge">Admin</span>
                </span>

                <div class="book-title">${admin.bookname}</div>
                <div class="book-author">${admin.author} 저</div>

                <hr class="divider">

                <div class="meta-list">
                    <div class="meta-row">
                        <span class="meta-label">출판사</span>
                        <span class="meta-value">${admin.publisher}</span>
                    </div>
                    <div class="meta-row">
                        <span class="meta-label">ISBN</span>
                        <span class="meta-value">${admin.isbn}</span>
                    </div>
                    <div class="meta-row">
                        <span class="meta-label">이미지</span>
                        <span class="meta-value" style="font-size:12px; color:var(--text-muted); word-break:break-all;">
                            <c:choose>
                                <c:when test="${not empty admin.image}">${admin.image}</c:when>
                                <c:otherwise>—</c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                </div>

                <div class="price-block">
                    <span class="price-label">판매가</span>
                    <div class="price-value">
                        <c:choose>
                            <c:when test="${not empty admin.price}">
                                ${admin.price}<span class="price-unit">원</span>
                            </c:when>
                            <c:otherwise>
                                <span style="font-size:20px; font-family:'DM Sans',sans-serif; font-weight:300; color:var(--text-muted);">가격 미정</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/admin/list" class="btn btn-back">← 목록</a>
                    <a href="${pageContext.request.contextPath}/admin/updateform?isbn=${admin.isbn}"
                       class="btn btn-update">수정</a>
                    <a href="${pageContext.request.contextPath}/admin/delete?isbn=${admin.isbn}"
                       class="btn btn-delete"
                       onclick="return confirm('정말 삭제하시겠습니까?')">삭제</a>
                </div>
            </div>

        </c:when>
        <c:otherwise>
            <div class="not-found" style="grid-column:1/-1;">
                <p>해당 도서를 찾을 수 없습니다.</p>
                <a href="${pageContext.request.contextPath}/admin/list" class="btn btn-back">← 목록으로</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
