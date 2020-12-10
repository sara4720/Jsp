<%@page import="kr.co.jboard1.db.DBConfig"%>
<%@page import="kr.co.jboard1.dao.ArticleDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.bean.ArticleBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 로그인 사용자 정보 확인
	MemberBean mb = (MemberBean) session.getAttribute("smember");
	
	ArticleDao dao = ArticleDao.getInstance();
	
	// 글 순서 번호 구하기 (글목록의 번호)
	int total = dao.selectCountArticle();
	
	//목록 게시물 가져오기 
	List<ArticleBean> articles = dao.selectArticles();
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글목록</title>
    <link rel="stylesheet" href="/Jboard1/css/style.css">    
</head>
<body>
    <div id="wrapper">
        <section id="board" class="list">
            <h3>글목록</h3>
            <article>
                <p>
                    <%= mb.getName() %>님 반갑습니다.
                    <a href="/Jboard1/user/proc/logout.jsp" class="logout">[로그아웃]</a>
                </p>
                <table border="0">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                    
                    <% for(ArticleBean ab : articles){ %>
                    <tr>
                        <td><%= total-- %></td>
                        <td><a href="/Jboard1/view.jsp?seq=<%= ab.getSeq() %>"><%= ab.getTitle() %></a>&nbsp;[<%= ab.getComment() %>]</td>
                        <td><%= ab.getNick() %></td>
                        <td><%= ab.getRdate().substring(2, 10) %></td>
                        <td><%= ab.getHit() %></td>
                    </tr>
                    <% } %>
                    
                </table>
            </article>

            <!-- 페이지 네비게이션 -->
            <div class="paging">
                <a href="#" class="prev">이전</a>
                <a href="#" class="num current">1</a>                
                <a href="#" class="num">2</a>                
                <a href="#" class="num">3</a>                
                <a href="#" class="next">다음</a>
            </div>

            <!-- 글쓰기 버튼 -->
            <a href="/Jboard1/write.jsp" class="btnWrite">글쓰기</a>

        </section>
    </div>    
</body>
</html>