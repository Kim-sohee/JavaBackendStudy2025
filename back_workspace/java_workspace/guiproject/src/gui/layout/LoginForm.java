package gui.layout;

import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Button;
import java.awt.BorderLayout;

public class LoginForm {
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		Panel panel_center = new Panel();
		Panel panel_south = new Panel();
		
		Label idLabel = new Label("ID");
		TextField idInput = new TextField("", 10);
		Label pwLabel = new Label("Password");
		TextField pwInput = new TextField("", 10);
		
		Button bt_login = new Button("login");
		Button bt_logout = new Button("logout");
		
		panel_center.add(idLabel);
		panel_center.add(idInput);
		panel_center.add(pwLabel);
		panel_center.add(pwInput);
		
		panel_south.add(bt_login);
		panel_south.add(bt_logout);
		
		frame.add(panel_center, BorderLayout.CENTER);
		frame.add(panel_south,  BorderLayout.SOUTH);
		
		frame.setSize(220, 135);
		frame.setVisible(true);
	}
}
