package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.bean.Comment;
import com.mybatis.MyBatiser;

/**
 * Servlet implementation class SaveCommentServlet
 */
@WebServlet("/SaveCommentServlet")
public class SaveCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
		//response.getWriter().write("访问这个servlet应该用post请求，大傻子！");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        String content=request.getParameter("content");
        String news_id=request.getParameter("news_id");
        String user_id=request.getParameter("user_id");
        long create_time = System.currentTimeMillis();
        
        Integer status;
        MyBatiser myBatiser = new MyBatiser();
        
        Comment comment=new Comment();
        comment.setUser_id(user_id);
        comment.setCreate_time(create_time);
        comment.setNews_id(news_id);
        comment.setContent(content);
        boolean result= myBatiser.addComment(comment);
        
        if(result) {
			status = 200;
		}
		else {
			status = 401;
		}
        
        //关闭SqlSession对象
        myBatiser.closeSqlSession();
  		
  		//返回响应信息
  		JSONObject jsonObject = new JSONObject();
  		jsonObject.put("status", status);
  		String json = jsonObject.toJSONString();
  		response.getWriter().write(json);        		
	}

}
