package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Base64Util {
	
	/*
	 * 将base64字符串转化为图片文件存入磁盘
	 * 若有同名文件，则覆盖
	 */
	public static boolean changeBase64ToImage(String base64String, String file_name){
	    //base64格式字符串为空，返回false
	    if(base64String == null){
	        return false;
	    }
	    Decoder decoder = Base64.getDecoder();
	    try {
	        //解码过程，即将base64字符串转换成二进制流
	        byte[] imageByte=decoder.decode(base64String);

	        //生成图片路径和文件名
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
