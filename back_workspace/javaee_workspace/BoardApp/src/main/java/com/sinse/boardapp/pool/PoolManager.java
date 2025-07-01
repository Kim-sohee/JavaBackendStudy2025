package com.sinse.boardapp.pool;

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
	
	//비록 생성자는 막았지만, 내가 지원해주는 메서드로 인스턴스를 가져가라.
	private static PoolManager instance;
	
	//Connection들을 모아서 관리할 백터(순서가 있는 List)
	Vector<Connection> connectionPool = new Vector<>();
	
	private PoolManager() {
		
	}
	
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	//커넥션 담아두기
	public void createConnection() {
		for(int i=0; i<20; i++) {
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, user, pass);
				
				//벡터에 모아 놓기
				connectionPool.add(con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//빌려주기
	public synchronized Connection getConnection() {
		//빌려줄 것이 없으면 또 만든다.
		if(connectionPool.isEmpty()) {	//요소가 소진되었다면...
			createConnection();
			System.out.println("호출됨!");
		}
		return connectionPool.remove(0);		//반환과 동시에, 기존 벡터의 요소를 하나 제거
	}
	
	//반납하기
	public void release(Connection con) {
		if(con != null) {
			connectionPool.add(con);		//다시 벡터에 추가
		}
	}
	public void release(Connection con, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			connectionPool.add(con);		//다시 벡터에 추가
		}
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
		if(con != null) {
			connectionPool.add(con);		//다시 벡터에 추가
		}
	}
}
