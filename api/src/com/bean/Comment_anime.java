package com.bean;
 
public class Comment_anime {
	private long id;//评论的id
	private String news_id;//所属资讯的id
	private String user_id;//用户账号
	private long create_time;//评论发表时间
	private String content;//评论内容
	
	public Comment_anime() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnime_id() {
		return news_id;
	}

	public void setAnime_id(String news_id) {
		this.news_id = news_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	

}
