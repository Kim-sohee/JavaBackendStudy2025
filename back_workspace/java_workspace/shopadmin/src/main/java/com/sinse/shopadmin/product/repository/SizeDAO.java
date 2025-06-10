package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Size;

public class SizeDAO {
	DBManager dbManager = DBManager.getInstance();
	
	//Update
	public int insert(Size size) {
		int result=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into size(size_name) values(?)");
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, size.getSize_name());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	//Read
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Size> list = new ArrayList<Size>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from size");
		
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Size size = new Size();
				size.setSize_id(rs.getInt("size_id"));
				size.setSize_name(rs.getString("size_name"));
				
				list.add(size);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
}
