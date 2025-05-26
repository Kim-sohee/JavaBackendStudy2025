/*Emp 테이블에 있는 레코드를 모두 가져와서 JTable에 출력*/
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmpLoad extends JFrame{
	JTable table;
	JScrollPane scroll;
	String[][] data;	//몇건 인지 알 수 없으므로 배열을 아직 생성하지 못함
	String[] columns= {
			"empno", "ename", "job","mgr","hiredate","sal","comm","deptno"
	};
	
	//mysql 연동
	public void loadData() {
		String url="jdbc:mysql://localhost:3306/dev";
		String user="java";
		String pw="1234";
		
		Connection con = null;	//접속 시도가 아니라, 접속 후 그 정보를 가진 객체, 이 객체를 이용하여 접속을 종료할 수 있다.
		PreparedStatement pstmt = null;	//쿼리문을 수행하는 객체
		ResultSet rs = null;	//표 데이터를 표현한 객체
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver load success!");
			
			con = DriverManager.getConnection(url, user, pw);
			if(con != null) {
				System.out.println("접속 성공");
				
				String sql = "select * from emp order by empno asc";
				
				//TYPE_SCROLL_INSENSITIVE: 커서가 전방향, 후방향 및 건너뛰기 가능함, 커서의 위치를 자유자재로 조절 가능한 옵션
				//CONCUR_READ_ONLY: ResultSet에 담은 레코드를 읽기 전용으로 쓰겠다.
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery(); //select 문은 ResultSet으로 결과를 받음
				
				rs.last(); 	//레코드 내에서 마지막 행으로 강제 이동
				int total = rs.getRow();
				
				//rs가 데이터베이스 연동 결과이므로, 바로 이 시점 이후부터 배열의 크기를 결정지을 수 있다.
				//자바 뿐만 아니라 대부분의 언어에서의 배열은 선언 시 반드시 크기를 결정해야 함. 
				data = new String[total][8];
				
				//아래의 코드를 반복문으로 처리하면서 층수 변경
				//rs.next() 커서도 한칸 씩 이동
				//주의: 현재 총 레코드 수를 얻어오는 바람에 커서가 제일 아래 가 있음
				rs.beforeFirst();	//커서의 위치를 다시 원상복귀
				
				for(int i=0; i<data.length; i++) {
					if(rs.next()==true) {						
						data[i][0]=rs.getString("empno");	//empno
						data[i][1]=rs.getString("ename");	//ename
						data[i][2]=rs.getString("job");	//job
						data[i][3]=rs.getString("mgr");	//mgr
						data[i][4]=rs.getString("hiredate");	//hiredate
						data[i][5]=rs.getString("sal");	//sal
						data[i][6]=rs.getString("comm");	//comm
						data[i][7]=rs.getString("deptno");	//deptno
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	public EmpLoad() {
		//mysql에서 이미 사원 정보를 가져왔어야 함
		loadData();
		
		table = new JTable(data, columns);
		scroll = new JScrollPane(table);
		
		add(scroll);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new EmpLoad();
	}
}
