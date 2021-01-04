<%@page import="kr.co.farmstory1.bean.Termsbean"%>
<%@page import="kr.co.farmstory1.db.Sql"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.farmstory1.db.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	
	DBConfig db = DBConfig.getInstance();
	Connection conn = db.getConnection();
	
	// 3단계	- SQL 실행객체 생성	
	PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_TERMS);
	
	// 4단계	- SQL 실행
	ResultSet rs = psmt.executeQuery();
	
	// 5단계	- 결과셋 처리
	Termsbean tb = new Termsbean();
	
	if(rs.next()){
		tb.setTerms(rs.getString(1));
		tb.setPrivacy(rs.getString(2));
	}
	
	// 6단계
	rs.close();
	psmt.close();
	conn.close();
%>
<section id="user" class="terms">
    <table>
        <caption>사이트 이용약관</caption>
        <tr>
            <td>
                <textarea readonly><%= tb.getTerms() %></textarea>
                <p>
                    <label><input type="checkbox" name="chk1"/>동의합니다.</label>
                </p>
            </td>
        </tr>
    </table>
    <table>
        <caption>개인정보 취급방침</caption>
        <tr>
            <td>
                <textarea readonly><%= tb.getPrivacy() %></textarea>
                <p>
                    <label><input type="checkbox" name="chk2"/>동의합니다.</label>
                </p>
            </td>
        </tr>
    </table>
    <div>
        <a href="/Farmstory1/user/login.jsp">취소</a>
        <a href="/Farmstory1/user/register.jsp" class="next">다음</a>
    </div>
</section>
<%@ include file="../_footer.jsp" %>