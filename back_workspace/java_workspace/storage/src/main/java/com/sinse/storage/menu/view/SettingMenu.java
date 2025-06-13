package com.sinse.storage.menu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sinse.storage.config.util.ImageUtil;

public class SettingMenu extends JPanel{
	JButton bt_user;
	JButton bt_sns;
	JButton bt_search;
	JButton bt_help;
	JButton bt_setting;
	JPanel p_top;
	JPanel p_center;
	JPanel p_bottom;
	ImageUtil icon;
	
	private static final int ICON_WIDTH= 80;
	private static final int ICON_HIGHT= 50;
	
	public SettingMenu() {
		//생성
		icon = new ImageUtil();
		bt_user = new JButton(icon.getIcon("images/icon/img0.png", ICON_WIDTH, ICON_HIGHT));
		bt_sns = new JButton(icon.getIcon("images/icon/img1.png", ICON_WIDTH, ICON_HIGHT));
		bt_search = new JButton(icon.getIcon("images/icon/img2.png", ICON_WIDTH, ICON_HIGHT));
		bt_help = new JButton(icon.getIcon("images/icon/img3.png", ICON_WIDTH, ICON_HIGHT));
		bt_setting = new JButton(icon.getIcon("images/icon/img4.png", ICON_WIDTH, ICON_HIGHT));
		p_top = new JPanel();
		p_center = new JPanel();
		p_bottom = new JPanel();
		
		//스타일
		//배경 지정
		p_top.setBackground(new Color(38, 124, 181));
		p_center.setBackground(new Color(38, 124, 181));
		p_bottom.setBackground(new Color(38, 124, 181));
		
		//버튼 크기 지정
		Dimension d = new Dimension(ICON_WIDTH, ICON_HIGHT);
		bt_user.setPreferredSize(d);
		bt_sns.setPreferredSize(d);
		bt_search.setPreferredSize(d);
		bt_help.setPreferredSize(d);
		bt_setting.setPreferredSize(d);
		
		//버튼의 배경 및 테두리 제거
		bt_user.setContentAreaFilled(false);  // 배경 제거
		bt_user.setBorderPainted(false);      // 테두리 제거
		bt_user.setFocusPainted(false);       // 선택 시 테두리 제거
		bt_sns.setContentAreaFilled(false);
		bt_sns.setBorderPainted(false);
		bt_sns.setFocusPainted(false);
		bt_search.setContentAreaFilled(false);
		bt_search.setBorderPainted(false);
		bt_search.setFocusPainted(false);
		bt_help.setContentAreaFilled(false);
		bt_help.setBorderPainted(false);
		bt_help.setFocusPainted(false);
		bt_setting.setContentAreaFilled(false);
		bt_setting.setBorderPainted(false);
		bt_setting.setFocusPainted(false);
		
		p_top.setLayout(new BoxLayout(p_top, BoxLayout.Y_AXIS));
        p_bottom.setLayout(new BoxLayout(p_bottom, BoxLayout.Y_AXIS));
        p_center.setLayout(new BorderLayout());
		
		
		//조립
        p_top.add(Box.createVerticalStrut(40));
		p_top.add(bt_user);
		p_top.add(Box.createVerticalStrut(30));
		p_top.add(bt_sns);
		p_top.add(Box.createVerticalStrut(30));
		p_top.add(bt_search);
		
		p_bottom.add(bt_help);
		p_bottom.add(Box.createVerticalStrut(30));
		p_bottom.add(bt_setting);
		p_bottom.add(Box.createVerticalStrut(40));
		
		p_center.add(p_top, BorderLayout.NORTH);
		p_center.add(p_bottom, BorderLayout.SOUTH);
		setLayout(new BorderLayout());
		add(p_center);
		
		setPreferredSize(new Dimension(100, 960));
	}
}