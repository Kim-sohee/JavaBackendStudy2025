/*AWT는 자바의 초창기 GUI 패키지임은 맞지만, os마다 다른 디자인으로 실행된다. ex) mac-맥에 맞게, win-윈도우에 맞게
swing은 os(플랫폼)에 상관없는 중립적인 Look&Feel 디자인을 유지
요즘은 swing처럼 os 어울리지 않는 java에 최적화된 디자인은 지양함 따라서 javaFX가 지원된다.
swing은 기존의 awt를 계승했기 때문에 우리가 사용해왔던 awt컴포넌트 명에 J접두어만 붙는다.
*/

package gui.swing;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyWin extends JFrame implements ActionListener{
	JPanel p_south;
	JButton bt;
	JTextArea area;
	
	public MyWin(){
		p_south = new JPanel();
		bt = new JButton("설정");
		area = new JTextArea();
		
		area.setBackground(Color.YELLOW);
		
		add(area);
		p_south.add(bt);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(this);
		
		//setSize(300, 400);
		setBounds(500, 300, 300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("버튼 누름!");
		//this : 인스턴스가 자기 자신을 가리키는 레퍼런스 변수명이다.
		//개발자가 정한 이름이 아닌 시스템에서 정해놓은 레퍼런스 변수명이다.
		new Config(this);
	}
	
	
	public static void main(String[] args) {
		new MyWin();
	}
}
