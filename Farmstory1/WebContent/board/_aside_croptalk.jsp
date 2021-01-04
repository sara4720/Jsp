<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");
	String title = null;
	
	if(cate.equals("story")){
		title = "농작물 이야기";
	}else if(cate.equals("grow")){
		title = "텃밭 가꾸기";
	}else if(cate.equals("school")){
		title = "귀농학교";
	}
%>
<div id="sub">
    <div><img src="/Farmstory1/img/sub_top_tit3.png" alt="CROP TALK"></div>
    <section class="cate3">
        <aside>
            <img src="/Farmstory1/img/sub_aside_cate3_tit.png" alt="농작물이야기"/>

            <ul class="lnb">
                <li class="<%= cate.equals("story") ? "on" : "off" %>"><a href="/Farmstory1/board/list.jsp?gnb=croptalk&cate=story">농작물이야기</a></li>
                <li class="<%= cate.equals("grow") ? "on" : "off" %>"><a href="/Farmstory1/board/list.jsp?gnb=croptalk&cate=grow">텃밭가꾸기</a></li>
                <li class="<%= cate.equals("school") ? "on" : "off" %>"><a href="/Farmstory1/board/list.jsp?gnb=croptalk&cate=school">귀농학교</a></li>
            </ul>

        </aside>
        <article>
            <nav>
                <img src="/Farmstory1/img/sub_nav_tit_cate3_<%= cate %>.png" alt="귀농학교"/>
                <p>
                    HOME > 농작물이야기 > <em><%= title %></em>
                </p>
            </nav>

            <!-- 내용 시작 -->