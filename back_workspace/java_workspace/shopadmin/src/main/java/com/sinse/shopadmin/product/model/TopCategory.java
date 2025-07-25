package com.sinse.shopadmin.product.model;

//아래의 클래스는 로직을 담기 위한 객체가 아니라, 단지 DB의 TopCategory 테이블의
//레코드 1건을 담기 위한 모델 객체이다.
//레코드를 담고 있기 때문에 보안상 은닉화
public class TopCategory {
	private int topcategory_id;
	private String top_name;
	
	//Getter & Setter
	public int getTopcategory_id() {
		return topcategory_id;
	}
	public void setTopcategory_id(int topcategory_id) {
		this.topcategory_id = topcategory_id;
	}
	public String getTop_name() {
		return top_name;
	}
	public void setTop_name(String top_name) {
		this.top_name = top_name;
	}
	
	//누군가가 이 클래스를 String 취급하면서 출력하려 한다면, 자동으로 호출되는 메서드인 Object로 부터 상속받은
	//toString() 메서드가 호출되므로, 만일 이 객체의 데이터 중 원하는 데이터를 출력하게 하려면 toString을 오버라이딩 해야 한다.
	@Override
	public String toString() {
		return top_name;
	}
}
