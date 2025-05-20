package gui.chat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.awt.TextField;

public class MyActionListener implements ActionListener {
	TextArea area;
	TextField field;
	
	public MyActionListener(TextArea area, TextField field){
		this.area = area;
		this.field = field;
	}
	
	public void actionPerformed(ActionEvent e){
		//ChatB 띄우기
		new ChatB();
		
		area.append(field.getText());
		field.setText("");
	}
}
