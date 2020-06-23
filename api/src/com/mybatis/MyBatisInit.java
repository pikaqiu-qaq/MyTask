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
	 * MyBatis的初始化
	 */
	static {
		try {
            // 读取配置文件 mybatis-config.xml
            config = Resources.getResourceAsStream("com/mybatis/mybatis-config.xml");
            // 根据配置文件构建SqlSessionFactory
            factory = new SqlSessionFactoryBuilder().build(config);
        }catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
	/*
	 * 获取Session对象
	 */
	public static SqlSession getSession() { 
		return factory.openSession();
	}
	
	/*
	 * 用于MyBatis主动初始化函数
	 */
	public static void init() {
		if(config==null) {
			// 读取配置文件 mybatis-config.xml
            try {
				config = Resources.getResourceAsStream("com/mybatis/mybatis-config.xml");
			} catch (IOException e) {
				e.printStackTrace();
			}
            // 根据配置文件构建SqlSessionFactory
            factory = new SqlSessionFactoryBuilder().build(config);
		}
		else if(factory==null) {
			factory = new SqlSessionFactoryBuilder().build(config);
		}
	}

}
