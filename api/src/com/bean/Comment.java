package com.bean;
 
public class Comment {
	private long id;//���۵�id
	private String news_id;//������Ѷ��id
	private String user_id;//�û��˺�
	private long create_time;//���۷���ʱ��
	private String content;//��������
	
	public Comment() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNews_id() {
		return news_id;
	}

	public void setNews_id(String news_id) {
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
