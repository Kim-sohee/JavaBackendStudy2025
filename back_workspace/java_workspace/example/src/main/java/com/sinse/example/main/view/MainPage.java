package com.sinse.example.main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sinse.example.common.util.DBManager;
import com.sinse.example.main.model.UserDataModel;
import com.sinse.example.main.repository.UserDAO;

public class MainPage extends JFrame{
	JPanel p_north;
	JButton bt_excel;		//엑셀 선택 후 insert
	JButton bt_download;		//표를 엑셀로 다운로드
	JButton bt_pdf;
	JTable table;
	JScrollPane scroll;
	JFileChooser chooser;
	
	UserDAO userDAO;
	String[] columnNames = {"empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno"};
	UserDataModel userDataModel;
	
	public DBManager dbManager = DBManager.getInstance();
	public Connection con;
	
	public MainPage() {
		//생성
		userDAO = new UserDAO();
		userDataModel = new UserDataModel();
		p_north = new JPanel();
		bt_excel = new JButton("일괄 등록하기");
		bt_download = new JButton("엑셀 다운로드");		
		bt_pdf = new JButton("pdf 다운로드");

		table = new JTable(userDataModel);
		scroll = new JScrollPane(table);
		chooser = new JFileChooser();
		
		//스타일
		p_north.setBackground(Color.LIGHT_GRAY);
		p_north.setPreferredSize(new Dimension(600, 40));
		
		//조립
		p_north.add(bt_excel);
		p_north.add(bt_download);
		p_north.add(bt_pdf);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);

		
		//엑셀 선택하여 읽기
		bt_excel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = chooser.showOpenDialog(MainPage.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
//					loadExcel(file);
				}
			}
		});
		
		//엑셀 다운로드
		bt_download.setToolTipText("아래 데이터를 엑셀 파일로 다운로드 할 수 있습니다.");
		bt_download.addActionListener(e->{
			exportToExcel(columnNames, userDataModel);
		});
		
		//PDF 다운로드
		bt_pdf.setToolTipText("아래 데이터를 pdf 파일로 다운로드 할 수 있습니다.");
		bt_pdf.addActionListener(e->{
			exportToPdf(columnNames, userDataModel);
		});
		
		//페이지 닫히면 db 연결 끊기
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//데이터베이스 접속 끊기
				dbManager.release(con);
				System.exit(0);
			}
		});
		
		setSize(600, 500);
		setVisible(true);
		
	}
	
	public void exportToExcel(String[] title, UserDataModel tableModel) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("download");
		
		//헤더 행 생성
		Row headerRow = sheet.createRow(0);
		for(int i=0; i<title.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(title[i]);
		}
		
		//데이터 추가
		for(int rowIdx=0; rowIdx<tableModel.getRowCount(); rowIdx++) {
			Row row = sheet.createRow(rowIdx+1);
			for(int colIdx=0; colIdx<tableModel.getColumnCount(); colIdx++) {
				row.createCell(colIdx).setCellValue(tableModel.getValueAt(rowIdx, colIdx).toString());
			}
		}
		
		//다운로드 폴더에 저장하도록 유도
		 String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
		 String excelFilePath = getUniqueFileName(downloadPath, "UserData", ".xlsx");
		
		//파일 다운받기
		try {
			FileOutputStream fo = new FileOutputStream(excelFilePath);
			workbook.write(fo);
			workbook.close();
			JOptionPane.showMessageDialog(this, "엑셀 파일 저장 완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "엑셀 저장 실패!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//PDF 다운로드하기
	public void exportToPdf(String[] titleArray, UserDataModel tableModel) {
		//다운로드 폴더에 저장하도록 유도
		String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
	    String pdfFilePath = getUniqueFileName(downloadPath, "UserData", ".pdf");
		 
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
			document.open();
			
			//제목 스타일 지정
			Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);	//폰트 및 크기 설정
			Paragraph title = new Paragraph("User Data", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);	//중앙 정렬
			document.add(title);
			document.add(new Paragraph("\n"));
			
			//테이블 생성
			PdfPTable table = new PdfPTable(titleArray.length);
			
			//헤더 추가
			for(int i=0; i<titleArray.length; i++) {
				String column = titleArray[i];
				
				PdfPCell headerCell = new PdfPCell(new Phrase(column));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			
			//데이터 추가
			for(int rowIdx=0; rowIdx < tableModel.getRowCount(); rowIdx++) {
				for(int colIdx=0; colIdx< tableModel.getColumnCount(); colIdx++) {
					table.addCell(tableModel.getValueAt(rowIdx, colIdx).toString());
				}
			}
			document.add(table);
			document.close();
			JOptionPane.showMessageDialog(this, "PDF 파일이 저장되었습니다.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "PDF 파일 저장 실패", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//유일한 파일 이름을 지정
	public String getUniqueFileName(String directory, String baseName, String extension) {
	    int count = 1;
	    File file = new File(directory, baseName + extension);

	    while (file.exists()) { // 파일이 존재하면 번호 증가
	        file = new File(directory, baseName + "(" + count + ")" + extension);
	        count++;
	    }
	    
	    return file.getAbsolutePath();
	}

	
	public static void main(String[] args) {
		new MainPage();
	}
}
