package gui.font;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

class DocumentB extends Frame{
	Panel p_center;
	Label la_font;
	Label la_color;
	Choice c_font;
	Choice c_color;
	Button bt;
	
	//생성자
	public DocumentB(){
		p_center = new Panel();
		la_font = new Label("Font Size");
		la_color = new Label("Color");
		c_font = new Choice();
		c_color = new Choice();
		bt = new Button("적용");
		
		p_center.setLayout(new FlowLayout());
		
		c_font.add("10");
		c_font.add("20");
		c_font.add("30");
		c_font.add("40");
		
		c_color.add("BLACK");
		c_color.add("BLUE");
		c_color.add("ORANGE");
		c_color.add("RED");
		
		p_center.add(la_font);
		p_center.add(c_font);
		p_center.add(la_color);
		p_center.add(c_color);
		
		add(p_center);
		add(bt, BorderLayout.SOUTH);
		
		setSize(300, 400);
		setVisible(true);
	}
}
