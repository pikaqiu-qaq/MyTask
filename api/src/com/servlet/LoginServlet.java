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
 * �����¼��Servelt
 */
@SuppressWarnings("serial")
@WebServlet("/v1/login")
public class LoginServlet extends HttpServlet {
	
	public LoginServlet() {
        super();  
    }

	/**
	 * �����ܣ�����ͻ��˵��û���¼����
	 * ���������user_id(String,emailҲ��),password(String)
	 * ���ز�����status(int)
	 * ״̬��˵���� 200---->�����¼
	 *             404---->�û�δע��
	 *             422---->�������
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
				response.setCharacterEncoding("utf-8");
		        request.setCharacterEncoding("utf-8");
		        
				//��ȡ�ͻ��˴����Ĳ���
				String user_id = request.getParameter("user_id");
				String password = request.getParameter("password");
				
				//�������ݿ��������󣬲���ѯָ��user_id���û�
				MyBatiser myBatiser = new MyBatiser();
				User user = myBatiser.selectUserByUser_id(user_id);
				if(user==null) {
					user = myBatiser.selectUserByEmail(user_id);
				}
				myBatiser.closeSqlSession();
				
				//��¼��Ӧ״̬��
				Integer status;
				
				//���е�¼��֤
				if(user == null) {//�û�������
					status = 404;
				}
				else{
					String salt = user.getSalt();
					password = Encryption.encrypt(password, salt);
					if(!user.getPassword().equals(password)) {//������󣬲������¼
						status = 422;
					}
					else {//������ȷ�������¼
						status = 200;
						//���û���Ϣ��jsonString����ʽ�����session�У������´����¼
						request.getSession().invalidate();
						HttpSession session = request.getSession(true);
						session.setMaxInactiveInterval(60*60*24*2);//���������
						session.setAttribute("userMemory", JSON.toJSONString(user));
						

					}
					
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", status);
				String json = jsonObject.toJSONString();
				response.getWriter().write(json);
				
	}
}