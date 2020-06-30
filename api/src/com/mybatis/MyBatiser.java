package com.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


import com.bean.Comment_anime;
import com.bean.Comment_animeMapper;
import com.bean.Comment_news;
import com.bean.Comment_newsMapper;
import com.bean.User;
import com.bean.UserMapper;

/*
 * 鏁版嵁搴撴搷浣滄柟娉曠被
 */
public class MyBatiser {

	public static int NOT_FOUND = 1;
	public static int EXIST = 2;
	public static int SUCCESS = 3;
	public static int FAILED = 4;

	private SqlSession session;
	private UserMapper userMapper;
	private Comment_newsMapper comment_newsMapper;
	private Comment_animeMapper comment_animeMapper;

	public MyBatiser() {
		MyBatisInit.init();
		this.session = MyBatisInit.getSession();
		this.userMapper = session.getMapper(UserMapper.class);
		this.comment_newsMapper = session.getMapper(Comment_newsMapper.class);
		this.comment_animeMapper = session.getMapper(Comment_animeMapper.class);
	}

	/*
	 * 鍏抽棴SqlSession瀵硅薄
	 */
	public void closeSqlSession() {
		session.close();
	}

	/*
	 * 杩斿洖褰撳墠鐢ㄦ埛鎬绘暟锛屽嵆user鏁版嵁琛ㄧ殑鎬昏鏁�
	 */
	public long totalUser() {
		return userMapper.totalUser();
	}

	/*
	 * 娣诲姞涓�涓敤鎴�
	 */
	public int addUser(User user) {
		if (selectUserByEmail(user.getEmail()) != null)
			return EXIST;
		try {
			userMapper.addUser(user);
			session.commit();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAILED;
		}
	}

	/*
	 * 鍒犻櫎鎸囧畾user_id鐨勭敤鎴�
	 */
	public int deleteUser(User user) {
		if (this.selectUserByUser_id(user.getUser_id()) == null)
			return NOT_FOUND;
		try {
			userMapper.deleteUser(user);
			session.commit();
			return SUCCESS;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			return FAILED;
		}
	}

	/*
	 * 淇敼鎸囧畾鐢ㄦ埛
	 */
	public int updateUser(User user) {
		if (this.selectUserByUser_id(user.getUser_id()) == null)
			return NOT_FOUND;
		try {
			userMapper.updateUser(user);
			session.commit();
			return SUCCESS;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			return FAILED;
		}
	}

	/*
	 * 閫氳繃user_id鏌ヨ鐢ㄦ埛,鑻ユ病鏈夋鐢ㄦ埛鍒欒繑鍥瀗ull
	 */
	public User selectUserByUser_id(String user_id) {
		return userMapper.selectUserByUser_id(user_id);
	}

	/*
	 * 閫氳繃email鏌ヨ鐢ㄦ埛,鑻ユ病鏈夋鐢ㄦ埛鍒欒繑鍥瀗ull
	 */
	public User selectUserByEmail(String email) {
		return userMapper.selectUserByEmail(email);
	}

	/*
	 * 鏌ヨ鎵�鏈夌敤鎴�
	 */
	public List<User> selectAllUsers(){
		return userMapper.selectAllUsers();
	}
	
	/*
	 * 鍐荤粨鐢ㄦ埛
	 */
	public boolean banUserByUser_id(String user_id) {
		try {
			User user = userMapper.selectUserByUser_id(user_id);
			if(user==null)return false;
			user.setBan(1);
			this.updateUser(user);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	/*
	 * 瑙ｅ喕
	 */
	public boolean unbanUserByUser_id(String user_id) {
		try {
			User user = userMapper.selectUserByUser_id(user_id);
			//if(user==null)return false;
			user.setBan(0);
			this.updateUser(user);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	/*
	 * 鍚慶omment鏁版嵁琛ㄥ鍔犱竴鏉¤瘎璁�
	 */
	public boolean addComment(Comment_news comment) {
		try {
			comment_newsMapper.addComment(comment);
			session.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 鏍规嵁id鍒犻櫎璇勮
	 */
	public boolean deleteCommentById(long id) {
		try {
			comment_newsMapper.deleteCommentById(id);
			session.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 閫氳繃news_id鏌ヨComment锛岃繑鍥濴ist<Comment>
	 */
	public List<Comment_news> selectCommentByNews_id(String news_id) {
		return comment_newsMapper.selectCommentByNews_id(news_id);
	}

	/*
	 * 鏍规嵁id鏌ヨ璇勮
	 */
	public Comment_news selectCommentById(long id) {
		return comment_newsMapper.selectCommentById(id);
	}

	
	
	/*
	 * 鍚慶omment_anime鏁版嵁琛ㄥ鍔犱竴鏉¤瘎璁�
	 */
	public boolean addComment_anime(Comment_anime comment_anime) {
		try {
			comment_animeMapper.addComment_anime(comment_anime);
			session.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 鏍规嵁id鍒犻櫎鐣墽璇勮
	 */
	public boolean deleteComment_animeById(long id) {
		try {
			comment_animeMapper.deleteComment_animeById(id);
			session.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 閫氳繃anime_id鏌ヨComment_anime锛岃繑鍥濴ist<Comment_anime>
	 */
	public List<Comment_anime> selectComment_animeByAnime_id(String anime_id) {
		return comment_animeMapper.selectComment_animeByAnime_id(anime_id);
	}

	/*
	 * 鏍规嵁id鏌ヨ璇勮
	 */
	public Comment_anime selectComment_animeById(long id) {
		return comment_animeMapper.selectComment_animeById(id);
	}
	
	public List<Comment_news> selectAllComment_news(){
		return comment_newsMapper.selectAllComment_news();
	}
	
	public List<Comment_anime> selectAllComment_anime(){
		return comment_animeMapper.selectAllComment_anime();
	}
}