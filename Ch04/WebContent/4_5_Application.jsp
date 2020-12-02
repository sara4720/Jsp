<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>4.3</title>
</head>
<body>

<!-- 
	날짜 : 2020/12/01
	이름 : 김사라
	내용 : PageContext 내장객체 교재 p156
	
	PageContext 내장객체
	- 현재 JSP 페이지를 의미하는 객체
	- forward 기능을 갖는 객체
 -->
 
 <h3>4.4 JSP PageContext 내장객체</h3>
 
 <%
 	 pageContext.forward("./4_3_Out.jsp");
 %>
</body>
</html>