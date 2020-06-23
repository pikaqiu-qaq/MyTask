package com.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Comment;
import com.bean.CommentMapper;
import com.bean.User;
import com.bean.UserMapper;

/*
 * 数据库操作方法类
 */
public class MyBatiser {
	
	public static int NOT_FOUND = 1;
	public static int EXIST = 2;
	public static int SUCCESS = 3;
	public static int FAILED = 4;
	
	private SqlSession session;
	private UserMapper userMapper;
	private CommentMapper commentMapper;
	
	public MyBatiser() {
		MyBatisInit.init();
    	this.session = MyBatisInit.getSession();
    	this.userMapper = session.getMapper(UserMapper.class);
    	this.commentMapper = session.getMapper(CommentMapper.class);
	}
    
	
	/*
     * 关闭SqlSession对象
     */
    public void closeSqlSession() {
    	session.close();
    }
    
    
    /*
     * 返回当前用户总数，即user数据表的总行数
     */
    public long totalUser() {
    	return userMapper.totalUser();
    }
    
    
    /*
	 * 添加一个用户
	 */
    public int addUser(User user) {
    	if(selectUserByEmail(user.getEmail())!=null)return EXIST;
    	try {
    		userMapper.addUser(user);
    		session.commit();
    		return SUCCESS;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return FAILED;
    	}
    }
    
    
    /*
     * 删除指定user_id的用户
     */
    public int deleteUser(User user) {
    	if(this.selectUserByUser_id(user.getUser_id())==null)return NOT_FOUND;
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
     * 修改指定用户
     */
    public int updateUser(User user) {
    	if(this.selectUserByUser_id(user.getUser_id())==null)return NOT_FOUND;
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
     * 通过user_id查询用户,若没有此用户则返回null
     */
    public User selectUserByUser_id(String user_id) {
		return userMapper.selectUserByUser_id(user_id);
    }
    
    
    /*
     * 通过email查询用户,若没有此用户则返回null
     */
    public User selectUserByEmail(String email) {
    	return userMapper.selectUserByEmail(email);
    }
    
    /*
     * 向comment数据表增加一条评论
     */
    public boolean addComment(Comment comment) {
    	try {
    		commentMapper.addComment(comment);
    		session.commit();
    		return true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    /*
     * 根据id删除评论
     */
    public boolean deleteCommentById(long id) {
    	try {
    		commentMapper.deleteCommentById(id);
    		session.commit();
    		return true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    
    /*
     * 通过news_id查询Comment，返回List<Comment>
     */
    public List<Comment> selectCommentByNews_id(String news_id){
    	return commentMapper.selectCommentByNews_id(news_id);
    }

    
    /*
     * 根据id查询评论
     */
	public Comment selectCommentById(long id) {
		return commentMapper.selectCommentById(id);
	}
	
	
    
}