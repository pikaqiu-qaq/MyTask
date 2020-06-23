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
	 * �����ܣ�����ͻ��˵��û�ע������
	 * ���������email(String),password(String),user_name(String,�Ǳ��룬Ĭ��Ϊ��anonymous_user��)
	 * ���ز�����status(int)��user_id(String,��ע��ʧ����Ϊnull)
	 * ״̬��˵���� 201---->ע��ɹ�
	 *             404---->ע��ʧ�ܣ������ѱ�ע��
	 *             500---->ע��ʧ�ܣ�����������
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        //��ȡ�ͻ��˴����Ĳ���
		String email = request.getParameter("email");
        String user_name = request.getParameter("user_name");
        if(user_name == null)user_name="anonymous_user";
		String salt = Encryption.getSalt();
		String password = Encryption.encrypt(request.getParameter("password"), salt);
		
		//��Ӧ״̬��
		Integer status;
				
		//���ɵ�account
	    String user_id = null;
		
		//�������ݿ��������󣬲���ѯ�������Ƿ�ע���
		MyBatiser myBatiser = new MyBatiser();
		User user = myBatiser.selectUserByEmail(email);
		//�ж�������
		if(user == null) {//����δ��ע��
			//�½�User����
			user = new User();
			//����account
			user_id = IdFactory.creatUserId();
			user.setUser_id(user_id);
			//д��email��password,salt
			user.setEmail(email);
			user.setPassword(password);
			user.setSalt(salt);
			user.setUser_name(user_name);
			
			//���������ݿ�����û�
			int result = myBatiser.addUser(user);
			if(result == MyBatiser.SUCCESS) {//ע��ɹ�
				status = 201;
			}
			else {//ע��ʧ�ܣ�����������
				status = 500;
				user_id = null;
			}
		}
		else {//�����ѱ�ע��
			status = 404;
		}
		
		//�ر�SqlSession����
		myBatiser.closeSqlSession();
		
		//������Ӧ��Ϣ
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("user_id",user_id);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}
}
