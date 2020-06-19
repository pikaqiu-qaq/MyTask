package com.bean;

import java.util.List;

public interface CommentMapper {

	List<Comment> selectCommentByUrl(String url);
	void addNewsComment(Comment comment);
	void deleteCommnt(Comment comment);
    
}
