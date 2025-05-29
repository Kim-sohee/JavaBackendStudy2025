package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.pages.Cs;
import shop.pages.Home;
import shop.pages.Mypage;
import shop.pages.Page;
import shop.pages.Product;
import util.ImageUtil;

/*화면을 4개 보유한 쇼핑몰 애플리케이션의 화면 전환 처리 방법*/
public class ShopMain extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel p_center;	//프로그램 가동과 동시에 이 패널에는 앞으로 사용하게 될 페이지들을 미리 부착하게 될 예정
	ImageUtil imageUtil;
	
	/*
	 * URI : 자원을 표현하는 방법(표준이 정해짐)
	 * URL : URI중에서 유달리, 해당 자원의 위치를 표현한다면
	 * */
	
	//쇼핑몰을 구성하는 모든 페이지를 보유한다.
	Page[] pageArray = new Page[4];
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int MYPAGE = 2;
	public static final int CS = 3;
	
	
	public ShopMain() {
		imageUtil = new ImageUtil();
		
		//생성
		p_north = new JPanel();
		bt_home = new JButton(imageUtil.getIcon("home.png", 35, 30));
		bt_product = new JButton(imageUtil.getIcon("product.png", 35, 30));
		bt_mypage = new JButton(imageUtil.getIcon("user.png", 35, 30));
		bt_cs = new JButton(imageUtil.getIcon("setting.png", 35, 30));
		
		//개발자가 버튼에 추가적인 값을 심을 수 있다.
		bt_home.putClientProperty("id", HOME);
		bt_product.putClientProperty("id", PRODUCT);
		bt_mypage.putClientProperty("id", MYPAGE);
		bt_cs.putClientProperty("id", CS);
		
		
		
		p_center = new JPanel();
		
		pageArray[HOME] = new Home();
		pageArray[PRODUCT] = new Product();
		pageArray[MYPAGE] = new Mypage();
		pageArray[CS] = new Cs();
		
		//스타일
		p_north.setPreferredSize(new Dimension(800, 50));
		p_north.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(35 ,30);
		bt_home.setPreferredSize(d);
		bt_product.setPreferredSize(d);
		bt_mypage.setPreferredSize(d);
		bt_cs.setPreferredSize(d);
		
		//조립
		p_north.add(bt_home);
		p_north.add(bt_product);
		p_north.add(bt_mypage);
		p_north.add(bt_cs);
		
		for(int a=0; a<pageArray.length; a++) {
			p_center.add(pageArray[a]);	//페이지를 센터 패널에 부착
		}
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt_home.addActionListener(this);
		bt_product.addActionListener(this);
		bt_mypage.addActionListener(this);
		bt_cs.addActionListener(this);
		
		setSize(800, 650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	//margin auto와 비슷하게 화면 가운데로 띄워짐
	}
	
	//원하는 페이지만 보여지게 처리하는 메서드
	public void showHide(int target) {
		for(int i=0; i<pageArray.length; i++) {
			pageArray[i].setVisible((i==target)? true : false);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton obj = (JButton)e.getSource();
		
		int id = (int)obj.getClientProperty("id");	//눌린 버튼마다 자신의 id값인 0, 1, 2, 3
		
		showHide(id);
		
	}

	public static void main(String[] args) {
		new ShopMain();
	}
}
