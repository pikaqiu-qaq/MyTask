package com.bean;

public class Comment_news {
	private long id;// ���۵�id
	private String news_id;// ������Ѷ��id
	private String user_id;// �û��˺�
	private long create_time;// ���۷���ʱ��
	private String content;// ��������
	private String user_name;//�û���
	private String avatar_url;//ͷ��
	private String gender;//�Ա�
	

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Comment_news() {
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
