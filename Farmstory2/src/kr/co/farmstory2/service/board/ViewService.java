package kr.co.farmstory2.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.controller.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.vo.ArticleVo;

public class ViewService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String seq = req.getParameter("seq");
		
		ArticleVo article = ArticleDao.getInstance().selectArticle(seq);
		
		req.setAttribute("article", article);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		return "/board/view.jsp";
	}

}
