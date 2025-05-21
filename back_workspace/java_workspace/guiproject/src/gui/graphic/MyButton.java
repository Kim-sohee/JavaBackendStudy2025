/*버튼 뿐만 아니라, 눈에 보여지는 모든 컴포넌트는 실행 시 스스로 그린다.
따라서 개발자가 원하는 그림으로 커스텀하려면, 그 그림을 뺏어서 그리면 된다.
-> 버튼을 상속받아 오버라이딩 하자
자바에서 대상 클래스가 final 선언되어 있지 않으면 언제나 상속이 가능함.*/

package gui.graphic;

import javax.swing.JButton;
import java.awt.Graphics;

class MyButton extends JButton{
	// 생성자는 자식에게 물려주지 않으므로, 버튼의 제목을 출력하는 JButton 고유의 생성자를 호출하지 않으면
	// 제목이 나올 수 없다. 따라서 super()호출하지 말고, 매개변수 있는 생성자를 호출해야 한다.
	public MyButton(String btText){
		//int x=3;	부모의 초기화보다 앞서는 코드는 존재할 수 없다.
		super(btText);
	}
	
	//버튼 뿐아니라 컴포넌트들이 보유하고 있는 paint()메서드를 오버라이딩 하기
	//Graphics = 그림 그리는 도구 (도형, 이미지, 글씨, 선, 점, ...)
	//결론: 컴포넌트 중 본래의 그래픽을 사용해야 할 경우, 개발자가 그림을 뺏어야 할 상황이 있음
	public void paint(Graphics g){
		System.out.println("버튼의 그림을 뺏었어요");
		g.drawOval(0, 0, 25, 25);
	}
}
