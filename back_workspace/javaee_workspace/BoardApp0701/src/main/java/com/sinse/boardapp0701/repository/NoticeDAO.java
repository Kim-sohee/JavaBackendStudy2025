package com.sinse.boardapp0701.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.boardapp0701.exception.NoticeException;
import com.sinse.boardapp0701.model.Notice;
import com.sinse.boardapp0701.pool.PoolManager;

public class NoticeDAO {
	PoolManager poolManager = PoolManager.getInstance();
	
	//모든 컬럼 가져오기
	public List<Notice> selectAll(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList<>();
		
		con = poolManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from notice order by notice_id desc");
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//한 개의 컬럼 가져오기
	public Notice select(String notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = new Notice();

		con = poolManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from notice where notice_id=?");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, notice_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	
	//생성하기
	public void insert(Notice notice) throws NoticeException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = poolManager.getConnection();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(title, writer, content) values(?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			
			int result = pstmt.executeUpdate();
			
			if(result < 1) {
				throw new NoticeException("글 등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt);
		}
		
	}
	
	//수정하기
	public void update() {
		
	}
	
	//삭제하기
	public void delete() {
		
	}
}
