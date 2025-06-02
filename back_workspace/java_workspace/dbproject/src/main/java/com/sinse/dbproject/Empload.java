package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*JTable은 껍데기에 불과하므로, 연동할 테이블이 수백개라 할지라도
 * TableModel은 1개로 추운하다. 바뀌는 것은 쿼리문 만..
 * 
 * WindowListener를 포함하여, 이벤트 리스너 중 재정의할 메서드의 수가 너무 많은 경우
 * 사용하지도 않는 부모의 메서드를 클래스 코드 안에 남겨놔야 하는 상황이 발생하게 됨.
 * */
public class Empload extends JFrame{
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	JButton bt_excel;
	
	DataModel model;	//JTable이 바라보는 Model 객체
	JTable table;
	JScrollPane scroll;
	
	//윈도우 프레임이 뜰 때 한번 접속하고, 윈도우를 닫을 때 데이터베이스를 닫자.
	String url="jdbc:mysql://localhost:3306/dev";
	String user="java";
	String pwd="1234";
	Connection con;		//윈도우 창 닫으면 접속을 끊어야 하므로, 멤버변수로 빼두어야 한다.

	JFileChooser chooser = new JFileChooser();
	
	//생성자
	public Empload() {
		p_north = new JPanel();
		bt_emp = new JButton("사원 테이블");
		bt_dept = new JButton("부서 테이블");
		bt_excel = new JButton("엑셀에서 로드");
		
		table = new JTable();			//테이블과 모델 연결은 반드시 생성자에서만 할 수 있는 건 아니다.
		scroll = new JScrollPane(table);
		
		//style
		p_north.setPreferredSize(new Dimension(800, 30));
		
		//assemble
		p_north.add(bt_emp);
		p_north.add(bt_dept);
		p_north.add(bt_excel);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		/* 이벤트 구현 시 정의되는 리스너 클래스는 재사용성이 없으므로, 굳이 .java 파일까지 정의해가면서 개발할 필요가 없다.
		내부 클래스 중, 이름 없는 클래스를 가리켜 익명 내부 클래스라 한다.
		주로, 1회성 객체 사용 시(이벤트)
		익명 내부 클래스는, 자신을 감싸고 있는 바깥쪽 외부 클래스의 멤버들을 같이 사용할 수 있다. 즉, 접근할 수 있다는 점이 장점 */
		bt_emp.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				//System.out.println("사원 테이블 눌렀어?");
				String sql = "select * from emp";
				loadTable(sql);
			}
		});
		
		bt_dept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "select * from dept";
				loadTable(sql);
			}
		});
		
		bt_excel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				//내부 클래스는 외부 클래스의 인스턴스 접근 시, 클래스명.this
				int result = chooser.showOpenDialog(Empload.this);
				if(result == JFileChooser.APPROVE_OPTION) {	//파일 열기를 수락했다면
					//유저가 선택한 파일 정보를 얻기
					File file = chooser.getSelectedFile();
					
					loadExcel(file);
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				//System.out.println("창 닫았어?");
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);		// process kill
			}
		});
				
		connect();	//db접속
		
		setSize(800, 630);
		setVisible(true);
	}
	
	//데이터베이스 접속
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			if(con != null) {
				this.setTitle("접속 성공");
			}else {
				this.setTitle("접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//사원 테이블 가져오기
	public void loadTable(String sql) {
		//쿼리 수행 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과 표를 표현한 객체
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
																ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();		//표 반환, 커서의 초기 위치는 첫번째 레코드보다 더 위쪽에 위치
			
			//현재의 rs 자체는 JTable이 이해할 수 없는 상태이므로, TableModel에 rs를 가공하여 넣어주면 된다.
			
			//현재 select 문의 대상이 되는 테이블의 컬럼 정보를 프로그래밍에서 얻어오려면 ResultSetMetaData 객체를 이용하면 된다.
			ResultSetMetaData metaData = pstmt.getMetaData();
			int colCount = metaData.getColumnCount();	//총 컬럼 수
			
			//rs는 몇층까지일까?
			rs.last();	//가장 마지막 row로 보내버림
			int total = rs.getRow();	//총 레코드 수
			
			//레코드 수와 컬럼 수를 알아냈으니, 모델객체가 보유한 현재 null인 이차원 배열을 메모리에 올리자
			model = new DataModel();
			model.data = new String[total][colCount];
			model.title = new String[colCount];	//컬럼 배열 생성
			for(int i=0; i<colCount; i++) {				
				 model.title[i] = metaData.getColumnName(i+1);
			}
			
			//rs의 데이터를 이차원배열로 옮겨심기
			rs.beforeFirst();		//rs 원 위치
			
			int index = 0;	//층수를 접근하기 위한 index;
			
			while(rs.next()) {
				for(int j=0; j<colCount; j++) {						
					model.data[index][j] = rs.getString(j+1);
				}
				index++;
			}
			
			//모든 데이터가 완성되었으므로, JTable에 모델을 동적으로 적용
			table.setModel(model);
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		}
	}
	
	//엑셀로 불러오기
	/*자바 자체적인 api에는 엑셀을 연동하는 기능이 없기 때문에, apache에서 배포하는 POI 패키지를 사용
	 * 
	 * 액셀파일(WorkBook) > 시트(WorkSheet) > Row > Cell */
	public void loadExcel(File file) {
		//Excel 97 ~ 2001 구버전 :  .xls -> HSSFWorkBoock
		// 그 이후: .xlsx -> XSSWorkBook
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//시트에 접근하자
			XSSFSheet sheet = workbook.getSheetAt(0);	//0번째 시트 얻기
			
			//Row에 접근하자
			XSSFRow row = sheet.getRow(0);
			
			//Model의 컬럼 제목 배열인 title 배열에 컬럼 제목들을 채워넣기
			model = new DataModel();
			model.title = new String[row.getLastCellNum()];
			for(int i=0; i<row.getLastCellNum(); i++) {
				model.title[i] = row.getCell(i).getStringCellValue();	
			}
			
			//2번째 행부터 데이터를 접근하여 Model의 data에 대입하자.
			model.data = new String[sheet.getLastRowNum()][row.getLastCellNum()];
			int idx = 0;
			for(int i=sheet.getFirstRowNum()+1; i<=sheet.getLastRowNum(); i++) {
				XSSFRow r = sheet.getRow(i);
				//하나의 row를 이루고 있는 셀 만큼 반복
				for(int a=0; a<r.getLastCellNum(); a++) {
					XSSFCell cell = r.getCell(a);
					model.data[idx][a] = cell.getStringCellValue();
				}
				idx++;
			}
			
			//모든 데이터가 완성되었으므로, JTable에 모델을 동적으로 적용
			table.setModel(model);
			table.updateUI();
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Empload();
	}
}
