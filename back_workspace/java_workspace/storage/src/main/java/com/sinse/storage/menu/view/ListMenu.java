package com.sinse.storage.menu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListMenu extends JPanel{
	JPanel p_center;
	JLabel la_title;
	//입고
	JLabel la_inbound;
	JLabel la_inCurrent;
	JLabel la_inRequest;
	JLabel la_inInspect;
	//출고
	JLabel la_outbound;
	JLabel la_outCurrent;
	JLabel la_outRequest;
	JLabel la_outInspect;
	//재고
	JLabel la_inventory;
	//통계 및 보고서
	JLabel la_statsReport;
	JLabel la_stats;
	JLabel la_report;
	//사용자 관리
	JLabel la_management;
	
	public ListMenu() {
		//생성
		p_center = new JPanel();
		la_title = new JLabel("관리자 페이지");
		la_inbound = new JLabel("입고");
		la_inCurrent = new JLabel("입고 현황");
		la_inRequest = new JLabel("입고 요청");
		la_inInspect = new JLabel("입고 검수");
		la_outbound = new JLabel("출고");
		la_outCurrent = new JLabel("출고 현황");
		la_outRequest = new JLabel("출고 요청");
		la_outInspect = new JLabel("출고 검수");
		la_inventory = new JLabel("재고 현황");
		la_statsReport = new JLabel("통계 및 보고서");
		la_stats = new JLabel("입/출고 현황 통계");
		la_report = new JLabel("입/출고 현황 보고서");
		la_management = new JLabel("사용자 관리");
		
		//스타일
		p_center.setBorder(BorderFactory.createLineBorder(Color.black));
		la_title.setPreferredSize(new Dimension(340, 200));
		la_title.setFont(new Font("SansSerif", Font.BOLD, 45));
		la_title.setForeground(new Color(38,  124,  181));
		
		//상위 항목 스타일 지정
		Dimension top = new Dimension(330, 100);
		la_inbound.setPreferredSize(top);
		la_outbound.setPreferredSize(top);
		la_inventory.setPreferredSize(top);
		la_statsReport.setPreferredSize(top);
		la_management.setPreferredSize(top);
		
		Font topFont = new Font("SansSerif", Font.BOLD, 30);
		la_inbound.setFont(topFont);
		la_outbound.setFont(topFont);
		la_inventory.setFont(topFont);
		la_statsReport.setFont(topFont);
		la_management.setFont(topFont);
		
		//하위 항목 스타일 지정
		Dimension sub = new Dimension(300, 30);
		la_inCurrent.setPreferredSize(sub);
		la_inRequest.setPreferredSize(sub);
		la_inInspect.setPreferredSize(sub);
		la_outCurrent.setPreferredSize(sub);
		la_outRequest.setPreferredSize(sub);
		la_outInspect.setPreferredSize(sub);
		la_stats.setPreferredSize(sub);
		la_report.setPreferredSize(sub);
		Font subFont = new Font("SansSerif", Font.PLAIN, 20);
		
		setPreferredSize(new Dimension(350, 960));
		setLayout(new BorderLayout());
		
		//부착
		p_center.setLayout(new FlowLayout());
		p_center.add(la_title);
		p_center.add(la_inbound);
		p_center.add(la_inCurrent);
		p_center.add(la_inRequest);
		p_center.add(la_inInspect);
		p_center.add(la_outbound);
		p_center.add(la_outCurrent);
		p_center.add(la_outRequest);
		p_center.add(la_outInspect);
		p_center.add(la_inventory);
		p_center.add(la_statsReport);
		p_center.add(la_stats);
		p_center.add(la_report);
		p_center.add(la_management);
		add(p_center);
		
	}
}