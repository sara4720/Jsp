<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>3-5</title>
</head>
<body>

	<!-- 
		날짜 : 2020/12/01
		이름 : 김사라
		내용 : JSP Include 지시자 실습하기 교재 p91
		
		include 지시자
		- 공통의 전역파일을 삽입하는 지시자
		- 일반적으로 UI 모듈, 공통전역 파일 변수를 삽입할 때 사용
		- 정적타임에 삽입
		- 참고) include 태구는 동적타임에 삽입
	 -->
	 
	<h3>3-5. JSP Include 지시자</h3>
	
	
	<!-- header include 파일 삽입 -->
	<%@ include file ="./inc/_header.jsp" %>
	
	<main>
		<h1>Main 컨텐츠 영역</h1>
	</main>
	
	<!-- footer include 파일 삽입 -->
	<%@ include file = "./inc/_footer.jsp" %>
	

</body>
</html>