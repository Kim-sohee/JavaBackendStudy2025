package com.sinse.shopadmin.config.view;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.product.model.Color;
import com.sinse.shopadmin.product.model.Size;
import com.sinse.shopadmin.product.repository.ColorDAO;
import com.sinse.shopadmin.product.repository.SizeDAO;

public class ConfigPage extends Page{
	JPanel p_color;
	JLabel la_color;
	JTextField t_color;
	JButton bt_color;
	JList list_color;
	JScrollPane scroll_color;
	
	JPanel p_size;
	JLabel la_size;
	JTextField t_size;
	JButton bt_size;
	JList list_size;
	JScrollPane scroll_size;
	
	ColorDAO colorDAO;
	SizeDAO sizeDAO;
	
	public ConfigPage(AppMain appMain) {
		super(appMain);
		setBackground(java.awt.Color.MAGENTA);
		
		//생성
		p_color = new JPanel();
		la_color = new JLabel("색상 등록");
		t_color = new JTextField();
		bt_color = new JButton("등록");
		list_color = new JList();
		scroll_color = new JScrollPane(list_color);
		
		p_size = new JPanel();
		la_size = new JLabel("사이즈 등록");
		t_size = new JTextField();
		bt_size = new JButton("등록");
		list_size = new JList();
		scroll_size = new JScrollPane(list_size);
		
		colorDAO = new ColorDAO();
		sizeDAO = new SizeDAO();
		
		// 스타일
		Dimension d= new Dimension(150 , 30);
		la_color.setPreferredSize(d);
		t_color.setPreferredSize(d);
		la_size.setPreferredSize(d);
		t_size.setPreferredSize(d);
		
		scroll_color.setPreferredSize(new Dimension(200, 300));
		scroll_size.setPreferredSize(new Dimension(200, 300));
		
		p_color.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH-300, 350));
		p_size.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH-300, 350));
		
		p_color.setBorder(new TitledBorder("색상 정보 등록"));
		p_size.setBorder(new TitledBorder("사이즈 정보 등록"));
		
		//조립
		p_color.add(la_color);
		p_color.add(t_color);
		p_color.add(bt_color);
		p_color.add(scroll_color);
		
		p_size.add(la_size);
		p_size.add(t_size);
		p_size.add(bt_size);
		p_size.add(scroll_size);
		
		add(p_color);
		add(p_size);
		
		//이벤트
		getListColor();
		getListSize();
		
		//색상 등록 버튼에 이벤트 연결
		/*이벤트 리스너를 처리할 로직을 객체 수준까지 정의한다는 것은 너무 거창하므로,
		 * 자바 이벤트 처리나, 함수형 인터페이스 구현 시에는 js의 화살표 함수와 비슷한 개념의 함수형 코드를 도입(Lambda)
		 * 람다는 아무 때나 사용할 수 없고, 함수형 인터페이스에 국한된다.*/
		bt_color.addActionListener(	(e) -> {
			registColor();	//등록
			getListColor();	//목록
			t_color.setText("");	 //입력창 초기화
		});
		
		//사이즈 등록 이벤트
		/*재정의할 메서드의 매개변수가 1개밖에 없을때는 소괄호마저도 생략 가능*/
		bt_size.addActionListener((e) ->{
			registSize();
			getListSize();
			t_size.setText("");
		});
	}
	
	//색상 등록 (DAO에게 일 시키기)
	public void registColor() {
		Color color =new Color();
		color.setColor_name(t_color.getText());
		
		int result = colorDAO.insert(color);	//Color의 인스턴스 넣기
		if(result > 0) {
			JOptionPane.showMessageDialog(this, "등록 성공");
		}else {
			JOptionPane.showMessageDialog(this, "등록 실패");			
		}
	}
	
	public void getListColor() {
		List<Color> colorList = colorDAO.selectAll();
		
		Vector vec = new Vector(colorList);	//ArrayList와 동일하지만, 쓰레드 동기화 지원..(내부적 연산이 들어가서 속도 느림)
		list_color.setListData(vec);
		
	}
	
	public void registSize() {
		Size size = new Size();
		size.setSize_name(t_size.getText());
		
		int result = sizeDAO.insert(size);
		if(result > 0) {
			JOptionPane.showMessageDialog(this, "등록 성공");
		}else {
			JOptionPane.showMessageDialog(this, "등록 실패");			
		}
	}
	
	public void getListSize() {
		List<Size> sizeList = sizeDAO.selectAll();
		
		Vector vec = new Vector(sizeList);
		list_size.setListData(vec);
	}
}
