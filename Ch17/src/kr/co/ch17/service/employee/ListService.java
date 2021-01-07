package kr.co.ch17.service.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ch17.dao.EmployeeDao;
import kr.co.ch17.service.CommonService;
import kr.co.ch17.vo.EmployeeVo;

public class ListService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {

		EmployeeDao dao = EmployeeDao.getInstance();
		List<EmployeeVo> list = dao.selectEmployees();
		
		// �� ������Ʈ���� list�� �����ϱ� ���� request��ü�� ����
		req.setAttribute("list", list);
		
		return "/employee/list.jsp";
	}

}
