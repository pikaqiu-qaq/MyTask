package com.bean;
 
import java.util.List;

public interface Comment_animeMapper {
	List<Comment_anime> selectComment_animeByAnime_id(String url);
	void addComment_anime(Comment_anime comment_anime);
	void deleteComment_animeById(long id);
	Comment_anime selectComment_animeById(long id);

}
