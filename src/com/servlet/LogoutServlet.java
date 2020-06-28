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
	 * 请求头：包含携带sessionid的cookie
	 * 处理功能：处理退出登录的请求
	 * 请求参数：无
	 * 返回参数：status(int)
	 * 状态码说明： 200---->退出登录成功
	 *             500---->服务器出错或身份已过期，请重新登录
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从cookie中获得sessionid
		Cookie[] cookies = request.getCookies();
		String sessionid = null;
		for(Cookie c:cookies) {
			if(c.getName().contentEquals("JSESSIONID")) {
				sessionid = c.getValue();
			}
		}
	    
		//返回参数
		JSONObject jsonObject = new JSONObject();
		if(sessionid == null) {
			jsonObject.put("status", 500);
		}
		else {
			System.out.println(sessionid);
			HttpSession session = MySessionContext.getSession(sessionid);
			session.invalidate();
			jsonObject.put("status", 200);
		}
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

}
