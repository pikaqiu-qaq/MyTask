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
	
	public static final int ADD_SUCCESS = 1;//添加用户成功
	public static final int ADD_FAILED = -1;//添加用户失败
	public static final int UPDATE_SUCCESS = 1;//修改用户成功
	public static final int UPDATE_FAILED = -1;//修改用户失败
	public static final int DELETE_SUCCESS = 1;//删除用户成功
	public static final int DELETE_FAILED = -1;//删除用户失败
	public static final int ADD_EXIST = 2;//用户已经存在
	public static final int NOT_FOUND = 0;//未找到指定用户
	
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
    	if(selectByEmail(user.getEmail())!=null)return ADD_EXIST;
    	try {
    		userMapper.addUser(user);
    		session.commit();
    		return ADD_SUCCESS;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return ADD_FAILED;
    	}
    }
    
    /*
     * 删除指定user_id的用户
     */
    public int deleteUser(User user) {
    	if(this.selectByUser_id(user.getUser_id())==null)return NOT_FOUND;
    	try {
           userMapper.deleteUser(user);
            session.commit();
            return DELETE_SUCCESS;
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            return DELETE_FAILED;
        }
    }
    
    /*
     * 修改指定用户
     */
    public int updateUser(User user) {
    	if(this.selectByUser_id(user.getUser_id())==null)return NOT_FOUND;
    	try {
    		userMapper.updateUser(user);
            session.commit();
            return UPDATE_SUCCESS;
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
            return UPDATE_FAILED;
        }
    }
    
    /*
     * 通过user_id查询用户,若没有此用户则返回null
     */
    public User selectByUser_id(String user_id) {
		return userMapper.selectUserByUserId(user_id);
    }
    
    /*
     * 通过email查询用户,若没有此用户则返回null
     */
    public User selectByEmail(String email) {
    	return userMapper.selectUserByEmail(email);
    }
    
    /*
     * 向comment数据表增加一条评论
     */
    public boolean addComment(Comment comment) {
    	try {
    		commentMapper.addNewsComment(comment);
    		session.commit();
    		return true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    
    /*
     * 通过url查询Comment，返回List<Comment>
     */
    public List<Comment> selectCommentByUrl(String url){
    	return commentMapper.selectCommentByUrl(url);
    }
    
}
