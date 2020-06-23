package com.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Comment;
import com.bean.CommentMapper;
import com.bean.User;
import com.bean.UserMapper;

/*
 * ���ݿ����������
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
     * �ر�SqlSession����
     */
    public void closeSqlSession() {
    	session.close();
    }
    
    
    /*
     * ���ص�ǰ�û���������user���ݱ��������
     */
    public long totalUser() {
    	return userMapper.totalUser();
    }
    
    
    /*
	 * ���һ���û�
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
     * ɾ��ָ��user_id���û�
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
     * �޸�ָ���û�
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
     * ͨ��user_id��ѯ�û�,��û�д��û��򷵻�null
     */
    public User selectUserByUser_id(String user_id) {
		return userMapper.selectUserByUser_id(user_id);
    }
    
    
    /*
     * ͨ��email��ѯ�û�,��û�д��û��򷵻�null
     */
    public User selectUserByEmail(String email) {
    	return userMapper.selectUserByEmail(email);
    }
    
    /*
     * ��comment���ݱ�����һ������
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
     * ����idɾ������
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
     * ͨ��news_id��ѯComment������List<Comment>
     */
    public List<Comment> selectCommentByNews_id(String news_id){
    	return commentMapper.selectCommentByNews_id(news_id);
    }

    
    /*
     * ����id��ѯ����
     */
	public Comment selectCommentById(long id) {
		return commentMapper.selectCommentById(id);
	}
	
	
    
}