<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.jboard1.dao.UserDao"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	int count = UserDao.getInstance().selectCountUser(uid);
	
	// JSON 출력
	//String json = "{\"result\": "+count+"}";
	JsonObject json = new JsonObject();
	json.addProperty("result", count);
	
	out.print(json);
%>