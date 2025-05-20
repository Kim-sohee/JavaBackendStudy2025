package gui.chat;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


class ChatB extends Frame implements KeyListener{
	TextArea area;
	Panel p_south;
	TextField t_input;
	ChatA chatA;
	
	//생성자
	public ChatB(ChatA a){
		area = new TextArea();
		p_south = new Panel();
		t_input = new TextField();
		//this? => 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		this.chatA = a;
		
		//스타일
		area.setBackground(Color.ORANGE);
		
		Dimension d = new Dimension(270, 25);
		t_input.setPreferredSize(d);
		
		//조립
		add(area);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);
		
		t_input.addKeyListener(this);
		
		setSize(300, 400);
		setVisible(true);
	}
	//KeyListener의 메서드를 재정의
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ENTER){	//Enter키 입력
			chatA.area.append(t_input.getText()+"\n");
			t_input.setText("");
		}
	}
}
