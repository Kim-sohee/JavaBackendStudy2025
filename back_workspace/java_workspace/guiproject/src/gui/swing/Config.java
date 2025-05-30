package gui.swing;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Config extends JFrame implements ActionListener{
	JTextField t_size;
	JButton bt;
	
	//has a 관계는 멤버 변수로 보유한 관계를 의미
	MyWin myWin;
	
	public Config(MyWin myWin){
		t_size = new JTextField(20);
		bt = new JButton("설정 적용");
		
		//외부에서 MyWin을 전달받는다, 이 생성자 함수를 호출하는 자는 주소값에 의한 생성자 호출을 시도
		//(Call by Reference)
		this.myWin = myWin;
		
		setLayout(new java.awt.FlowLayout());		//컴포넌트들이 자신 본연의 크기를 유지하려면 플로우로..
		
		add(t_size);
		add(bt);
		
		bt.addActionListener(this);
		
		setBounds(500+400, 300, 250, 150);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//MyWin이 보유한 area의 폰트의 크기를 설정해주자.
		//단, 폰트의 크기값은 나의 TextField로 부터 얻은 값이다.
		
		/*기본 자료형과 객체 자료형간 변환이 가능하도록 지원되는 객체 = 래퍼 클래스
		지원되는 이유: 기본 자료형으로는 할 수 없는 더 많은 일을 하기 위해서 사용됨
		1) 숫자
				정수: byte < short < int < long
						(Byte	  Short   Integer  Long)
				실수: float < double
						(Float  Double)
		2) 문자: char  (Char)
		3) 논리값: boolean  (Boolean)
		*/
		int size = Integer.parseInt(t_size.getText());
		
		myWin.area.setFont(new Font(null, 0, size));
	}
}