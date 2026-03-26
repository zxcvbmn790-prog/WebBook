<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script>
    /* == 이것은 객체 비교 */
    /* === 이것은 값 비교 */
    /* var msg = "${param.msg}";
    if(msg && msg !== ""){
    	alert(msg);
    } */
    var msg ="${param.msg}";
    console.log("${msg}"); //콘솔창에서 결과를 확인하면 ?msg=로그인성공!!
    if (msg && msg !== "") {
     alert(msg);
    }
    </script>
<div class="index">
<h1 class="index-title">메인 홈페이지</h1>
<p><img src="/img/bonobono.webp">

<a href="/logout">로그아웃</a>[${loginUser}]</p>
</div>