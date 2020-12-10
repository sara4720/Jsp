<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 시작페이지 요청(현재 로그인 사용자 정보 확인하여 로그인을 했으면 시작페이지가 리스트 페이지로)
	MemberBean mb = (MemberBean) session.getAttribute("smember");

	if(mb == null){
		// 로그인을 안했으면 로그인 페이지 forward
		pageContext.forward("./user/login.jsp");
	} else{
		// 로그인을 했으면 리스트 페이지 forward
		pageContext.forward("./list.jsp");
	}

	// response.sendRedirect("./user/login.jsp");
%>