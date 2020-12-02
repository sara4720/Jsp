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
	내용 : Application 내장객체 교재 p161
	
	Application 내장객체
	- 현재 프로젝트를 실행하는 WAS(톰캣)을 의미하는 객체
 -->
 
 <h3>4.5 JSP Application 내장객체</h3>
 <p>
 	Was 정보 : <%= application.getServerInfo() %><br />
 	컨텍스트 루트 : <%= application.getContextPath() %><br />
</p>
</body>
</html>