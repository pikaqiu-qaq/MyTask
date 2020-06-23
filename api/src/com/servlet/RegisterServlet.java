package com.servlet;

import java.io.IOException;
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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/v1/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	/**
	 * 处理功能：处理客户端的用户注册请求
	 * 请求参数：email(String),password(String),user_name(String,非必须，默认为“anonymous_user”)
	 * 返回参数：status(int)，user_id(String,若注册失败则为null)
	 * 状态码说明： 201---->注册成功
	 *             404---->注册失败，邮箱已被注册
	 *             500---->注册失败，服务器出错
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        //获取客户端传来的参数
		String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        if(user_name == null)user_name="anonymous_user";
		String salt = Encryption.getSalt();
		String password = Encryption.encrypt(request.getParameter("password"), salt);
		
		//响应状态码
		Integer status;
				
		//生成的account
	    String user_id = null;
		
		//创建数据库操作类对象，并查询此邮箱是否被注册过
		MyBatiser myBatiser = new MyBatiser();
		User user = myBatiser.selectUserByEmail(email);
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
			user.setUser_name(user_name);
			
			//尝试向数据库添加用户
			int result = myBatiser.addUser(user);
			if(result == MyBatiser.SUCCESS) {//注册成功
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
}
