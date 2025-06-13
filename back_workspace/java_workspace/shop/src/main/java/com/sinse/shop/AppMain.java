package com.sinse.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.home.MainPage;
import com.sinse.shop.member.view.MemberJoin;

public class AppMain extends JFrame{
	JPanel p_north;			//p_util, p_navi를 공존시켜야 하므로
	JPanel p_util;				//최상단 유틸리티 네비 영역(서브 메뉴)
	JPanel p_nav;			//메인 네비게이션
	JPanel p_container;	//모든 페이지가 전환될 컨테이너 영역
	
	//유틸리티 네비 관련 항목
	JLabel la_login;
	JLabel la_join;
	JLabel la_cart;
	JLabel la_wishlist;
	
	//메인 네비게이션 관련
	JLabel la_home;
	JLabel la_category;
	JLabel la_new;
	JLabel la_best;
	JLabel la_cs;
	
	Page[] pages;
	
	
	public AppMain() {
		//생성
		p_north = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));	//margin=0;
		p_util = new JPanel(new FlowLayout(FlowLayout.RIGHT));		//패널 내에서의 우측정렬
		p_nav = new JPanel();
		p_container = new JPanel();
		
		la_login = new JLabel("Login");
		la_join = new JLabel("Join");
		la_cart = new JLabel("Cart");
		la_wishlist = new JLabel("Wishlist");
		
		la_home = new JLabel("Home");
		la_category = new JLabel("Category");
		la_new = new JLabel("New");
		la_best = new JLabel("Best");
		la_cs = new JLabel("CS");
		
		//style
		p_util.setBackground(Color.YELLOW);
		p_nav.setBackground(Color.ORANGE);
		p_container.setBackground(Color.PINK);
		
		p_north.setPreferredSize(new Dimension(Config.SHOP_MAIN_WIDTH, Config.UTIL_HEIGHT + Config.NAVI_HEIGHT));
		p_util.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		p_nav.setPreferredSize(new Dimension(Config.NAVI_WIDTH, Config.NAVI_HEIGHT));
		p_container.setPreferredSize(new Dimension(Config.SHOP_MAIN_WIDTH, 810));
		
		//조립
		p_util.add(la_login);
		p_util.add(la_join);
		p_util.add(la_cart);
		p_util.add(la_wishlist);
		
		p_nav.add(la_home);
		p_nav.add(la_category);
		p_nav.add(la_new);
		p_nav.add(la_best);
		p_nav.add(la_cs);
		
		p_north.add(p_util);
		p_north.add(p_nav);
		add(p_north, BorderLayout.NORTH);
		add(p_container);
		
		createPage();			//앱이 가동될 때 모든 페이지 생성 및 부착
		showPage(Config.MAIN_PAGE); 	//부착된 페이지들 중, 보고싶은 페이지의 index를 넘기자
		
		la_join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.JOIN_PAGE);
			}
		});
		
		setBounds(150, 80, 1400, 900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 		//DB 연동 후에는 지우기
	}
	
	//쇼핑몰의 모든 페이지를 생성하여 부착
	public void createPage() {
		//페이지 생성
		pages = new Page[1];	//본인이 만든 페이지 수로 추후 대체
		pages = new Page[2];
		
		pages[0] = new MainPage(this);
		pages[1] = new MemberJoin(this);
		
		//모든 페이지를 대상으로 p_container에 부착
		for(int i=0; i<pages.length; i++) {
			p_container.add(pages[i]);
		}
	}
	
	
	//원하는 페이지를 보여주는 메서드 정의
	public void showPage(int target) {
		//반복문의 대상이 되려면 모든 페이지는 같은 자료형의 배열로 존재
		//Page라는 최상위 객체를 만들었음
		for(int i=0; i<pages.length; i++) {
			pages[i].setVisible((i==target)? true: false);
		}
	}
	
	public static void main(String[] args) {
		new  AppMain();
	}
	
}
