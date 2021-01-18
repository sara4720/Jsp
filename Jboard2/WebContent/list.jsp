<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글목록</title>
    <link rel="stylesheet" href="/Jboard2/css/style.css">    
</head>
<body>
    <div id="wrapper">
        <section id="board" class="list">
            <h3>글목록</h3>
            <article>
                <p>
                    ${sessionScope.smember.nick}님 반갑습니다.
                    <a href="/Jboard2/user/logout.do" class="logout">[로그아웃]</a>
                </p>
                <table border="0">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                    <c:forEach var="article" items="${articles}">
	                    <tr>
	                        <td>${listCount = listCount - 1}</td>
	                        <td><a href="/Jboard2/view.do">${article.title}</a>&nbsp;[${article.comment}]</td>
	                        <td>${article.nick}</td>
	                        <td>${article.rdate}</td>
	                        <td>${article.hit}</td>
	                    </tr>
                    </c:forEach>
                </table>
            </article>

            <!-- 페이지 네비게이션 -->
            <div class="paging">
                <a href="#" class="prev">이전</a>
                <c:forEach var="i" begin="1" end="${lastPgNum}">
                	<a href="/Jboard2/list.do?pg=${i}" class="num">${i}</a>                
                </c:forEach>                
                <a href="#" class="next">다음</a>
            </div>

            <!-- 글쓰기 버튼 -->
            <a href="/Jboard2/write.do" class="btnWrite">글쓰기</a>

        </section>
    </div>    
</body>
</html>