package com.sinse.storage.menu.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinse.storage.config.util.ImageUtil;

public class SettingMenu extends JFrame{
	JButton bt_user;
	JButton bt_sns;
	JButton bt_search;
	JButton bt_help;
	JButton bt_setting;
	JPanel p_center;
	ImageUtil icon;
	
	private static final int ICON_WIDTH= 90;
	private static final int ICON_HIGHT= 50;
	
	public SettingMenu() {
		//생성
		icon = new ImageUtil();
		bt_user = new JButton(icon.getIcon("images/icon/img0.jpg", ICON_WIDTH, ICON_HIGHT));
		bt_sns = new JButton(icon.getIcon("images/icon/img1.jpg", ICON_WIDTH, ICON_HIGHT));
		bt_search = new JButton(icon.getIcon("images/icon/img2.jpg", ICON_WIDTH, ICON_HIGHT));
		bt_help = new JButton(icon.getIcon("images/icon/img3.jpg", ICON_WIDTH, ICON_HIGHT));
		bt_setting = new JButton(icon.getIcon("images/icon/img4.jpg", ICON_WIDTH, ICON_HIGHT));
		p_center = new JPanel();
		
		//스타일
		Dimension d = new Dimension(90, 50);
		bt_user.setPreferredSize(d);
		bt_sns.setPreferredSize(d);
		bt_search.setPreferredSize(d);
		bt_help.setPreferredSize(d);
		bt_setting.setPreferredSize(d);
		
		//조립
		p_center.setLayout(new FlowLayout());
		p_center.add(bt_user);
		p_center.add(bt_sns);
		p_center.add(bt_search);
		p_center.add(bt_help);
		p_center.add(bt_setting);
		add(p_center);
		
		setSize(100, 960);
		setVisible(true);
	}
	
	//이후에 삭제
	public static void main(String[] args) {
		new SettingMenu();
	}
}
