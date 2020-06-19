package com.bean;

import java.util.List;

public interface CommentMapper {

	List<Comment> selectCommentByNews_id(String news_id);
	Comment selectCommentById(int id);
	void addNewsComment(Comment comment);
	void deleteNewsComment(int id);
    
}
