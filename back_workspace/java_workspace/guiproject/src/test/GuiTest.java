package test;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;

/*
GUI = Graphic User Interface 그림으로 조작하는 프로그램
CLI = Command Line Interface 명령으로 조작하는 프로그램

Java의 그래픽 관련 API는 java.awt 패키지에 들어있음
모든 데스크탑 기반의 GUI 프로그램에서 최상단에 존재하는 그래픽 컴포넌트는 window이다
java에서 윈도우는 상징적 존재에 불과하고 그 하위에 Frame으로 대신한다.
*/
class GuiTest {
	public static void main(String[] args) {
		//html에서 요소들을 부모 컨테이너에 추가하듯, 자바도 GUI 요소들을 윈도우 컨테이너에 부착
		Frame f = new Frame();		//윈도우 생성
		
		//버튼이 크게 나오는 이유는 아직 배치(레이아웃)방법을 지정하지 않았기 때문(default layout 적용)
		f.setLayout(new FlowLayout());
		
		//html에서 봐왔던 UI 컴포넌트 요소들을 자바 버전으로 생성하여 확인
		Button bt = new Button("버튼");
		TextField t = new TextField(20);
		Choice ch1 = new Choice();
		
		ch1.add("@gmail.com");
		ch1. add("@naver.com");
		ch1.add("@daum.net");
		
		TextArea area = new TextArea(12, 20);
		Checkbox[] chArray = new Checkbox[3];	//반드시 크기 지정
		
		chArray[0] = new Checkbox("JAVA", true);
		chArray[1] = new Checkbox("JSP", false);
		chArray[2] = new Checkbox("ORACLE", true);
		
		f.add(bt);	//frame에 버튼 부착
		f.add(t);	//프레임에 텍스트 필드 부착
		f.add(ch1);
		f.add(area);
		
		for(int i=0; i<chArray.length; i++){
			f.add(chArray[i]);
		}
		
		Label la = new Label("제목 등의 일반 텍스트");
		f.add(la);
		
		//메뉴바와 메뉴 만들기
		Menu m_file, m_edit, m_style, m_view, m_help; 
		m_file = new Menu("파일");
		m_edit = new Menu("편집");
		m_style = new Menu("서식");
		m_view = new Menu("보기");
		m_help = new Menu("도움말");
		
		MenuBar bar = new MenuBar();
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		f.setMenuBar(bar);
		
		//자바의 윈도우를 사용하려면 너비, 높이를 지정
		f.setSize(600, 500);
		
		//윈도우의 default 보기 옵션은 비활성화 되어 있음 -> 활성화 필요
		f.setVisible(true);
		
	}
}
