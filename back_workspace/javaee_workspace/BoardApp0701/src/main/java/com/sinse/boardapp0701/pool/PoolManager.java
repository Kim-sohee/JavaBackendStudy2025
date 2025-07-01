package com.sinse.boardapp0701.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PoolManager {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/spring4";
	private String user = "spring4";
	private String pass = "1234";
	
	private static PoolManager instance;
	
	//Connection들을 모아서 관리할 백터
	Vector<Connection> connectionPool = new Vector<>();
	
	//싱글톤으로 객체 생성
	public static PoolManager getInstance() {
		if(instance == null) instance = new PoolManager();
		return instance;
	}
	
	//커넥션 담아두기
	public void createConnection() {
		for(int i=0; i<20; i++) {
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, user, pass);
				connectionPool.add(con);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//커넥션 빌려주기
	public synchronized Connection getConnection() {
		//빌려줄 것이 없으면 생성
		if(connectionPool.isEmpty()) createConnection();
		return connectionPool.remove(0);
	}
	
	//커넥션 반납하기
	public void release(Connection con) {
		if(con != null) connectionPool.add(con);		//다시 재사용
	}
	
	public void release(Connection con, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) connectionPool.add(con);
	}
	
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
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
		if(con != null) connectionPool.add(con);
	}
}
