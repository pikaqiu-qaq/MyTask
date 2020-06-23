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
	 * 处理功能：获取指定news_id的评论
	 * 请求参数：news_id(String)
	 * 返回参数：status(int),List<Comment>
	 * 状态码说明： 200---->成功返回包含评论的非空集合
	 *             404---->数据库中没有此资讯的评论，返回的评论数据为null
	 *             
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getComments(request, response);
	}

	/**
	 * 处理功能：接收客户端的评论信息，存入数据库
	 * 请求参数：news_id(String),user_id(String),content(String)
	 * 返回参数：status(int)
	 * 状态码说明： 401---->用户没有权限进行此操作
	 *             200---->成功将客户端传来的评论信息添加到数据库        
	 *             500---->添加失败，服务器出错或参数部分为null
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
	 * 处理功能：接收客户端传来的评论id，从数据库中删除指定id的评论
	 * 请求参数：id(long)
	 * 返回参数：status(int)
	 * 状态码说明： 401---->用户没有权限进行此操作
	 *             200---->成功从数据库删除指定id的评论
	 *             500---->删除失败，服务器出错
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_s = getStringFromStream(request);
		long id = Long.parseLong(id_s.substring(3, id_s.length()));
		if(check_delete(request,id)) {
			deleteComment(request,response,id);
		}
		else {//权限不够
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 401);
			String json = jsonObject.toJSONString();
			response.getWriter().write(json);
		}
	}
	
	/*
	 * 获取delete请求里的参数
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
	 * 检验用户post权限
	 */
	private boolean check_post(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//对每个cookie进行搜索
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
	 * 检验用户delete权限
	 */
	private boolean check_delete(HttpServletRequest request,long id) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//对每个cookie进行搜索
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
		//设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//获取客户端传来的news_id
		String news_id = request.getParameter("news_id");
		
		//创建一个数据库操作对象
		MyBatiser myBatiser = new MyBatiser();
		
		//从数据库获取评论
		List<Comment> comments = myBatiser.selectCommentByNews_id(news_id);	
		
		//状态码
		int status;
		
		//验证是否有评论
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

	    //存入response，返回给客户端
		response.getWriter().write(json);
	}
	
	
	/*
	 * 
	 */
	private void postComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        //获取请求参数
        String news_id=request.getParameter("news_id");
        String user_id=request.getParameter("user_id");
        String content=request.getParameter("content");
        long creat_time = System.currentTimeMillis()/1000L;
        
        //状态码
        int status;
        
        //创建数据库操作对象
        MyBatiser myBatiser = new MyBatiser();
        
        //构建Comment对象实例
        Comment comment=new Comment();
        comment.setNews_id(news_id);
        comment.setUser_id(user_id);
        comment.setCreate_time(creat_time);
        comment.setContent(content);
        
        //尝试向数据库添加
        boolean result= myBatiser.addComment(comment);
        
        if(result) {
			status = 200;//数据库添加评论成功
		}
		else {
			status = 500;//服务器出错
		}
        
        //关闭SqlSession对象
        myBatiser.closeSqlSession();
  		
  		//返回响应信息
  		JSONObject jsonObject = new JSONObject();
  		jsonObject.put("status", status);
  		String json = jsonObject.toJSONString();
  		response.getWriter().write(json);        		
	}
	
	
	/*
	 * 
	 */
	private void deleteComment(HttpServletRequest request, HttpServletResponse response,long id) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		//状态码
		Integer status;
		
		//创建数据库操作对象
		MyBatiser myBatiser = new MyBatiser();
		
		//尝试通过id删除评论
		boolean result=myBatiser.deleteCommentById(id);
		
		if(result) {
			status=200;//从数据库删除评论成功
		}
		else {
			status=500;//服务器出错 
		}
			
        //返回参数
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

}