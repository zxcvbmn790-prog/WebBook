<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="hero-section">
    <div class="hero-text">
        <span class="hero-badge">2026 SPRING COLLECTION</span>
        <h1>지금 가장 눈에 띄는 도서들을 만나보세요</h1>
        <p>베스트셀러부터 추천 도서까지, 감각적인 온라인 서점 경험을 구성합니다.</p>
    </div>
</section>

<section class="catalog-toolbar">
    <form action="${pageContext.request.contextPath}/book/list" method="get" class="modern-search">
        <select name="searchType" class="search-select">
            <option value="title" ${param.searchType eq 'title' || empty param.searchType ? 'selected="selected"' : ''}>도서명</option>
            <option value="author" ${param.searchType eq 'author' ? 'selected="selected"' : ''}>저자</option>
        </select>

        <input type="text"
               name="keyword"
               value="${param.keyword}"
               class="search-input"
               placeholder="찾고 싶은 책이나 저자를 검색해보세요">

        <button type="submit" class="btn-search">검색</button>
    </form>
</section>

<section class="catalog-section">
    <c:choose>
        <c:when test="${not empty list}">
            <div class="modern-grid">
                <c:forEach var="book" items="${list}">
                    <div class="modern-card">
                        <a href="${pageContext.request.contextPath}/book/view?isbn=${book.isbn}" class="card-link">
                            <div class="modern-img">
                                <c:choose>
                                    <c:when test="${not empty book.image}">
                                        <img src="${book.image}" alt="${book.bookname}">
                                    </c:when>
                                    <c:otherwise>
                                        <div class="no-img">NO IMAGE</div>
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

                        <div class="card-actions">
                            <a href="${pageContext.request.contextPath}/book/view?isbn=${book.isbn}" class="mini-btn light">상세보기</a>
                            <a href="${pageContext.request.contextPath}/book/view?isbn=${book.isbn}" class="mini-btn dark">담으러 가기</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>

        <c:otherwise>
            <div class="empty-box">
                <h3>등록된 도서가 없습니다.</h3>
                <p>초기 데이터 자동 등록을 붙이면 이 화면이 바로 채워지게 만들 수 있어.</p>
            </div>
        </c:otherwise>
    </c:choose>
</section>