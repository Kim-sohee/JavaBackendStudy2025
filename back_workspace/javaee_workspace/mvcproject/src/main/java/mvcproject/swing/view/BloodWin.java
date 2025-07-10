package mvcproject.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//mvc
public class BloodWin extends JFrame{
	JComboBox box;
	JButton bt;
	
	public BloodWin() {
		box = new JComboBox<>();
		bt = new JButton("판단요청");
		
		box.setPreferredSize(new Dimension(175, 30));
		
		//데이터 채우기
		box.addItem("A");
		box.addItem("B");
		box.addItem("O");
		box.addItem("AB");
		
		setLayout(new FlowLayout());
		
		add(box);
		add(bt);
		
		bt.addActionListener((e)->{
			//getAdvice();
		});
		
		setSize(200, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new BloodWin();
	}
}
