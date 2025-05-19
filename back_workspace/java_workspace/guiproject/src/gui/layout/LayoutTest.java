package gui.layout;

/*GUI 프로그래밍을 윈도우 프로그래밍이라고 한다.
모든 유저 인터페이스(UI 컴포넌트)는 윈도우 안에서 구현되므로

컴포넌트: 재사용 가능한 객체 단위(ex: button, checkbox...)
[java 컴포넌트의 유형]
- 남을 포함할 수 있는 유형(컨테이너형)
	ex) Frame
	특징) 남을 포함하려다 보니, 어떻게 배치할 지 고민함 -> 컨테이너 형에는 배치관리자(Layout Manager)가 지원됨
			컨테이너 유형은 개발자가 배치관리자를 지정하지 않아도, 시스템에서 기본으로 적용된느 배치관리자가 있음
	배치관리자 종류) 
		1) BorderLayout(): 동, 서, 남, 북, 센터의 방향을 갖는 배치, 각 방향에 들어가는 컴포넌트는 자신의 크기를 잃어버리고, 확장
		2) FlowLayout(): 위치가 결정되지 않고 컨테이너에 따라 흘러다님, 방향성이 있어서 수평/수직방향의 흐름 둘 중 하나임
		3) GridLayout(): 행과 열의 배치방식, 각 행, 열에 들어가는 컴포넌트가 자신의 크기를 잃어버리고 확장.
		4) CardLayout(): 오직 하나의 카드만 보여지는 방식, 화면 전환 시 사용됨.
		
- 남에게 포함 당할 수 있는 유형(비주얼 컴포넌트형)
	ex) button, checkbox, choice..등 윈도우 안에 포함되는 모든 것들
*/
import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;

public class LayoutTest {
	public static void main(String[] args) {
		//윈도우 생성
		Frame frame = new Frame("배치 학습");
		
		//윈도우 안에 소속되는 컨테이너형 컴포넌트 -> 다른 컴포넌트를 포함할 수 있다.
		//Panel도 컨테이너형이므로 배치관리자가 지원되며, 개발자가 지정하지 않으면 디폴트가 FlowLayout이다.
		Panel panel = new Panel(); // 눈에 보이지 않음(div와 흡사)
		Panel panel_center = new Panel();
		
		//버튼 하나를 생성하여 부착해보자.(방향을 지정하지 않으면 디폴트는 센터)
		Button bt_center1 = new Button("Center1");
		Button bt_center2 = new Button("Center2");
		Button bt_west = new Button("West");
		Button bt_south = new Button("South");
		
		panel.add(bt_south);	//패널에 버튼 부착
		panel.setBackground(Color.BLUE);
		
		panel_center.add(bt_center1);
		panel_center.add(bt_center2);
		panel_center.setBackground(Color.YELLOW);
		
		frame.add(panel_center, BorderLayout.CENTER);
		//상수는 public static final 로 선언되었고, 클래스 소속이므로 인스턴스 생성없이 사용가능함.
		frame.add(bt_west, BorderLayout.WEST);
		frame.add(panel, BorderLayout.SOUTH);
		
		frame.setSize(500, 400);
		//윈도우는 보이고, 안 보이게 하는 기능이 있기 때문에 디폴트는 안보이는 것임
		frame.setVisible(true);
	}
}
