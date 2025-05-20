package gui.chat;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChatA extends Frame implements ActionListener, KeyListener {
							/* is a                    is a*/
	TextArea area;
	Panel p_south;
	TextField t_input;
	Button bt_open;
	ChatB chatB;
	
	//생성자
	public ChatA(){
		area = new TextArea();
		p_south = new Panel();
		t_input = new TextField();
		bt_open = new Button("버튼");
		
		//스타일
		area.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(220, 25);
		t_input.setPreferredSize(d);
		
		//조립
		add(area);
		p_south.add(t_input);
		p_south.add(bt_open);
		add(p_south, BorderLayout.SOUTH);
		
		//버튼과 리스너인자와의 연결
		bt_open.addActionListener(this);
		
		//텍스트 필드와 리스너인자와의 연결
		t_input.addKeyListener(this);
		
		setSize(300, 400);
		setVisible(true);
	}
	
	//ActionListener를 구현하겠다고 선언하였으므로 현재 클래스에서 인터페이스의 메서드를 오버라이딩
	public void actionPerformed(ActionEvent e){
		//System.out.println("나눌렀어?");
		//ChatB의 인스턴스를 생성
		chatB = new ChatB(this);
	}
	
	//KeyListener의 메서드를 재정의
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ENTER){	//Enter키는 10번임 -> 상수로 변경

			//ChatB가 보유한 area의 텍스트값을 원하는 값으로 넣자.
			chatB.area.append(t_input.getText()+"\n");
			t_input.setText("");
		}
	}
	
	public static void main(String[] args) {
		new ChatA();
	}
}
