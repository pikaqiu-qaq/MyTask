package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.mybatis.MyBatiser;

public class IdFactory {
		
		//����userId
		public static String creatUserId() {
			//��ȡ��ǰʱ��
			SimpleDateFormat tempDate = new SimpleDateFormat("HHmmss");
			String datetime = tempDate.format(new Date(System.currentTimeMillis()));		
			
			//��ȥ���ݿ�������󲢻�ȡ�û�����
			MyBatiser myBatiser = new MyBatiser();
			long total = myBatiser.totalUser(); //��ȡ���û���
			myBatiser.closeSqlSession();
			
			//��������user_id
			String second = datetime.substring(4, 6);
			String hour_min = datetime.substring(0,4);
			String user_id0 = Integer.toString((Integer.parseInt(second)%10+1))+hour_min+reverse(Long.toString(1234+total));
			return trans(user_id0);
		}
		
		//ת��
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
		
		//��ת
		public static String reverse(String str){
			StringBuffer sb = new StringBuffer();
			for (int x = str.length() -1; x >= 0; x--) {
				sb.append(str.charAt(x));
			}
			return sb.toString();
		}
}