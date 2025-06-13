package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.exception.ProductException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;

//Product 테이블에 대한 CRUD만을 수행함. 데이터베이스 작업 코드만 작성
public class ProductDAO {
	DBManager dbManager=DBManager.getInstance();
	
	//한 건 등록
	public void insert(Product product) throws ProductException {
		//상품 입력 폼의 값을 담고 있는 Product 모델 출력하기
		System.out.println(product.getProduct_name());
		System.out.println(product.getBrand());
		System.out.println(product.getPrice());
		System.out.println(product.getDiscount());
		System.out.println(product.getIntroduce());
		System.out.println(product.getDetail());
		System.out.println("SubCategory의 PK는 "+product.getSubCategory().getSubcategory_id());
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product(product_name, brand, price, discount, introduce, detail, subcategory_id)"
				+ "	values(?, ?, ?, ?, ?, ?, ?)");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, product.getProduct_name());
			pstmt.setString(2, product.getBrand());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getDiscount());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setString(6, product.getDetail());
			pstmt.setInt(7, product.getSubCategory().getSubcategory_id());
			
			//쿼리 수행
			result = pstmt.executeUpdate();	//DML 실행
			
			if(result ==0) {
				throw new ProductException("등록이 정상 완료되지 못하였습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//e.printStackTrace에서 처리만 해버리면, 바깥쪽 유저가 사용하는 프로그램에서는 에러의 원인을 알 수 없으므로 신뢰성이 떨어짐
			//따라서 에러가 발생하면, 이 영역에서만 처리를 국한시키지 말고 외부 영역까지 에러 원인을 전달해야 한다.
			throw new ProductException("등록에 실패하였습니다.\n 이용에 불편을 드려 죄송합니다.", e);
		} finally {
			dbManager.release(pstmt);
		}
	}
	
	//방금 수행한 insert에 의해 증가된 pk의 최신값 얻기
	//나의 세션 내에서 증가된 것만 가져오기(select last_insert_id() 함수 사용)
	//절대 max는 사용하면 안됨, 다른 유저 계정에 의한 증가값도 반환해버리기 때문에
	public int selectRecentPk() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pk = 0;
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select last_insert_id() as product_id");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {	//조회 성공이라면
				pk = rs.getInt("product_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return pk;
	}
	
	//모든 상품 목록 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*배열은 생성 시 크기를 먼저 지정해야 하므로 배열을 쓸 경우 rs는 전방향, 후방향, 스크롤까지 가능해야 함*/
		List<Product> list = new ArrayList();
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.topcategory_id, top_name, s.subcategory_id, sub_name"
				+ ", product_id, product_name, brand, price, discount, introduce, detail"
				+ " from topcategory t, subcategory s, product p"
				+ " where t.topcategory_id = s.topcategory_id"
				+ " and s.subcategory_id = p.subcategory_id");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();	//쿼리 수행 및 표 반환
			
			while(rs.next()) {		//레코드 수 만큼 커서 이동
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setDiscount(rs.getInt("discount"));
				product.setIntroduce(rs.getString("introduce"));
				product.setDetail(rs.getString("detail"));
				
				//하위 카테고리
				SubCategory subCategory = new SubCategory();
				subCategory.setSubcategory_id(rs.getInt("s.subcategory_id"));
				subCategory.setSub_name(rs.getString("sub_name"));
				product.setSubCategory(subCategory);
				
				//상위 카테고리
				TopCategory topCategory = new TopCategory();
				topCategory.setTopcategory_id(rs.getInt("t.topcategory_id"));
				topCategory.setTop_name(rs.getString("top_name"));
				subCategory.setTopcategory(topCategory);		//서브가 탑을 참조해야 하므로 보유
				
				list.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
}
