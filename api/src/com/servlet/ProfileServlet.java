package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.mybatis.MyBatiser;
import com.session.manager.MySessionContext;
import com.util.Base64Util;
import com.util.Encryption;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/v1/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProfileServlet() {
        super();
    }

	/**
	 * 处理功能：处理获取个人信息的请求
	 * 请求参数：无
	 * 返回参数：status(int),user_id(String),email(String),user_name(String),avatar_url(String)，sex(String),register(Data)
	 * 状态码说明：
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//json对象
		JSONObject jsonObject = new JSONObject();
		
		//状态码
		int status = 0;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					if(session == null) {
						status = 401;
					}
					else {
						String json = (String) session.getAttribute("userMemory");
						User user = JSON.parseObject(json,User.class);
						jsonObject.put("user_id",user.getUser_id());					
					    jsonObject.put("email",user.getEmail());					
					    jsonObject.put("user_name",user.getUser_name());					
					    jsonObject.put("avatar_url",user.getAvatar_url());
					    jsonObject.put("register_time",user.getRegister_time().toString());
					    if(user.getGender()==1) {
					    	jsonObject.put("gender","男");
					    }
					    else if(user.getGender()==2) {
					    	jsonObject.put("gender","女");
					    }
					    else {
					    	jsonObject.put("gender","未知");
					    }
					    
	                    status = 200;
					}
				}
			}
		}
		if(status == 0)status = 401;
		
		jsonObject.put("status",status);
		response.getWriter().write(jsonObject.toJSONString());
	}

	/**
	 * 处理功能：处理修改个人信息的请求
	 * 请求参数：password(String),user_name(String),avatar_base64(String),sex(String,仅限)
	 * 返回参数：status(int)
	 * 状态码说明：
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//状态码
		int status = 0;
			
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					if(session == null) {
						status = 401;
					}
					else {
						String json = (String) session.getAttribute("userMemory");
						User user = JSON.parseObject(json,User.class);
						String password1 = request.getParameter("password");
						String user_name = request.getParameter("user_name");
						String avatar_base64 = request.getParameter("avatar_base64");
						String sex = request.getParameter("gender");
						
						//密码
						if(password1 != null) {
							String salt = Encryption.getSalt();
							String password2 = Encryption.encrypt(request.getParameter("password"), salt);
							user.setSalt(salt);
							user.setPassword(password2);
						}
						
						//昵称
						if(user_name != null) {
							user.setUser_name(user_name);
						}
						
						//头像
						if(avatar_base64 != null) {
							if(Base64Util.changeBase64ToImage(avatar_base64, user.getUser_id()+".jpg")) {
								String avatar_url = "http://"+request.getHeader("Host")+"/avatar/"+user.getUser_id()+".jpg";
								System.out.println(request.getServerPort());
								user.setAvatar_url(avatar_url);
								System.out.println("保存成功");
							}
							else {
								status = 408;//base64码格式错误
								System.out.println("保存失败");
							}
							
						}
						
						//性别
						if(sex != null) {
							if(sex.contentEquals("男"))user.setGender(1);
							else if(sex.contentEquals("女"))user.setGender(2);
							else if(sex.contentEquals("未知"))user.setGender(0);
							
						}
							
						//存入数据库
						if(status != 500) {
							MyBatiser myBatiser = new MyBatiser();
							if(myBatiser.updateUser(user) == MyBatiser.SUCCESS) {
								status = 201;
							}
							else {
								status = 500;
								System.out.println("存入的数据库失败");
							}
							myBatiser.closeSqlSession();
						}
						
						//更新session里的userMemory
						session.removeAttribute("userMemory");
						session.setAttribute("userMemory", JSON.toJSONString(user));
					}
				}
			}
		}
		if(status == 0)status =401;
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status",status);
		response.getWriter().write(jsonObject.toJSONString());
	}
	

}
