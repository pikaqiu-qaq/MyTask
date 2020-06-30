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
import com.bean.Comment_anime;
import com.bean.User;
import com.mybatis.MyBatiser;
import com.session.manager.MySessionContext;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/v1/anime/comment")
public class Comment_animeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Comment_animeServlet() {
        super();
    }

	/**
	 * 澶勭悊鍔熻兘锛氳幏鍙栨寚瀹歯ews_id鐨勮瘎璁�
	 * 璇锋眰鍙傛暟锛歯ews_id(String)
	 * 杩斿洖鍙傛暟锛歴tatus(int),List<Comment>
	 * 鐘舵�佺爜璇存槑锛� 200---->鎴愬姛杩斿洖鍖呭惈璇勮鐨勯潪绌洪泦鍚�
	 *             404---->鏁版嵁搴撲腑娌℃湁姝よ祫璁殑璇勮锛岃繑鍥炵殑璇勮鏁版嵁涓簄ull
	 *             
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getComments_anime(request, response);
	}

	/**
	 * 璇锋眰澶达細鍖呭惈鎼哄甫sessionid鐨刢ookie
	 * 澶勭悊鍔熻兘锛氭帴鏀跺鎴风鐨勮瘎璁轰俊鎭紝瀛樺叆鏁版嵁搴�
	 * 璇锋眰鍙傛暟锛歯ews_id(String),content(String)
	 * 杩斿洖鍙傛暟锛歴tatus(int)
	 * 鐘舵�佺爜璇存槑锛� 401---->鐢ㄦ埛娌℃湁鏉冮檺杩涜姝ゆ搷浣�
	 *             200---->鎴愬姛灏嗗鎴风浼犳潵鐨勮瘎璁轰俊鎭坊鍔犲埌鏁版嵁搴�        
	 *             500---->娣诲姞澶辫触锛屾湇鍔″櫒鍑洪敊鎴栧弬鏁伴儴鍒嗕负null
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(check_post(request)) {
			postComment_anime(request,response);
		}
		else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 401);
			String json = jsonObject.toJSONString();
			response.getWriter().write(json);
		}
	}

	/**
	 * 璇锋眰澶达細鍖呭惈鎼哄甫sessionid鐨刢ookie
	 * 澶勭悊鍔熻兘锛氭帴鏀跺鎴风浼犳潵鐨勮瘎璁篿d锛屼粠鏁版嵁搴撲腑鍒犻櫎鎸囧畾id鐨勮瘎璁�
	 * 璇锋眰鍙傛暟锛歩d(long)
	 * 杩斿洖鍙傛暟锛歴tatus(int)
	 * 鐘舵�佺爜璇存槑锛� 401---->鐢ㄦ埛娌℃湁鏉冮檺杩涜姝ゆ搷浣�
	 *             200---->鎴愬姛浠庢暟鎹簱鍒犻櫎鎸囧畾id鐨勮瘎璁�
	 *             500---->鍒犻櫎澶辫触锛屾湇鍔″櫒鍑洪敊
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_s = getStringFromStream(request);
		long id = Long.parseLong(id_s.substring(3, id_s.length()));
		if(check_delete(request,id)) {
			deleteComment_anime(request,response,id);
		}
		else {//鏉冮檺涓嶅
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 401);
			String json = jsonObject.toJSONString();
			response.getWriter().write(json);
		}
	}
	
	/*
	 * 鑾峰彇delete璇锋眰閲岀殑鍙傛暟
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
	 * 妫�楠岀敤鎴穚ost鏉冮檺
	 */
	private boolean check_post(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//瀵规瘡涓猚ookie杩涜鎼滅储
			for(Cookie c:cookies) {
				if(c.getName().contentEquals("JSESSIONID")) {
					HttpSession session = MySessionContext.getSession(c.getValue());
					if(session != null) {
						String json = (String) session.getAttribute("userMemory");
						if(json == null)return false;
						else {
							User user = JSON.parseObject(json,User.class);
							String user_id = user.getUser_id();
							if(user_id != null)return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * 妫�楠岀敤鎴穌elete鏉冮檺
	 */
	private boolean check_delete(HttpServletRequest request,long id) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//瀵规瘡涓猚ookie杩涜鎼滅储
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
							Comment_anime comment_anime = myBatiser.selectComment_animeById(id);
							if(comment_anime != null && user_id.contentEquals(comment_anime.getUser_id()))return true;
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
	private void getComments_anime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//璁剧疆缂栫爜
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//鑾峰彇瀹㈡埛绔紶鏉ョ殑news_id
		String anime_id = request.getParameter("anime_id");
		
		//鍒涘缓涓�涓暟鎹簱鎿嶄綔瀵硅薄
		MyBatiser myBatiser = new MyBatiser();
		
		//浠庢暟鎹簱鑾峰彇璇勮
		List<Comment_anime> animecomments = myBatiser.selectComment_animeByAnime_id(anime_id);	
		
		//鐘舵�佺爜
		int status;
		
		//楠岃瘉鏄惁鏈夎瘎璁�
		if(animecomments.isEmpty()) {
			status=404;
		}
		else {
			status=200;
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		jsonObject.put("comments",animecomments);
		String json = jsonObject.toJSONString();

	    //瀛樺叆response锛岃繑鍥炵粰瀹㈡埛绔�
		response.getWriter().write(json);
	}
	
	
	/*
	 * 
	 */
	private void postComment_anime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//璁剧疆缂栫爜
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        //鑾峰彇璇锋眰鍙傛暟
        String anime_id=request.getParameter("anime_id");
        String content=request.getParameter("content");
        
        //鑾峰彇褰撳墠鏃堕棿
        long create_time = System.currentTimeMillis()/1000L;
        
        //鑾峰彇session閲岀殑user_id
        Cookie[] cookies = request.getCookies();
        String sessionid = null;
        for(Cookie c:cookies) {
        	if(c.getName().contentEquals("JSESSIONID")) {
        		sessionid = c.getValue();
        	}
        }
        HttpSession session = MySessionContext.getSession(sessionid);
        String json1 = (String)session.getAttribute("userMemory"); 
        User user = JSON.parseObject(json1,User.class);
		String user_id = user.getUser_id();
		
        //鐘舵�佺爜
        int status;
        
        //鍒涘缓鏁版嵁搴撴搷浣滃璞�
        MyBatiser myBatiser = new MyBatiser();
        
        //鏋勫缓Comment瀵硅薄瀹炰緥
        Comment_anime comment_anime=new Comment_anime();
        comment_anime.setAnime_id(anime_id);
        comment_anime.setUser_id(user_id);
        comment_anime.setCreate_time(create_time);
        comment_anime.setContent(content);
        
        //灏濊瘯鍚戞暟鎹簱娣诲姞
        boolean result= myBatiser.addComment_anime(comment_anime);
        
        if(result) {
			status = 200;//鏁版嵁搴撴坊鍔犺瘎璁烘垚鍔�
		}
		else {
			status = 500;//鏈嶅姟鍣ㄥ嚭閿�
		}
        
        //鍏抽棴SqlSession瀵硅薄
        myBatiser.closeSqlSession();
  		
  		//杩斿洖鍝嶅簲淇℃伅
  		JSONObject jsonObject = new JSONObject();
  		jsonObject.put("status", status);
  		String json2 = jsonObject.toJSONString();
  		response.getWriter().write(json2);        		
	}
	
	
	/*
	 * 
	 */
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
			
        //杩斿洖鍙傛暟
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		String json = jsonObject.toJSONString();
		response.getWriter().write(json);
	}

}