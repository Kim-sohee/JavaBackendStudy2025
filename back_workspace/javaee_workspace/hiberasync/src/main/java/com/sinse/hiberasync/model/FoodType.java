package com.sinse.hiberasync.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*[ ORM - Java의 Object와 Relation(관계형 DB) 자체를 직접 매핑 -> 즉 테이블과 java 객체와의 매핑 ]
 * JPA는 java의 자체 api에서 지원하는 인터페이스, 즉 자바 표준이다.
 * hibernate 사설로 JPA를 구현한 구현체, 표준은 아님*/
@Data
@Entity
@Table(name="food_type")
public class FoodType {
	@Id
	private int food_type_id;
	private String title;
}
