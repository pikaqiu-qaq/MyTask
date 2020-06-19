package com.bean;

public class Comment {
	private int id;
	private String news_id;
	private String user_id;
	private Long create_time;
	private String content;
	//private String user_name;
	public Comment() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String url) {
		this.news_id = url;
	}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
