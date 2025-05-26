package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*select 문 수행하기*/
public class SelectTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user = "java";
		String pass ="1234";
		Connection con = null;	//닫아야 하니까
		PreparedStatement pstmt = null;	//query 수행 객체, 쿼리문 마다 1:1 대응됨
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load Success!");
			
			//다른 언어와 달리 Connection 객체는 접속이 성공되면, 메모리에 올라오는 결과물
			//접속 정보를 가진 객체이다.(접속 시도 객체 X)
			con = DriverManager.getConnection(url, user, pass);
			if(con != null) {
				System.out.println("접속 성공");
				
				String sql = "select * from emp";
				pstmt = con.prepareStatement(sql);	//pstmt 객체 생성
				
				//query 실행(DML: insert, update, delete , DDL:create, drop, alter)
				//DML,DDL: executeUpdate(), select: executeQuery()
				rs = pstmt.executeQuery();
				
				//최초에 rs를 생성한 시점에는 커서가 첫번째 레코드보다 위에 위치
				while(rs.next()) {	//커서 한칸 전진					
					//현재 커서가 위치한 곳에서의 ename 컬럼값을 가져옴
					String ename = rs.getString("ename");
					int sal = rs.getInt("sal");
					String job = rs.getString("job");
					Date hiredate = rs.getDate("hiredate");
					
					System.out.println("name: "+ename+", sal: "+sal + ", job: "+job + ", hiredate: "+hiredate);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
}
