package gui.layout;
import java.awt.*;

public class LoginForm1 {
	public static void main(String[] args) {
		//이 디자인에 사용될 컴포넌트들을 우선 선언
		Frame frame=null;
		Panel p_center = null;
		Panel p_south = null;
		Label la_id=null;
		Label la_pwd=null;
		TextField t_id=null;
		TextField t_pw=null;
		Button bt_login=null;
		Button bt_join=null;
		
		//생성
		frame = new Frame("Login Form");
		p_center = new Panel();
		p_south = new Panel();
		la_id = new Label("ID");
		la_pwd = new Label("Password");
		t_id = new TextField(20);	//생성자의 매개변수로 초기 글자수 너비
		t_pw = new TextField(20);
		bt_login = new Button("Login");
		bt_join = new Button("Join");
		
		//조립
		p_center.setLayout(new GridLayout(2,2));	//2층 2호수 그리드 적용
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pwd);
		p_center.add(t_pw);
		frame.add(p_center, BorderLayout.CENTER);
		
		p_south.add(bt_login);
		p_south.add(bt_join);
		frame.add(p_south, BorderLayout.SOUTH);
		
		//윈도우 설정
		frame.setSize(220, 135);
		frame.setVisible(true);
	}
}
