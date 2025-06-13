package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Color;

//다른 로직은 포함하지 않아야 하며 오직 Color 테이블에 대한 CRUD 만을 수행하는 쿼리 수행 객체
//Data Access Object(쿼리 전담 객체)
public class ColorDAO {
	DBManager dbManager = DBManager.getInstance();
	
	//Create
	public int insert(Color color) {
		int result=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into color(color_name) values(?)");		//바인드 변수
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql.toString());	//쿼리 준비
			pstmt.setString(1, color.getColor_name());			//바인드 변수 값 결정
			result = pstmt.executeUpdate();		//DML이 수행되면 이 쿼리에 의해 영향을 받은 레코드 수 반환
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;	//1이면 insert 성공
	}
	
	//Read
	//모든 색상 조회하기
	public List<Color> selectAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Color> list = new ArrayList<>();
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from color");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Color color = new Color();	//레코드 1건을 담는 모델 인스턴스

				color.setColor_id(rs.getInt("color_id"));
				color.setColor_name(rs.getString("color_name"));
				
				list.add(color);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		
		return list;
	}
}
