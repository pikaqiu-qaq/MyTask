package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.mybatis.MyBatiser;

public class IdFactory {
		
		//生成userId
		public static String creatUserId() {
			//获取当前时间
			SimpleDateFormat tempDate = new SimpleDateFormat("HHmmss");
			String datetime = tempDate.format(new Date(System.currentTimeMillis()));		
			
			//或去数据库操作对象并获取用户总数
			MyBatiser myBatiser = new MyBatiser();
			long total = myBatiser.totalUser(); //获取总用户数
			myBatiser.closeSqlSession();
			
			//处理生成user_id
			String second = datetime.substring(4, 6);
			String hour_min = datetime.substring(0,4);
			String user_id0 = Integer.toString((Integer.parseInt(second)%10+1))+hour_min+reverse(Long.toString(1234+total));
			return trans(user_id0);
		}
		
		//转化
		public static String trans(String s) {
			char sub1 = s.charAt(0);
			char sub2 = s.charAt(s.length()-1);
			char sub3 = s.charAt(s.length()/2);
			char sub4 = s.charAt(s.length()/2+1);
			char[] c = s.toCharArray();
			c[0]=sub4;
			c[s.length()/2+1]=sub3;
			c[s.length()/2]=sub2;
			c[s.length()-1]=sub1;
			return reverse(new String(c));
		}
		
		//反转
		public static String reverse(String str){
			StringBuffer sb = new StringBuffer();
			for (int x = str.length() -1; x >= 0; x--) {
				sb.append(str.charAt(x));
			}
			return sb.toString();
		}
}