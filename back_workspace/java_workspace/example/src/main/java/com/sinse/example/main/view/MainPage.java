package com.sinse.example.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import  com.sinse.example.main.model.DataModel;
import com.sinse.example.main.model.UserModel;
import com.sinse.example.main.repository.UserDAO;

public class MainPage extends JFrame{
	JPanel p_north;
	JButton bt_excel;		//엑셀 선택 후 insert
	JButton bt_download;		//표를 엑셀로 다운로드
	JTable table;
	JScrollPane scroll;
	JFileChooser chooser;
	DefaultTableModel model;
	UserDAO userDAO;
	
	public MainPage() {
		//생성
		p_north = new JPanel();
		bt_excel = new JButton("일괄 등록하기");
		bt_download = new JButton("엑셀 다운로드");		
		table = new JTable();
		scroll = new JScrollPane();
		model = new DefaultTableModel(new String[] {}, 0);
		userDAO = new UserDAO();
		
		//스타일
		p_north.setBackground(Color.LIGHT_GRAY);
		p_north.setPreferredSize(new Dimension(600, 40));
		
		//조립
		p_north.add(bt_excel);
		p_north.add(bt_download);
		
		add(p_north, BorderLayout.NORTH);
		add(table);
		
		List<UserModel> users = userDAO.selectAll();
		DataModel.fillTable(model, users);
		
		for(int i=0; i<users.size(); i++) {
			System.out.println(users.get(i).getEname());
		}
		
		//엑셀 선택하여 읽기
		bt_excel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = chooser.showOpenDialog(MainPage.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
//					loadExcel(file);
				}
			}
		});
		
		setSize(600, 500);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new MainPage();
	}
}
