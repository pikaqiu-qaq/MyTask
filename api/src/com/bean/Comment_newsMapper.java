package com.bean;

import java.util.List;

public interface Comment_newsMapper {
	List<Comment_news> selectCommentByNews_id(String url);

	void addComment(Comment_news comment);

	void deleteCommentById(long id);

	Comment_news selectCommentById(long id);
	
	List<Comment_news>selectAllComment_news();

}
