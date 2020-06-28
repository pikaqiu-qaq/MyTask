package com.servlet;
 
import java.io.IOException;
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
	 * 请求参数：email(String),password(String),user_name(String,非必须，默认为“anonymous_user”)，sex(String,仅限"0","1","2")
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
        String sexString = request.getParameter("gender");
        int gender = 0;
        if(sexString.contentEquals("男"))gender = 1;
        else if(sexString.contentEquals("女"))gender = 2;
		String salt = Encryption.getSalt();
		String password = Encryption.encrypt(request.getParameter("password"), salt);
		Date register_time = new Date();
		System.out.println(register_time.toString());
		//响应状态码
		Integer status;
				
		//生成的user_id
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
			
			//生成默认的头像url
			String avatar_url = "http://"+request.getHeader("Host")+"/avatar/default.jpg";
			
			//向User对象写入
			user.setUser_id(user_id);
			user.setEmail(email);
			user.setPassword(password);
			user.setSalt(salt);
			user.setUser_name(user_name);
			user.setAvatar_url(avatar_url);
			user.setRegister_time(register_time);
			user.setGender(gender);
			user.setBan(false);
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
