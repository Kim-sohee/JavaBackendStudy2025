package gui.font;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DocumentA extends Frame implements ActionListener{
	TextArea area;
	Button bt;
	
	//생성자
	public DocumentA(){
		area = new TextArea("TEST 메시지 입니다. \n아래에 메시지를 남겨주세요.");
		bt = new Button("서식");
		
		//조립
		add(area);
		add(bt, BorderLayout.SOUTH);
		area.setBackground(Color.YELLOW);
		
		bt.addActionListener(this);
		
		setSize(300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		DocumentB b = new DocumentB();
	}
	
	//실행부
	public static void main(String[] args) {
		new DocumentA();
	}
}
