package com.sinse.shop.common.config;

//유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스 
public class Config {
	public static final String url = "jdbc:mysql://localhost:3306/shop";
	public static final String user = "shop";
	public static final String pass = "1234";
	
	/*------------------------------------------------------------
	 	페이지 정의
	------------------------------------------------------------*/
	public static final int MAIN_PAGE = 0;
	public static final int JOIN_PAGE = 1;
	public static final int PRODUCT_DETAIL_PAGE = 2;
	public static final int CUSTOMER_PAGE = 3;
	public static final int LOGIN_PAGE = 4;
	public static final int CART_PAGE = 5;
	public static final int WISHLIST_PAGE = 6;
	
	
	/*------------------------------------------------------------
	 	쇼핑몰 앱 메인 설정
	------------------------------------------------------------*/
	public static final int SHOP_MAIN_WIDTH = 1400;
	public static final int SHOP_MAIN_HEIGHT = 900;
	public static final int UTIL_WIDTH = SHOP_MAIN_WIDTH;
	public static final int UTIL_HEIGHT = 40;
	public static final int NAVI_WIDTH = SHOP_MAIN_WIDTH;
	public static final int NAVI_HEIGHT = 50;
	
	/*------------------------------------------------------------
	 	메인 페이지 설정
	------------------------------------------------------------*/
	public static final int MAIN_VISUAL_WIDTH = SHOP_MAIN_WIDTH;
	public static final int MAIN_VISUAL_HEIGHT = 400;
	
	
}
