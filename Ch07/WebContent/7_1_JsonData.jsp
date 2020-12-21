<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String json = "{\"uid\": \"a101\", \"name\": \"홍길동\", \"hp\": \"010-1234-5678\", \"pos\": \"사원\"}";
	out.print(json);
%>