
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    ${list}
<!DOCTYPE html>
<html lang="ko">

    <div class="container mt-5">
        <h2 class="mb-4">게시판</h2>   
        <button class="btn btn-primary" 
        onclick="location.href='/board/write'">글쓰기</button>
        
        <table class="table table-bordered table-hover">
            <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

       