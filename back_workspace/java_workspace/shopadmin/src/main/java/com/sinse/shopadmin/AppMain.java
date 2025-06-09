package com.sinse.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.config.view.ConfigPage;
import com.sinse.shopadmin.cs.view.CustomerPage;
import com.sinse.shopadmin.main.view.MainPage;
import com.sinse.shopadmin.memeber.view.MemberJoin;
import com.sinse.shopadmin.memeber.view.MemberPage;
import com.sinse.shopadmin.order.view.OrderPage;
import com.sinse.shopadmin.product.view.ProductPage;
import com.sinse.shopadmin.security.LoginForm;
import com.sinse.shopadmin.security.model.Admin;

public class AppMain extends JFrame{
	JPanel p_north;
	JPanel p_west;			//사이드 메뉴 영역
	JPanel p_container;	//페이지가 교체될 영역
	JLabel la_user;			//현재 로그인 한 유저
	
	JLabel la_product;
	JLabel la_order;
	JLabel la_member;
	JLabel la_cs;
	JLabel la_config;
	public Connection con;
	public Admin admin = new Admin();	//추후 제거될 예정
	
	//모든 페이지를 담게될 배열
	Page[] pages;
 	
	public AppMain() {
		//생성
		p_north = new JPanel();
		p_west = new JPanel();
		p_container = new JPanel();
		la_user = new JLabel("Admin");
		
		la_product = new JLabel("상품 관리");
		la_order = new JLabel("주문 관리");
		la_member = new JLabel("회원 관리");
		la_cs = new JLabel("CS 관리");
		la_config = new JLabel("설정");
		
		//스타일
		p_north.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		p_north.setBackground(Color.GRAY);
		
		p_west.setPreferredSize(new Dimension(Config.SIDE_WIDTH, Config.SIDE_HEIGHT));
		p_west.setBackground(Color.RED);
		
		p_container.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH-Config.SIDE_WIDTH,
				Config.ADMIN_MAIN_HEIGHT- Config.UTIL_HEIGHT));
		p_container.setBackground(Color.PINK);
		
		//크기 조정
		Dimension d = new Dimension(185, 100);
		la_product.setPreferredSize(d);
		la_order.setPreferredSize(d);
		la_member.setPreferredSize(d);
		la_cs.setPreferredSize(d);
		la_config.setPreferredSize(d);
		
		//폰트 조정
		Font f = new Font(null, Font.BOLD, 30);
		la_product.setFont(f);
		la_order.setFont(f);
		la_member.setFont(f);
		la_cs.setFont(f);
		la_config.setFont(f);
		
		//글자 색 조정
		la_product.setForeground(Color.WHITE);
		la_order.setForeground(Color.WHITE);
		la_member.setForeground(Color.WHITE);
		la_cs.setForeground(Color.WHITE);
		la_config.setForeground(Color.WHITE);
		
		//조립
		p_west.add(la_product);
		p_west.add(la_order);
		p_west.add(la_member);
		p_west.add(la_cs);
		p_west.add(la_config);		
		
		add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		add(p_container);
		
		//데이터베이스 접속해놓기
		connect();
		
		//모든 버튼에 이벤트 연결
		//리스너 중 재정의할 메서드가 많은 경우(3개 이상)는 어댑터 지원을 의심해보자
		//어댑터란? 우리 대신 리스너의 메서드를 오버라이딩 해놓은 중간 객체, sun사가 개발자 배려
		la_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.PRODUCT_PAGE);
			}
		});
		la_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.ORDER_PAGE);
			}
		});
		la_member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.MEMBER_PAGE);
			}
		});
		la_cs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CUSTOMER_PAGE);
			}
		});
		la_config.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CONFIG_PAGE);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//데이터베이스 접속 끊기
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				//프로세스 종료
				System.exit(0);
			}
		});
		
		createPage();
		showPage(-1);//최초에는 로그인폼은 기본으로 떠 있게 처리
		
		setBounds(150, 80, Config.ADMIN_MAIN_WIDTH, Config.ADMIN_MAIN_HEIGHT);
		setVisible(true);
	}
	
	//DB 연결
	public void connect() {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Config.url, Config.user, Config.pass);
			if(con != null) {
				this.setTitle("접속 완료");
			}else {
				this.setTitle("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//쇼핑몰에 사용할 모든 페이지 생성 및 부착
	public void createPage() {
		pages = new Page[8];
		
		pages[Config.LOGIN_PAGE] = new LoginForm(this);
		pages[Config.MAIN_PAGE] = new MainPage(this);
		pages[Config.PRODUCT_PAGE] = new ProductPage(this);
		pages[Config.ORDER_PAGE] = new OrderPage(this);
		pages[Config.MEMBER_PAGE] = new MemberPage(this);
		pages[Config.CUSTOMER_PAGE] = new CustomerPage(this);
		pages[Config.CONFIG_PAGE] = new ConfigPage(this);
		pages[Config.JOIN_PAGE] = new MemberJoin(this);
		
		for(int i=0; i<pages.length; i++) {
			p_container.add(pages[i]);
		}
	}
	
	//부착된 페이지들을 대상으로, 어떤 페이지를 보여줄지를 결정하는 메서드
	public void showPage(int target) {
		//로그인 체크
		if(admin==null && target != -1 && target != Config.JOIN_PAGE && target != Config.LOGIN_PAGE) {
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다.");
			pages[Config.LOGIN_PAGE].setVisible(true);
			return;
		}
		
		for(int i=0; i<pages.length; i++) {
			pages[i].setVisible((i==target)? true: false);
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
