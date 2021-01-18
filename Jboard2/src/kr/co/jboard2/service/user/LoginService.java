package kr.co.jboard2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.controller.CommonService;
import kr.co.jboard2.dao.UserDao;
import kr.co.jboard2.vo.MemberVo;

public class LoginService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("POST")) {
			
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass");
			
			MemberVo vo = UserDao.getInstance().selectUser(uid, pass);
			
			if(vo != null) {
				// ȸ���� ������
				HttpSession session = req.getSession();
				session.setAttribute("smember", vo);
				
				return "redirect:/Jboard2/list.do";
				
			}else {
				// ȸ���� �ƴϸ�
				return "redirect:/Jboard2/user/login.do";
			}
			
		}else {
			return "/user/login.jsp";	
		}
	}

}
