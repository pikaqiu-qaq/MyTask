package com.bean;

/*
 * 
 */
public class User {
	//
	private long id;
	
	//
	private String user_id;
	
	//�û�����
    private String email;
    
    //�û�����
    private String password; 
    
    //salt
    private String salt;
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public User() {  	
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
}
