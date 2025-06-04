package com.sinse.table.homeproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/*엑셀로 불러들인 데이터를 JTable에 출력하고 테이블에서 데이터를 편집한 후, 그 결과를 DB에 반영하기*/
public class Main extends JFrame{
	JPanel p_north;
	JButton bt_load;
	JButton bt_save;
	JTable table;
	
	//생성자
	public Main() {
		p_north = new JPanel();
		bt_load = new JButton("Load");
		bt_save = new JButton("Save");
		table = new JTable();
		
		//style
		p_north.setBackground(Color.YELLOW);
		p_north.setPreferredSize(new Dimension(600, 40));
		
		//조립
		p_north.add(bt_load);
		p_north.add(bt_save);
		
		add(p_north, BorderLayout.NORTH);
		add(table);
		
		setSize(600, 500);
		setVisible(true);
		
	}
	
	//실행부
	public static void main(String[] args) {
		new Main();
	}
}
