package kr.co.jboard2.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> instances = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// ������Ƽ ���� ��� ���ϱ�
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/urlMapping.properties";
		
		// ������Ƽ ���� �Է� ��Ʈ�� ����
		Properties prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// ������Ƽ ��ü �����ؼ� Service ��ü ����
		Iterator iter = prop.keySet().iterator();
		
		while(iter.hasNext()) {
			
			String key   = iter.next().toString();
			String value = prop.getProperty(key);
			
			try {
				
				Class obj = Class.forName(value);
				Object instance = obj.newInstance();
				
				instances.put(key, instance);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // init end
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}	
	
	public void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getContextPath();
		String uri  = req.getRequestURI();
		
		String key = uri.substring(path.length());
		
		CommonService instance = (CommonService) instances.get(key);		
		String result = instance.requestProc(req, resp);
		
		if(result.startsWith("redirect:")) {
			// �����̷�Ʈ
			String redirectUrl = result.substring(9);
			resp.sendRedirect(redirectUrl);
			
		}else if(result.startsWith("json:")) {
			
			PrintWriter out = resp.getWriter();
			out.print(result.substring(5));
			
		}else {
			// view ������
			RequestDispatcher dispatcher = req.getRequestDispatcher(result);
			dispatcher.forward(req, resp);	
		}
	}
}









