package com.sinse.example.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.example.common.util.DBManager;
import com.sinse.example.main.model.UserModel;

public class UserDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<UserModel> list = new ArrayList<UserModel>();
		
		con = dbManager.getConnection();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from emp");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserModel user = new UserModel();
				user.setEmpno(rs.getInt("empno"));
				user.setEname(rs.getString("ename"));
				user.setJob(rs.getString("job"));
				user.setMgr(rs.getInt("mgr"));
				user.setHiredate(rs.getDate("hiredate"));
				user.setSal(rs.getInt("sal"));
				user.setComm(rs.getInt("comm"));
				user.setDeptno(rs.getInt("deptno"));
				
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}

}
