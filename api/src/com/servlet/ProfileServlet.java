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
	 * �����ܣ������ȡ������Ϣ������
	 * �����������
	 * ���ز�����status(int),user_id(String),email(String),user_name(String),avatar_url(String)
	 * ״̬��˵����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//json����
		JSONObject jsonObject = new JSONObject();
		
		//״̬��
		int status = 0;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					String json = (String) session.getAttribute("userMemory");
					if(json == null) {
						status = 500;
					}
					else {
						User user = JSON.parseObject(json,User.class);
						jsonObject.put("user_id",user.getUser_id());					
					    jsonObject.put("email",user.getEmail());					
					    jsonObject.put("user_name",user.getUser_name());					
					    jsonObject.put("avatar_url",user.getAvatar_url());					
	                    status = 200;
					}
				}
			}
		}
		if(status == 0)status = 401;
		
		response.getWriter().write(jsonObject.toJSONString());
	}

	/**
	 * �����ܣ������޸ĸ�����Ϣ������
	 * ���������password(String),user_name(String),avatar_base64(String)
	 * ���ز�����status(int)
	 * ״̬��˵����
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//״̬��
		int status = 0;
			
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					String json = (String) session.getAttribute("userMemory");
					
					if(json == null) {
						status = 500;
					}
					else {
						User user = JSON.parseObject(json,User.class);
						String password1 = request.getParameter("password");
						String user_name = request.getParameter("user_name");
						String avatar_base64 = request.getParameter("avatar_base64");
						
						//����
						if(password1 != null) {
							String salt = Encryption.getSalt();
							String password2 = Encryption.encrypt(request.getParameter("password"), salt);
							user.setSalt(salt);
							user.setPassword(password2);
						}
						
						//�ǳ�
						if(user_name != null) {
							user.setUser_name(user_name);
						}
						
						//ͷ��
						if(avatar_base64 != null) {
							if(Base64Util.changeBase64ToImage(avatar_base64, user.getUser_id()+".jpg")) {
								String avatar_url = "http://localhost:8080/avatar/"+user.getUser_id()+".jpg";
								user.setAvatar_url(avatar_url);
								System.out.println("����ɹ�");
							}
							else {
								status = 500;
								System.out.println("����ʧ��");
							}
							
						}
						
						//�������ݿ�
						if(status != 500) {
							MyBatiser myBatiser = new MyBatiser();
							if(myBatiser.updateUser(user) == MyBatiser.SUCCESS) {
								status = 201;
							}
							else {
								status = 500;
								System.out.println("��������ݿ�ʧ��");
							}
							myBatiser.closeSqlSession();
						}
						
						//����session���userMemory
						session.removeAttribute("userMomery");
						session.setAttribute("userMomery", JSON.toJSONString(user));
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
