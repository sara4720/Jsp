<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>5-1</title>
</head>
<body>
		<!-- 
		날짜 : 2020/12/02
		이름 : 김사라
		내용 : JSP Include 액션태그 실습하기 교재 p173
		
		include 지시자
		- 공통의 전역파일을 삽입하는 지시자
		- 일반적으로 UI 모듈, 공통전역 파일 변수를 삽입할 때 사용
		- 정적타임에 삽입
		- 참고) include 태구는 동적타임에 삽입
		
		include 태그
		- 공통의 전역파일을 삽입하는 테그
		- 일반적으로 UI 모듈을 삽입할 때 사용하는 include
		- 동적타임에 삽입
		- 참고) include 지시자는 정적타임에 삽입
	 -->
	 
	<h3>5.1 include 액션태그</h3>
	
	<%@ include file ="./inc/_header.jsp" %>
	<%@ include file ="./inc/_footer.jsp" %>
	
	<h4>include 액션태그로 삽입</h4>
	<jsp:include page = "./inc/_header.jsp"></jsp:include>
	<jsp:include page = "./inc/_footer.jsp"></jsp:include>
	
	
</body>
</html>