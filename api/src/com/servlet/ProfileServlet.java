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
	 * ���ز�����status(int),user_id(String),email(String),user_name(String),avatar_url(String)��sex(String),register(Data)
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
					    	jsonObject.put("gender","��");
					    }
					    else if(user.getGender()==2) {
					    	jsonObject.put("gender","Ů");
					    }
					    else {
					    	jsonObject.put("gender","δ֪");
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
	 * �����ܣ������޸ĸ�����Ϣ������
	 * ���������password(String),user_name(String),avatar_base64(String),sex(String,����)
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
								String avatar_url = "http://"+request.getHeader("Host")+"/avatar/"+user.getUser_id()+".jpg";
								System.out.println(request.getServerPort());
								user.setAvatar_url(avatar_url);
								System.out.println("����ɹ�");
							}
							else {
								status = 408;//base64���ʽ����
								System.out.println("����ʧ��");
							}
							
						}
						
						//�Ա�
						if(sex != null) {
							if(sex.contentEquals("��"))user.setGender(1);
							else if(sex.contentEquals("Ů"))user.setGender(2);
							else if(sex.contentEquals("δ֪"))user.setGender(0);
							
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
