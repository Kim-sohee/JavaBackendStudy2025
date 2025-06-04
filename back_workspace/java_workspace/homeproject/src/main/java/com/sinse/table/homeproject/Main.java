package com.sinse.table.homeproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
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

/*엑셀로 불러들인 데이터를 JTable에 출력하고 테이블에서 데이터를 편집한 후, 그 결과를 DB에 반영하기*/
public class Main extends JFrame{
	JPanel p_north;
	JButton bt_load;
	JButton bt_excel;
	JButton bt_save;

	DataModel model;
	JTable table;
	JScrollPane scroll;
	
	String url="jdbc:mysql://localhost:3306/dev";
	String user="java";
	String pwd="1234";
	Connection con;
	JFileChooser chooser = new JFileChooser();
	
	//생성자
	public Main() {
		p_north = new JPanel();
		bt_load = new JButton("Load(DB)");
		bt_excel = new JButton("Load(Excel)");
		bt_save = new JButton("Save");
		table = new JTable();
		scroll = new JScrollPane();
		
		//style
		p_north.setBackground(Color.YELLOW);
		p_north.setPreferredSize(new Dimension(600, 40));
		
		//조립
		p_north.add(bt_excel);
		p_north.add(bt_load);
		p_north.add(bt_save);
		
		add(p_north, BorderLayout.NORTH);
		add(table);
		
		//엑셀 선택해서 읽기
		bt_excel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = chooser.showOpenDialog(Main.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					loadExcel(file);
				}
			}
		});
		
		//DB 읽기
		bt_load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable("select * from member");
			}
		});
		
		//창 닫히면 db닫기
		this.addWindowListener(new WindowAdapter() {
			private void windowClosing() {
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		
		//table 마우스 선택 시 감지
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				System.out.println("row: "+ row+", col: "+col+" 를 선택했습니다.");	//선택한 행, 열 정보 저장
				
			}
		});
		
		connect();
		
		setSize(600, 500);
		setVisible(true);
		
	}
	
	//DB 연동
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
	
	//DB에서 테이블 가져오기
	public void loadTable(String sql) {
		//쿼리 수행 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
			
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = pstmt.getMetaData();
			int colCount = metaData.getColumnCount();	//총 컬럼 수
			
			rs.last();
			int total = rs.getRow();
			
			model = new DataModel();
			model.data = new String[total][colCount];
			model.title = new String[colCount];
			
			for(int i=0; i<colCount; i++) {
				model.title[i] = metaData.getColumnName(i+1);
			}
			
			rs.beforeFirst();
			int index = 0;
			
			while(rs.next()) {
				for(int j=0; j<colCount; j++) {
					model.data[index][j] = rs.getString(j+1);
				}
				index++;
			}
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
	public void loadExcel(File file) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
			
			model = new DataModel();
			model.title = new String[row.getLastCellNum()];
			for(int i=0; i<row.getLastCellNum(); i++) {
				model.title[i] = row.getCell(i).getStringCellValue();
			}
			
			model.data = new String[sheet.getLastRowNum()][row.getLastCellNum()];
			int idx = 0;
			for(int i=sheet.getFirstRowNum()+1; i<=sheet.getLastRowNum(); i++) {
				XSSFRow r = sheet.getRow(i);
				for(int a=0; a<r.getLastCellNum(); a++) {
					XSSFCell cell = r.getCell(a);
					model.data[idx][a] = cell.getStringCellValue();
				}
				idx++;
			}
			table.setModel(model);
			table.updateUI();
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//실행부
	public static void main(String[] args) {
		new Main();
	}
}
