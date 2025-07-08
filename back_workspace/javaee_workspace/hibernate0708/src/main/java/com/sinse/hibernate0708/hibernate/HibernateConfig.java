package com.sinse.hibernate0708.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

	
public class HibernateConfig {
	private static HibernateConfig instance;
	private SessionFactory sessionFactory;
	
	private HibernateConfig() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static HibernateConfig getInstance() {
		if(instance == null) instance = new HibernateConfig();
		return instance;
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}

}
