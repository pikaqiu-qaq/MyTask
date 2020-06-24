package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Base64Util {
	
	/*
	 * ��base64�ַ���ת��ΪͼƬ�ļ��������
	 * ����ͬ���ļ����򸲸�
	 */
	public static boolean changeBase64ToImage(String base64String, String file_name){
	    //base64��ʽ�ַ���Ϊ�գ�����false
	    if(base64String == null){
	        return false;
	    }
	    Decoder decoder = Base64.getDecoder();
	    try {
	        //������̣�����base64�ַ���ת���ɶ�������
	        byte[] imageByte=decoder.decode(base64String);

	        //����ͼƬ·�����ļ���
	        String pathString ="D://avatar/"+file_name;
	        File file = new File(pathString);
	        if(file.exists()) {
	        	file.delete();
	        }
	        OutputStream out =new FileOutputStream(pathString);
	        out.write(imageByte);
	        out.flush();
	        out.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}

}
