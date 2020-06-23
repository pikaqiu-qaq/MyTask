package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.Comment;
import com.bean.User;
import com.mybatis.MyBatiser;
import com.session.manager.MySessionContext;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/v1/news/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentServlet() {
        super();
    }

	/**
	 * �����ܣ���ȡָ��news_id������
	 * ���������news_id(String)
	 * ���ز�����status(int),List<Comment>
	 * ״̬��˵���� 200---->�ɹ����ذ������۵ķǿռ���
	 *             404---->���ݿ���û�д���Ѷ�����ۣ����ص���������Ϊnull
	 *             
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getComments(request, response);
	}

	/**
	 * �����ܣ����տͻ��˵�������Ϣ���������ݿ�
	 * ���������news_id(String),user_id(String),content(String)
	 * ���ز�����status(int)
	 * ״̬��˵���� 401---->�û�û��Ȩ�޽��д˲���
	 *             200---->�ɹ����ͻ��˴�����������Ϣ��ӵ����ݿ�        
	 *             500---->���ʧ�ܣ�������������������Ϊnull
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(check_post(request)) {
			postComment(request,response);
		}
		else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 401);
			String json = jsonObject.toJSONString();
			response.getWriter().write(json);
		}
	}

	/**
	 * �����ܣ����տͻ��˴���������id�������ݿ���ɾ��ָ��id������
	 * ���������id(long)
	 * ���ز�����status(int)
	 * ״̬��˵���� 401---->�û�û��Ȩ�޽��д˲���
	 *             200---->�ɹ������ݿ�ɾ��ָ��id������
	 *             500---->ɾ��ʧ�ܣ�����������
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_s = getStringFromStream(request);
		long id = Long.parseLong(id_s.substring(3, id_s.length()));
		if(check_delete(request,id)) {
			deleteComment(request,response,id);
		}
		else {//Ȩ�޲���
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 401);
			String json = jsonObject.toJSONString();
			response.getWriter().write(json);
		}
	}
	
	/*
	 * ��ȡdelete������Ĳ���
	 */
	private String getStringFromStream(HttpServletRequest req) {
			ServletInputStream is;
			try {
				is = req.getInputStream();
				int nRead = 1;
				int nTotalRead = 0;
				byte[] bytes = new byte[10240];
				while (nRead > 0) {
					nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
					if (nRead > 0)
						nTotalRead = nTotalRead + nRead;
				}
				String str = new String(bytes, 0, nTotalRead, "utf-8");
				return str;
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
	}

	
	/*
	 * �����û�postȨ��
	 */
	private boolean check_post(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//��ÿ��cookie��������
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					if(session != null) {
						String json = (String) session.getAttribute("userMemory");
						if(json == null)return false;
						else {
							User user = JSON.parseObject(json,User.class);
							String user_id = user.getUser_id();
							if(user_id.equals(request.getParameter("user_id")))return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * �����û�deleteȨ��
	 */
	private boolean check_delete(HttpServletRequest request,long id) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//��ÿ��cookie��������
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					if(session != null) {
						String json = (String) session.getAttribute("userMemory");
						if(json == null)return false;
						else {
							User user = JSON.parseObject(json,User.class);
							String user_id = user.getUser_id();

							MyBatiser myBatiser = new MyBatiser();
							Comment comment = myBatiser.selectCommentById(id);
							if(comment != null && user_id.contentEquals(comment.getUser_id()))return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	/*
	 * 
	 */
	private void getComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//��ȡ�ͻ��˴�����news_id
		String news_id = request.getParameter("news_id");
		
		//����һ�����ݿ��������
		MyBatiser myBatiser = new MyBatiser();
		
		//�����ݿ��ȡ����
		List<Comment> comments = myBatiser.selectCommentByNews_id(news_id);	
		
		//״̬��
		int status;
		
		//��֤�Ƿ�������
		if(comments.isEmpty()) {
			status=404;
		}
		else {
			status=200;
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("comments",comments);
		String json = jsonObject.toJSONString();

	    //����response�����ظ��ͻ���
		response.getWriter().write(json);
	}
	
	
	/*
	 * 
	 */
	private void postComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        //��ȡ�������
        String news_id=request.getParameter("news_id");
        String user_id=request.getParameter("user_id");
        String content=request.getParameter("content");
        long creat_time = System.currentTimeMillis()/1000L;
        
        //״̬��
        int status;
        
        //�������ݿ��������
        MyBatiser myBatiser = new MyBatiser();
        
        //����Comment����ʵ��
        Comment comment=new Comment();
        comment.setNews_id(news_id);
        comment.setUser_id(user_id);
        comment.setCreate_time(creat_time);
        comment.setContent(content);
        
        //���������ݿ����
        boolean result= myBatiser.addComment(comment);
        
        if(result) {
			status = 200;//���ݿ�������۳ɹ�
		}
		else {
			status = 500;//����������
		}
        
        //�ر�SqlSession����
        myBatiser.closeSqlSession();
  		
  		//������Ӧ��Ϣ
  		JSONObject jsonObject = new JSONObject();
  		jsonObject.put("status", status);
  		String json = jsonObject.toJSONString();
  		response.getWriter().write(json);        		
	}
	
	
	/*
	 * 
	 */
	private void deleteComment(HttpServletRequest request, HttpServletResponse response,long id) throws ServletException, IOException {
		//���ñ���
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		//״̬��
		Integer status;
		
		//�������ݿ��������
		MyBatiser myBatiser = new MyBatiser();
		
		//����ͨ��idɾ������
		boolean result=myBatiser.deleteCommentById(id);
		
		if(result) {
			status=200;//�����ݿ�ɾ�����۳ɹ�
		}
		else {
			status=500;//���������� 
		}
			
        //���ز���
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

}