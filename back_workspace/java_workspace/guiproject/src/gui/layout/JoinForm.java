package gui.layout;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

//JoinForm은 extends를 선언하는 순간부터 is a 관계에 의해서 Frame이 되어 버림
class JoinForm extends Frame {
	//필요한 재료들을 has a관계로 보유하자
	Label la_title;
	Panel p_center;
	Label la_id;
	TextField t_id;
	Label la_password;
	TextField t_password;
	Label la_name;
	TextField t_name;
	Panel p_south;
	Button bt_login;
	Button bt_join;
	
	//생성자
	public JoinForm(){
		la_title = new Label("회원가입");
		p_center = new Panel();
		
		la_id = new Label("ID");
		t_id = new TextField();
		la_password = new Label("PW");
		t_password = new TextField();
		la_name = new Label("name");
		t_name = new TextField();
		
		p_south = new Panel();
		bt_login = new Button("Login");
		bt_join = new Button("Join");
		
		//스타일 적용
		la_title.setBackground(Color.YELLOW);
		t_id.setBackground(Color.YELLOW);
		t_password.setBackground(Color.YELLOW);
		t_name.setBackground(Color.YELLOW);
		
		
		//조립
		add(la_title, BorderLayout.NORTH);
		
		//센터 영역
		Dimension d = new Dimension(110, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_password.setPreferredSize(d);
		t_password.setPreferredSize(d);
		la_name.setPreferredSize(d);
		t_name.setPreferredSize(d);
		
		//센터 영역의 패널에 컴포넌트 부착
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_password);
		p_center.add(t_password);
		p_center.add(la_name);
		p_center.add(t_name);
		
		//패널을 센터 영역에 부착
		add(p_center);
		
		//남쪽 처리
		p_south.add(bt_login);
		p_south.add(bt_join);
		
		//남쪽에 패널 부착
		add(p_south, BorderLayout.SOUTH);
		
		MemberListener memberListener = new MemberListener(this, bt_login, bt_join);
		//로그인 버튼과 리스너 연결
		bt_login.addActionListener(memberListener);
		//가입 버튼과 리스너 연결
		bt_join.addActionListener(memberListener);
		
		this.setSize(300, 220);	//this 생략가능
		setVisible(true);
	}
	 
	//회원가입과 관련된 컴포넌트가 전부 회원가입 폼 클래스에 존재하므로
	//폼에 대한 유효성 체크 또한 가입 폼 클래스에 진행하는게 더 효율적이다.
	public void checkForm(){
		//아이디를 입력하지 않은 경우
		if(t_id.getText().length() < 1){
			System.out.println("아이디를 입력하세요.");
		}else if(t_password.getText().length() < 1){
			System.out.println("비밀번호를 입력하세요.");
		}else if(t_name.getText().length() < 1){
			System.out.println("이름을 입력하세요.");
		}
	}
	
	public static void main(String[] args) {
		new JoinForm();
	}
}
