package com.sinse.shopadmin.product.view;

public class Test {
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println(time);
		
//		for(int i=0; i<5; i++) {
//			System.out.println(System.currentTimeMillis());
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
//		String path = "c://user/test/mario.test.jpg";
//		//1) 가장 마지막점의 위치를 알아낸다.
//		int lastIdx = path.lastIndexOf(".");
//		//2) 가장 마지막점의 위치 바로 다음 위치부터 가장 마지막 문자열까지 추출
//		String ext =path.substring(lastIdx+1, path.length());
//		System.out.println(ext);
		
		String a = "a100";
		String b = "100";
		
		try {
			Integer.parseInt(a);
		} catch (NumberFormatException e) {
			System.out.println("숫자가 아닙니다.");
			e.printStackTrace();
		}
	}
}
