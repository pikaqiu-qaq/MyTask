package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.session.manager.MySessionContext;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/v1/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	/**
	 * ����ͷ������Я��sessionid��cookie
	 * �����ܣ������˳���¼������
	 * �����������
	 * ���ز�����status(int)
	 * ״̬��˵���� 200---->�˳���¼�ɹ�
	 *             500---->���������������ѹ��ڣ������µ�¼
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��cookie�л��sessionid
		Cookie[] cookies = request.getCookies();
		String sessionid = null;
		for(Cookie c:cookies) {
			if(c.getName().contentEquals("JSESSIONID")) {
				sessionid = c.getValue();
			}
		}
	    
		//���ز���
		JSONObject jsonObject = new JSONObject();
		if(sessionid == null) {
			jsonObject.put("status", 500);
		}
		else {
			HttpSession session = MySessionContext.getSession(sessionid);
			session.invalidate();
			jsonObject.put("status", 200);
		}
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

}
