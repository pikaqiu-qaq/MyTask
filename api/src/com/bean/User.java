package com.bean;

import java.util.Date;

public class User {
	private long id;// ��Ӧ���ݿ�����
	private String user_id;// �û���id��������QQ�ţ�
	private String email;// �û�����
	private String password;// �û�����
	private String user_name;// �û��ǳ�
	private String salt;// salt������������ܣ�
	private String avatar_url;// �û�ͷ���url
	private int gender;//�Ա�0δ֪��1�У�2Ů
    private Date register_time;//ע��ʱ��
    private int ban;//�˺��Ƿ񱻶���
    
    public User() {
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	public int getBan() {
		return ban;
	}

	public void setBan(int ban) {
		this.ban = ban;
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
	
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d.toString());
	}

}
