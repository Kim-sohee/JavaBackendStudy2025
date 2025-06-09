package com.sinse.shopadmin.product.repository;

public class UseDuck {

	public static void main(String[] args) {
		Duck d = Duck.getDuck();
		System.out.println(d);
		
		System.out.println(Duck.getDuck());
		System.out.println(Duck.getDuck());
		System.out.println(Duck.getDuck());
		System.out.println(Duck.getDuck());
		System.out.println(Duck.getDuck());
		System.out.println(Duck.getDuck());
	}
}
