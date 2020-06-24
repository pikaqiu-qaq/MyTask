package com.bean;
 
public class User {
	private long id;//对应数据库主键
	private String user_id;//用户的id（类似于QQ号）
    private String email;//用户邮箱
    private String password;//用户密码
    private String user_name;//用户昵称
    private String salt;//salt（用于密码加密）
    private String avatar_url;//用户头像的url

	public User() {  	
    }
	
	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}  
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
