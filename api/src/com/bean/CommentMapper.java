package com.bean;
 
import java.util.List;

public interface CommentMapper {
	List<Comment> selectCommentByNews_id(String url);
	void addComment(Comment comment);
	void deleteCommentById(long id);
	Comment selectCommentById(long id);

}
