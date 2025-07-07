package com.sinse.hiberasync.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="store")
public class Store {
	@Id
	private int store_id;
	private String store_name;
	private String tel;
	
	@ManyToOne
	@JoinColumn(name="food_type_id")
	private FoodType foodType;		//Mybatis에서는 association으로 채운다.
}
