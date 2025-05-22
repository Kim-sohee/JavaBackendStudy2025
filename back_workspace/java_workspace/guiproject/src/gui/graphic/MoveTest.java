package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class MoveTest extends JFrame implements ActionListener{
	JPanel p_north;
	MovePanel p_center;
	JButton bt;
	
	//생성자
	public MoveTest(){
		p_north = new JPanel();
		p_center = new MovePanel();
		bt = new JButton("MOVE");
		
		//style
		p_center.setBackground(Color.ORANGE);
		p_north.setLayout(new FlowLayout());
		p_north.setPreferredSize(new Dimension(600, 50));
		
		bt.addActionListener(this);	//버튼과 리스너 연결
		
		//조립
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setSize(600, 650);
		setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		//패널의 빨간 원을 이동시키자
		// x, y를 증가 시키자
		/*int x = p_center.getX();
		x++;
		p_center.setX(x);
		
		int y = p_center.getY();
		y++;
		p_center.setY(y);*/
		
		p_center.move();
		//다시 그려줘
		p_center.repaint();		//주의: 절대 paint를 호출해서는 안됨
	}
	
	//실행
	public static void main(String[] args) {
		new MoveTest();
	}
}
