package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.MyBatiser;

/**
 * Servlet implementation class CutCommentServlet
 */
@WebServlet("/v1/cut")
public class CutCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CutCommentServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("cut");
		 response.setContentType("text/plain;charset=UTF-8");
		 response.setCharacterEncoding("utf-8");// 注意設置為utf-8否則前端接收到的中文為亂碼
		
		String comment_type=request.getParameter("comment_type");
		String id=request.getParameter("id");
		if(comment_type.contentEquals("news")) {
			deleteComment_news(request,response,Long.parseLong(id));
		}
		else if(comment_type.contentEquals("anime")){
			deleteComment_anime(request,response,Long.parseLong(id));
		}
		else {
			int status = 500;
			PrintWriter out = response.getWriter();
			out.write(status+"");
		}
	}

	private void deleteComment_news(HttpServletRequest request, HttpServletResponse response, long id)
			throws ServletException, IOException {
		// 设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		// 状态码
		Integer status;

		// 创建数据库操作对象
		MyBatiser myBatiser = new MyBatiser();

		// 尝试通过id删除评论
		boolean result = myBatiser.deleteCommentById(id);

		if (result) {
			status = 200;// 从数据库删除评论成功
		} else {
			status = 500;// 服务器出错
		}

		PrintWriter out = response.getWriter();
		out.write(status+"");
	}
	
	private void deleteComment_anime(HttpServletRequest request, HttpServletResponse response,long id) throws ServletException, IOException {
		//璁剧疆缂栫爜
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		//鐘舵�佺爜
		Integer status;
		
		//鍒涘缓鏁版嵁搴撴搷浣滃璞�
		MyBatiser myBatiser = new MyBatiser();
		
		//灏濊瘯閫氳繃id鍒犻櫎璇勮
		boolean result=myBatiser.deleteComment_animeById(id);
		
		if(result) {
			status=200;//浠庢暟鎹簱鍒犻櫎璇勮鎴愬姛
		}
		else {
			status=500;//鏈嶅姟鍣ㄥ嚭閿� 
		}

		PrintWriter out = response.getWriter();
		out.write(status+"");
	}
}
