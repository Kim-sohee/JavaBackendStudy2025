package com.sinse.storage.graph.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class JTabbedTest extends JFrame{
	JTextArea area;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JTabbedPane tp;
	
	BarChart barChart;
	CircleChart circleChart;
	LineChart lineChart;
	HorizentalBarChart bar3dChart;
	
	
	public JTabbedTest() {
		//생성
		area = new JTextArea(200, 200);
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		tp = new JTabbedPane();
		
		barChart = new BarChart("Bar Chart Demo");
		circleChart = new CircleChart("Circle Chart Demo");
		lineChart = new LineChart();
		bar3dChart = new HorizentalBarChart("Bar Chart Demo");
		
		//디자인
		Dimension d = new Dimension(300, 300);
		barChart.setPreferredSize(d);
		circleChart.setPreferredSize(d);
		lineChart.setPreferredSize(d);
		
		tp.setBounds(50, 50, 200, 200);
		
		//조립
		p1.add(barChart);
		p2.add(circleChart);
		p3.add(lineChart);
		p4.add(bar3dChart);
		
		tp.add("일별", p1);
		tp.add("주별", p2);
		tp.add("월별", p3);
		tp.add("년별", p4);
		
		add(tp);
		
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new JTabbedTest();
	}
}
