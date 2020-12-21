/**
 * 
 */
// 데이터 유효성 검증(Validation)
$(function(){
	
	var form = $('.register > form');
	
	form.submit(function(){
		
		// 아이디 중복 확인
		if(!isUidOk){
			alert('아이디를 확인 하세요.');
			return false;
		}
		
		// 비밀번호 일치여부 확인
		if(!isPassOk){
			alert('비밀번호를 확인 하세요.');
			return false;
		}
		    			
		// 이름 한글여부 확인
		if(!isNameOk){
			alert('이름은 한글로 입력 하세요.');
			return false;
		}
		
		// 별명 중복 확인
		if(!isNickOk){
			alert('별명을 확인 하세요.');
			return false;
		}    			   			
		// 전송시작
		return true;
	});
});;