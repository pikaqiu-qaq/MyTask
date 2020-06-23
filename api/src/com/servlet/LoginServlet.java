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
 * 处理登录的Servelt
 */
@SuppressWarnings("serial")
@WebServlet("/v1/login")
public class LoginServlet extends HttpServlet {
	
	public LoginServlet() {
        super();  
    }

	/**
	 * 处理功能：处理客户端的用户登录请求
	 * 请求参数：user_id(String,email也可),password(String)
	 * 返回参数：status(int)
	 * 状态码说明： 200---->允许登录
	 *             404---->用户未注册
	 *             422---->密码错误
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
				response.setCharacterEncoding("utf-8");
		        request.setCharacterEncoding("utf-8");
		        
				//获取客户端传来的参数
				String user_id = request.getParameter("user_id");
				String password = request.getParameter("password");
				
				//创建数据库操作类对象，并查询指定user_id的用户
				MyBatiser myBatiser = new MyBatiser();
				User user = myBatiser.selectUserByUser_id(user_id);
				if(user==null) {
					user = myBatiser.selectUserByEmail(user_id);
				}
				myBatiser.closeSqlSession();
				
				//登录响应状态码
				Integer status;
				
				//进行登录验证
				if(user == null) {//用户不存在
					status = 404;
				}
				else{
					String salt = user.getSalt();
					password = Encryption.encrypt(password, salt);
					if(!user.getPassword().equals(password)) {//密码错误，不允许登录
						status = 422;
					}
					else {//密码正确，允许登录
						status = 200;
						//将用户信息已jsonString的形式存放在session中，便于下次免登录
						request.getSession().invalidate();
						HttpSession session = request.getSession(true);
						session.setMaxInactiveInterval(60*60*24*2);//最多存活两天
						session.setAttribute("userMemory", JSON.toJSONString(user));
						

					}
					
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", status);
				String json = jsonObject.toJSONString();
				response.getWriter().write(json);
				
	}
}