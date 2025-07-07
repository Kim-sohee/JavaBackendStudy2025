package com.sinse.hiberasync.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {
	private static HibernateConfig instance;
	private SessionFactory sessionFactory;
	
	private HibernateConfig() {
		//hibernate 설정 xml 위치를 따로 명시하지 않음 그 대신 개발자는 설정 파일의 위치를 resources 둬야 인식된다.
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static HibernateConfig getInstance() {
		if(instance == null) instance = new HibernateConfig();
		return instance;
	}
	
	//SessionFactory로 부터 쿼리를 수행하는 객체를 반환하는 메서드
	public Session getSession() {
		return sessionFactory.openSession();
	}
}
