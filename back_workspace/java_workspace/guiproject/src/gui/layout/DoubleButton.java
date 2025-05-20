package gui.layout;

import java.awt.Frame;
import java.awt.FlowLayout;
import java.awt.Button;
import gui.event.day0520.MyActionListener;

public class DoubleButton {
	public static void main(String[] args) {
		Frame frame = null;
		Button bt1 = null;
		Button bt2 = null;
			
		frame = new Frame();
		bt1 = new Button("A 버튼");
		bt2 = new Button("B 버튼");
		
		frame.setLayout(new FlowLayout());	//플로우 배치 적용
		
		frame.add(bt1);
		frame.add(bt2);
		
		MyActionListener my = new MyActionListener(bt1, bt2);
		
		bt1.addActionListener(my);//버튼1과 리스너 연결
		bt2.addActionListener(my);//버튼 2와 리스너 연결
		
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}
