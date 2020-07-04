package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.mybatis.MyBatiser;

/**
 * Servlet implementation class BanServlet
 */
@WebServlet("/v1/ban")
public class BanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//���ñ���
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		MyBatiser myBatiser = new MyBatiser();
		User user = myBatiser.selectUserByUser_id(user_id);
		int status;
		JSONObject jsonObject = new JSONObject();
		if(user==null) {
			status = 404;//�û�������
		}
		else {
			if(user.getBan()==1) {
				jsonObject.put("state","����״̬");
				status = 200;//���سɹ�
			}
			else if(user.getBan()==0) {
				jsonObject.put("state","����״̬");
				status = 200;//���سɹ�
			}
			else {
				status = 500;//����������
			}
		}
		jsonObject.put("status",status);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ʡ����֤����ԱȨ�޵Ĳ���
		//���ñ���
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String user_id = request.getParameter("user_id");
		System.out.println("user_id:"+user_id);
		System.out.println("action:"+action);
		
		int status;
		if(action!=null) {
			MyBatiser myBatiser = new MyBatiser();
			if(action.contentEquals("����")) {
				if(myBatiser.banUserByUser_id(user_id)){
					
					status = 203;//����ɹ�
				}
				else {
					status = 500;//������������û�������
				}
			}
			else if(action.contentEquals("�ⶳ")) {
				if(myBatiser.unbanUserByUser_id(user_id)) {
					
					status = 204;//�ⶳ�ɹ�
				}
				else {
					status = 500;//������������û�������
				}
				
			}
			else {
				status = 402;//������ʽ����
			}
		}
		else {
			status = 403;//����ȱʧ
		}
		System.out.println(status);

        response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("utf-8");// ע���O�Þ�utf-8��tǰ�˽��յ������Ğ�y�a
		PrintWriter out = response.getWriter();
		out.write(status+"");

	}
}
