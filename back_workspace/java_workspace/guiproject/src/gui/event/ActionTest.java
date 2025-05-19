/*Java GUI에서도 사용자 반응에 대한 이벤트 처리가 가능하다.
js에서의 처리보다 훨씬 복잡하다.
html에서 클릭 이벤트는 click -> java에서는 클릭이벤트라는 용어가 없다. action에 소속
*/
package gui.event;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Choice;

public class ActionTest {
	public static void main(String[] args) {
		Frame frame = null;
		Button bt = null;
		TextField t = null;
		Choice ch = null;
		
		frame = new Frame();
		bt = new Button("Click me!");
		t = new TextField(20);
		ch = new Choice();
		
		ch.addItem("choose your mail server");
		ch.addItem("@naver.com");
		ch.addItem("@google.com");
		ch.addItem("@daum.net");
		
		//js처럼 bt.addEventListener() 메서드를 버튼에 연결하는 과정을 동일하게 진행
		bt.addActionListener(new MyActionListener());	//이벤트를 구현한 객체의 인스턴스
		t.addKeyListener(new MyKeyListener());	//텍스트필드와 리스너와의 연결
		ch.addItemListener(new MyItemListener());
		frame.addMouseListener(new MyMouseListener());
		
		frame.setLayout(new FlowLayout());
		frame.add(bt);
		frame.add(t);
		frame.add(ch);
		
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}
