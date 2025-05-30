package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main extends JFrame{
	
	Connection con = null;
	
	String[][] data;
	String[] columns= {
			"empno", "ename", "job","mgr","hiredate","sal","comm","deptno"
	};
	JTable table;
	JScrollPane scroll;
	
	//DB 연결 함수
	public void conectDB() {
		String url = "jdbc:mysql://localhost:3306/dev";
		String name = "java";
		String password = "1234";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver load success!");
			
			con = DriverManager.getConnection(url, name, password);
			if(con != null) {
				System.out.println("접속 성공");
				String sql = "select * from emp";
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
	
				rs.last(); 	//레코드 내에서 마지막 행으로 강제 이동
				int total = rs.getRow();
				data = new String[total][8];
				rs.beforeFirst();
				for(int i=0; i<data.length; i++) {
					if(rs.next()==true) {
						for(int j=0; j<columns.length; j++) {
							data[i][j] = rs.getString(columns[j]);
						}
					}
				}
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//생성자
	public Main() {
		conectDB();
		
		table = new JTable(data, columns);
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//메인함수
	public static void main(String[] args) {
		new Main();
	}
}
