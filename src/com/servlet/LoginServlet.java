package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.mybatis.MyBatiser;
import com.util.Encryption;



/**
 * 处理登录的Servelt
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	 /**
     * @see HttpServlet#HttpServlet()
     */
	public LoginServlet() {
        super();  
    }
	
	/**
	 * @see 处理GET请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
		//获取客户端传来的参数
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		//创建数据库操作类对象，并查询指定user_id的用户
		MyBatiser myBatiser = new MyBatiser();
		User user = myBatiser.selectUserByUser_id(user_id);
		if(user==null) {
			user = myBatiser.selectUserByEmail(user_id);
		}
		myBatiser.closeSqlSession();
		
		//登录响应状态码
		Integer status;
		
		//进行登录验证
		if(user == null) {//用户不存在
			status = 404;
		}
		else{
			String salt = user.getSalt();
			password = Encryption.encrypt(password, salt);
			if(!user.getPassword().equals(password)) {//密码错误，不允许登录
				status = 422;
			}
			else {//密码正确，允许登录
				status = 200;
				//将用户信息已jsonString的形式存放在session中，便于下次免登录
				HttpSession session = request.getSession();
				session.setAttribute("userMemory", JSON.toJSONString(user));	
			}
			
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
		
	}

	/**
	 * @see 处理POST请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}