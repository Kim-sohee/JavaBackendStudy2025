package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.TopCategory;

/*javaEE 개발에서는, 개발자 간 협업을 위해, 정해진 규칙에 따라 코드를 작성해야 한다.
 * 특히 DB에서 테이블이 하나 정의되면, java 개발자는 이 테이블에 대해 1:1 대응하는 모델 객체와
 * 이 테이블에 대한 CRUD(C=insert, R=select, Update, Delete)를 무조건 만든다.
 * 예를 들어, 협업관계에 있는 같은 팀 구성원인 A, B가 있다고 가정했을때, Product 테이블에 대해 A가 모델, DAO를 만들면
 * B는 그 객체를 가져가 사용하면 된다. 중복정의X */

//이 코드는 오직 데이터베이스 관련된 코드만 들어있어야 한다.
//이 객체는 MVC의 영역 중 Model 영역의 즉 로직 객체 영역을 담당한다.
public class TopCategoryDAO {
	//DBManager는 싱글턴 패턴으로 정의되어 있으므로, new 할 수 없다.
	//아래의 코드의 동작 시점은 dbManager가 인스턴스 변수이므로, 누군가가 new TopCategoryDAO() 호출할 때 초기화하면서 DBManager의 인스턴스 값으로 채워진다.
	DBManager dbManager = DBManager.getInstance();
	
	public TopCategoryDAO() {
		
	}
	
	//TopCategory의 모든 레코드 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		
		con = dbManager.getConnection();
		//만일 배열로 rs의 데이터를 담게 되면, 배열의 크기를 명시해야 하는 이유로, rs.last(), getRow(), rs.beforeFirst() 해야 함.
				//TopCategory[] list = new TopCategory[];
				//Collection framework? 객체를 모야서 처리하는데,  유용한 API 지원하는 패키지
				//모여진 모습은 2가지 밖에 없다.
				//1) 순서 있는 모습 API - List, Queue
				//2) 순서 없는 모습 API - Set, Map
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from topcategory");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TopCategory topcategory = new TopCategory();
				topcategory.setTopcategory_id(rs.getInt("topcategory_id")); 	//pk
				topcategory.setTop_name(rs.getString("top_name"));
				list.add(topcategory);		//JS의 push()와 동일하다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
	//TopCategory의 레코드 한 건 가져오기
	public void select() {
		
	}
	
	//TopCategory의 레코드 한 건 입력하기
	public void insert() {
		
	}
	
	//TopCategory의 레코드 한 건 수정하기
	public void update() {
		
	}
	
	//TopCategory의 레코드 한 건 삭제하기
	public void delete() {
		
	}
}
