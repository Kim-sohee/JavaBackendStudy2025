package db;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame {
	JTable table;
	JScrollPane scroll;
	
	//데이터에 사용될 2차원 배열
	String[][] data = {
		{"Java", "Python", "C#", "JavaScript", "R"},
		{"JSP", "Servlet", "React", "Vue", "Node.js"},
		{"Oracle", "MySQL", "MariaDB", "MSSQL", "DB2"}
	};
	//컬럼 제목에 사용될 1차원 배열
	String[] columns = {"과목1", "과목2","과목3","과목4","과목5"};
	
	public TableTest() {
		table = new JTable(data, columns);	//row, column
		scroll = new JScrollPane(table);
		
		//BorderLayout에 붙이면 테이블의 컬럼명이 안 나옴.
		setLayout(new FlowLayout());
		add(scroll);
		
		setSize(500, 550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TableTest();
	}

}
