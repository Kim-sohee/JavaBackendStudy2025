package com.sinse.storage.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinse.storage.menu.view.ListMenu;
import com.sinse.storage.menu.view.SettingMenu;

public class MainPage extends JFrame{
	JPanel p_west;
	JPanel p_center;
	SettingMenu settingMenu;
	ListMenu listMenu;
	
	public MainPage() {
		//생성
		p_west = new JPanel();
		p_center = new JPanel();
		settingMenu = new SettingMenu();
		listMenu = new ListMenu();
		
		//스타일
		p_west.setLayout(new BorderLayout());
		p_west.setPreferredSize(new Dimension(450, 960));
		
		//조립
		p_west.add(settingMenu, BorderLayout.WEST);
		p_west.add(listMenu);
		add(p_west, BorderLayout.WEST);
		add(p_center);
		
		setSize(1440, 960);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MainPage();
	}
}
