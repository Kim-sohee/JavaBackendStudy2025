package com.sinse.shopadmin.common.config;

public class Config {
	
	public static final String url = "jdbc:mysql://localhost:3306/shop";
	public static final String user = "shop";
	public static final String pass = "1234";
	
	public static final String PRODUCT_IMG_PATH = "C:\\public";
	
	/*------------------------------------------------------------
	 	페이지 정의
	------------------------------------------------------------*/
	public static final int LOGIN_PAGE = 0;
	public static final int MAIN_PAGE = 1;
	public static final int PRODUCT_PAGE = 2;		//상품 등록 페이지
	public static final int ORDER_PAGE = 3;
	public static final int MEMBER_PAGE = 4;
	public static final int CUSTOMER_PAGE = 5;
	public static final int CONFIG_PAGE = 6;
	public static final int JOIN_PAGE = 7;
	public static final int PRODUCT_LIST_PAGE = 8;	//상품목록 페이지
	
	/*------------------------------------------------------------
	 	관리자 앱 메인 설정
	------------------------------------------------------------*/
	public static final int ADMIN_MAIN_WIDTH = 1300;
	public static final int ADMIN_MAIN_HEIGHT = 900;
	public static final int UTIL_WIDTH = ADMIN_MAIN_WIDTH;
	public static final int UTIL_HEIGHT = 50;
	public static final int SIDE_WIDTH = 200;
	public static final int SIDE_HEIGHT = ADMIN_MAIN_HEIGHT - UTIL_HEIGHT;
}
