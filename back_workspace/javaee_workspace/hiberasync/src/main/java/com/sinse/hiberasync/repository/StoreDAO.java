package com.sinse.hiberasync.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sinse.hiberasync.exception.StoreException;
import com.sinse.hiberasync.hibernate.HibernateConfig;
import com.sinse.hiberasync.model.Store;

public class StoreDAO {
	HibernateConfig config = HibernateConfig.getInstance();
	
	//한 건 넣기
	public void insert(Store store) throws StoreException {
		Transaction tx = null;
		
		try(Session session = config.getSession()){
			tx = session.beginTransaction();
			session.save(store);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new StoreException("등록 실패", e);
		}
	}
	
	//모든 레코드 조회하기
	public List selectAll() throws StoreException{
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
			throw new StoreException("목록 조회 실패", e);
		}
		return list;
	}
	
	//한 건 조회
	public Store select(int store_id) throws StoreException{
		Transaction tx = null;
		Store store = null;
		
		try(Session session = config.getSession()){
			tx = session.beginTransaction();
			store = session.get(Store.class, store_id);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new StoreException("조회 실패", e);
		}
		return store;
	}
	
	//한 건 수정하기
	public void update(Store store) throws StoreException{
		Transaction tx = null;
		
		try(Session session = config.getSession()){
			tx = session.beginTransaction();
			session.update(store);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new StoreException("수정 실패", e);
		}
	}
	
	//한 건 삭제하기
	public void delete(int store_id) throws StoreException{
		Transaction tx = null;
		
		try(Session session = config.getSession()){
			tx = session.beginTransaction();
			Store store = session.get(Store.class, store_id);
			if(store != null) session.delete(store);  
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new StoreException("삭제 실패", e);
		}
	}
}
