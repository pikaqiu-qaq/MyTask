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
 * Servlet implementation class DeleteNewsCommentServlet
 */
@WebServlet("/DeleteNewsCommentServlet")
public class DeleteNewsCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewsCommentServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doDelete(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Integer status=410;
		String user_id=request.getParameter("user_id");
		String news_id=request.getParameter("news_id");
		int id=Integer.parseInt(request.getParameter("id"));
		MyBatiser myBatiser = new MyBatiser();
		Comment comment=myBatiser.selectCommentById(id);
		if(comment.getUser_id().contentEquals(user_id))
		{
			boolean success=myBatiser.deleteNewsComment(id);
			if(success)
				status=200;
			else
				status=401; 
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

}
