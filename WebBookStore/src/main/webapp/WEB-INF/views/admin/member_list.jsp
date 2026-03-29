<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<style>
			.admin-container {
				max-width: 1000px;
				margin: 40px auto;
				padding: 20px;
				background: #fff;
				border-radius: 8px;
				box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
			}

			.admin-header {
				display: flex;
				justify-content: space-between;
				align-items: center;
				border-bottom: 2px solid #333;
				padding-bottom: 15px;
				margin-bottom: 20px;
			}

			.admin-header h2 {
				margin: 0;
				font-size: 24px;
				color: #222;
			}

			.member-table {
				width: 100%;
				border-collapse: collapse;
				text-align: center;
			}

			.member-table th {
				background-color: #f8f9fa;
				padding: 12px;
				border-top: 1px solid #ddd;
				border-bottom: 2px solid #ddd;
				font-weight: bold;
				color: #333;
			}

			.member-table td {
				padding: 15px 12px;
				border-bottom: 1px solid #eee;
				vertical-align: middle;
				color: #555;
			}

			/* ★ 마우스를 올렸을 때 클릭할 수 있다는 표시(손가락 모양)와 배경색 변경 */
			.member-table tbody tr {
				cursor: pointer;
				transition: background-color 0.2s;
			}

			.member-table tbody tr:hover {
				background-color: #f0f4ff;
			}

			.empty-msg {
				text-align: center;
				padding: 50px 0;
				color: #888;
				cursor: default;
			}
		</style>

		<div class="admin-header">
			<h2><i class="fa-solid fa-users"></i> 회원 관리</h2>
		</div>

		<div style="display: flex; justify-content: flex-end; margin-bottom: 20px;">
			<form action="${pageContext.request.contextPath}/admin/member/list" method="get"
				style="display: flex; gap: 8px;">
				<input type="text" name="keyword" value="${keyword}" placeholder="아이디 또는 닉네임 검색"
					style="padding: 10px 14px; border: 1px solid #ddd; border-radius: 4px; outline: none; width: 240px; font-size: 13px;">
				<button type="submit"
					style="padding: 10px 20px; background: #333; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: bold;">
					<i class="fa-solid fa-magnifying-glass"></i> 검색
				</button>
				<c:if test="${not empty keyword}">
					<a href="${pageContext.request.contextPath}/admin/member/list"
						style="padding: 10px 15px; background: #f8f9fa; color: #555; border: 1px solid #ddd; border-radius: 4px; text-decoration: none; font-size: 13px;">초기화</a>
				</c:if>
			</form>
		</div>

		<table class="member-table">
			<thead>
				<tr>
					<th>아이디</th>
					<th>닉네임</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty memberList}">
						<c:forEach var="member" items="${memberList}">
							<tr
								onclick="location.href='${pageContext.request.contextPath}/admin/member/detail?username=${member.username}'">
								<td><strong>${member.username}</strong></td>
								<td>${member.nickname}</td>
								<td>${member.email}</td>
								<td>${member.phone}</td>

								<td onclick="event.stopPropagation();">
									<c:if test="${member.username ne 'admin'}">
										<a href="${pageContext.request.contextPath}/admin/member/delete?username=${member.username}"
											class="action-btn danger" style="padding: 6px 12px; font-size: 13px;"
											onclick="return confirm('${member.username} 회원을 정말 강제 탈퇴 처리하시겠습니까?');">
											삭제
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr style="cursor: default;" onclick="event.stopPropagation();">
							<td colspan="5" class="empty-msg">가입된 회원이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		</div>