package com.util;

import java.security.MessageDigest;
import java.util.Random;

public class Encryption {

	
	private static String hex(byte[] arr) {
	     StringBuffer sb = new StringBuffer();
	     for (int i = 0; i < arr.length; ++i) {
		     sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
	     }
	     return sb.toString();
    }
   
   private static String md5Hex(String str) {
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] digest = md.digest(str.getBytes());
           return hex(digest);
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println(e.toString());
           return "";
       }
     }

	
	public static String getSalt()
    {
    	Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
              for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }

        String salt = sBuilder.toString();
        return salt;
    }
    	    
    
    public static String encrypt(String password,String salt)
    {
    	    password = md5Hex(password + salt);
	        char[] cs = new char[48];
	        for (int i = 0; i < 48; i += 3) {
	            cs[i] = password.charAt(i / 3 * 2);
	            char c = salt.charAt(i / 3);
	            cs[i + 1] = c;
	            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
	        }
	        return String.valueOf(cs);
    }
}