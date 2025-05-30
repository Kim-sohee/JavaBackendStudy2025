/*그래픽 프로그래밍 시, 알고 있으면 도움이 되는 비유
[현실]								[프로그래밍]
1) 화가							컴포넌트
2) 붓(그리는 행위)				컴포넌트가 보유한 메서드(paint())
3) 팔레트 등의 기타 도구		paint(Graphics g)
4) 그려질 대상					컴포넌트 자신
*/

/*모든 컴포넌트는 스스로 그린다.
버튼이 스스로 그림을 그릴때 뺏어보기*/
package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;

class TestFrame extends JFrame {
	MyButton bt;
	ImgPanel ip;	//내가 재정의한 패널
	
	//생성자
	public TestFrame(){
		bt = new MyButton("커스텀 버튼");
		ip = new ImgPanel();
		
		add(bt);
		add(ip);
		
		setLayout(new FlowLayout());
		setSize(300, 400);
		setVisible(true);
		
		//윈도우창을 닫을때, 프로세스 종료
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TestFrame();
	}
}
