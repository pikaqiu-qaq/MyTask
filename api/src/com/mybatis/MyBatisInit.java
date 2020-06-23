package com.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisInit {
	private static InputStream config = null;
	private static SqlSessionFactory factory = null;
	
	/*
	 * MyBatis�ĳ�ʼ��
	 */
	static {
		try {
            // ��ȡ�����ļ� mybatis-config.xml
            config = Resources.getResourceAsStream("com/mybatis/mybatis-config.xml");
            // ���������ļ�����SqlSessionFactory
            factory = new SqlSessionFactoryBuilder().build(config);
        }catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
	/*
	 * ��ȡSession����
	 */
	public static SqlSession getSession() { 
		return factory.openSession();
	}
	
	/*
	 * ����MyBatis������ʼ������
	 */
	public static void init() {
		if(config==null) {
			// ��ȡ�����ļ� mybatis-config.xml
            try {
				config = Resources.getResourceAsStream("com/mybatis/mybatis-config.xml");
			} catch (IOException e) {
				e.printStackTrace();
			}
            // ���������ļ�����SqlSessionFactory
            factory = new SqlSessionFactoryBuilder().build(config);
		}
		else if(factory==null) {
			factory = new SqlSessionFactoryBuilder().build(config);
		}
	}

}
