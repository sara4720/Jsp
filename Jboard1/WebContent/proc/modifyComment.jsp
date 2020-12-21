<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.jboard1.dao.ArticleDao"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String content = request.getParameter("content");
	String seq = request.getParameter("seq");
	
	int result = ArticleDao.getInstance().updateComment(content, seq);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	out.print(json);
%>
