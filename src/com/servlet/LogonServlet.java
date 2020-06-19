package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.mybatis.MyBatiser;
import com.util.Encryption;
import com.util.IdFactory;

/**
 * 处理注册请求的Servlet
 */
@SuppressWarnings("serial")
@WebServlet("/LogonServlet")
public class LogonServlet extends HttpServlet {
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        //获取客户端传来的参数
		String email = request.getParameter("email");
		
		//String salt = "1235416541451542145145152152";
		
		
		String salt = Encryption.getSalt();
		
		String password = Encryption.encrypt(request.getParameter("password"), salt);
		
		//响应状态码
		Integer status;
				
		//生成的account
	    String user_id = null;
		
		//创建数据库操作类对象，并查询此邮箱是否被注册过
		MyBatiser myBatiser = new MyBatiser();
		User user = myBatiser.selectByEmail(email);
		//判定处理结果
		if(user == null) {//邮箱未被注册
			//新建User对象
			user = new User();
			//生成account
			user_id = IdFactory.creatUserId();
			user.setUser_id(user_id);
			//写入email和password,salt
			user.setEmail(email);
			user.setPassword(password);
			user.setSalt(salt);
			
			//尝试向数据库添加用户
			int result = myBatiser.addUser(user);
			if(result == MyBatiser.ADD_SUCCESS) {//注册成功
				status = 201;
			}
			else {//注册失败，服务器出错
				status = 500;
				user_id = null;
			}
		}
		else {//邮箱已被注册
			status = 404;
		}
		
		//关闭SqlSession对象
		myBatiser.closeSqlSession();
		
		//返回响应信息
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("user_id",user_id);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}