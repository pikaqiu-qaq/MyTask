package com.bean;
 
public class Comment_anime {
	private long id;//璇勮鐨刬d
	private String anime_id;//鎵�灞炶祫璁殑id
	private String user_id;//鐢ㄦ埛璐﹀彿
	private long create_time;//璇勮鍙戣〃鏃堕棿
	private String content;//璇勮鍐呭
	private String user_name;//用户名
	private String avatar_url;//头像
	private String gender;//性别
	
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
	
	public Comment_anime() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnime_id() {
		return anime_id;
	}

	public void setAnime_id(String news_id) {
		this.anime_id = news_id;
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
