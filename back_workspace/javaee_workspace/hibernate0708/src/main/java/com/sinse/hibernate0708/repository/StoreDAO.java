package com.sinse.hibernate0708.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sinse.hibernate0708.exception.StoreException;
import com.sinse.hibernate0708.hibernate.HibernateConfig;
import com.sinse.hibernate0708.model.Store;

public class StoreDAO {
	HibernateConfig config = HibernateConfig.getInstance();
	
	public List selectAll() throws StoreException {
		Transaction tx = null;
		List list = null;
		
		try(Session session = config.getSession()){
			tx = session.beginTransaction();
			TypedQuery query = session.createQuery("from Store", Store.class);
			list = query.getResultList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new StoreException("데이터 조회 실패", e);
		}
		return list;
	}
}
