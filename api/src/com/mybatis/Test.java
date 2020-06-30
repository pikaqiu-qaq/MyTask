package com.mybatis;

import com.bean.User;

public class Test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		

		MyBatiser myBatiser = new MyBatiser();
		/*User user = myBatiser.selectUserByUser_id("123715024");
		System.out.println(user.getEmail());
		user.setBan(0);
		if(myBatiser.updateUser(user) == MyBatiser.SUCCESS) {
			System.out.println("success");
		}
		else {
			System.out.println("error");
		}*/
		//User user = myBatiser.selectUserByUser_id("123715024");
		//if(user==null)return false;
		//user.setBan(1);
		//myBatiser.updateUser(user);
		myBatiser.unbanUserByUser_id("123715024");
		myBatiser.closeSqlSession();
	}

}
