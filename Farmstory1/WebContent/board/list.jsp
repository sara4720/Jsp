<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../_header.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	String gnb = request.getParameter("gnb");
	String cate = request.getParameter("cate");
	
	String path = "_aside_"+gnb+".jsp";
%>

<jsp:include page="<%=path %>">
	<jsp:param value="<%= cate %>" name="cate" />
</jsp:include>

        <section id="board" class="list">      
            <h3>글목록</h3>
            <article>
                <table border="0">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td><a href="/Farmstory1/board/view.jsp?gnb=<%= gnb %>&cate=<%=cate %>">제목입니다</a>&nbsp;[3]</td>
                        <td>길동ㅇ;이</td>
                        <td>20-12-29</td>
                        <td>12</td>
                    </tr>
                </table>
            </article>
            <!-- 페이지 네비게이션 -->
            <div class="paging">
                <a href="#" class="prev">이전</a>
                <a href="#" class="num current">1</a>
                <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="next">4</a>
            </div>
            <!-- 글쓰기 버튼 -->
            <a href="/Farmstory1/board/write.jsp?gnb=<%= gnb %>&cate=<%=cate %>" class="btnWrite">글쓰기</a>
        </section>
        
         <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file ="../_footer.jsp" %>