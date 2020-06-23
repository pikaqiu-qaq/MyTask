package com.bean;
 
public interface UserMapper {
	User selectUserByUser_id(String user_id);
	User selectUserByEmail(String email);
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(User user);
	long totalUser();

}
