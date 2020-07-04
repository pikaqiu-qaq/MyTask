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

		//设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		MyBatiser myBatiser = new MyBatiser();
		User user = myBatiser.selectUserByUser_id(user_id);
		int status;
		JSONObject jsonObject = new JSONObject();
		if(user==null) {
			status = 404;//用户不存在
		}
		else {
			if(user.getBan()==1) {
				jsonObject.put("state","冻结状态");
				status = 200;//返回成功
			}
			else if(user.getBan()==0) {
				jsonObject.put("state","正常状态");
				status = 200;//返回成功
			}
			else {
				status = 500;//服务器出错
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
		//先省略验证管理员权限的部分
		//设置编码
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String user_id = request.getParameter("user_id");
		System.out.println("user_id:"+user_id);
		System.out.println("action:"+action);
		
		int status;
		if(action!=null) {
			MyBatiser myBatiser = new MyBatiser();
			if(action.contentEquals("冻结")) {
				if(myBatiser.banUserByUser_id(user_id)){
					
					status = 203;//冻结成功
				}
				else {
					status = 500;//服务器出错或用户不存在
				}
			}
			else if(action.contentEquals("解冻")) {
				if(myBatiser.unbanUserByUser_id(user_id)) {
					
					status = 204;//解冻成功
				}
				else {
					status = 500;//服务器出错或用户不存在
				}
				
			}
			else {
				status = 402;//参数格式错误
			}
		}
		else {
			status = 403;//参数缺失
		}
		System.out.println(status);

        response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("utf-8");// 注意設置為utf-8否則前端接收到的中文為亂碼
		PrintWriter out = response.getWriter();
		out.write(status+"");

	}
}
