package com.sinse.shopadmin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	public static String getSecurePass(String pwd) {
		//JavaSE는 이미 암호화 알고리즘 함수를 보유하고 있다.
		String pass = pwd;
		StringBuffer sb=new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(pass.getBytes("UTF8"));
			
			//잘게 쪼개진 바이트를 16진수 문자열로 변환
			for(int i=0; i<hash.length; i++) {
				//byte 데이터를 16진수로 변경하는 과정에서, byte 값 안에 음수가 존재할 경우 
				//byte 데이터 형이 int 형으로 변경되면서, 부호비트가 자동으로 붙게 되는데,
				//이 부호비트는 암호화에 사용되지 않으므로, 제거해야 한다.
				//이때 제거하는 연산은 16진수 0xff와 &비트연산자 중 and 연산자를 이용한다.
				//참고) byte 데이터가 int 형으로 변경되는 이유는 java언어에서 
				String hex = Integer.toHexString(0xff & hash[i]);
				
				if(hex.length()<2) sb.append("0");
				sb.append(hex);	//스트림 누적
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
