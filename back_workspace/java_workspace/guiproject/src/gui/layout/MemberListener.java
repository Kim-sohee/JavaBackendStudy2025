package gui.layout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MemberListener implements ActionListener{
	JoinForm joinForm;
	Button bt_login = null;
	Button bt_join = null;
	
	//생성자호출로 매개변수 값을 받아오자.
	public MemberListener(JoinForm joinForm, Button bt1, Button bt2){
		this.bt_login = bt1;
		this.bt_join = bt2;
		this.joinForm = joinForm;
	}
	
	//오버라이딩
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();	//이벤트를 일으킨 컴포넌트가 반환(Object형으로 리턴)
		
		if(obj == bt_login){
			System.out.println("로그인 버튼 누름");
			joinForm.checkForm();
		}else if(obj == bt_join){
			System.out.println("가입 버튼 누름");
		}
	}
}
