package com.sinse.boradapp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.boradapp.exception.NewsException;
import com.sinse.boradapp.model.News;
import com.sinse.boradapp.mybatis.MybatisConfig;

public class NewsDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	//모든 데이터 가져오기
	public List<News> selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		List<News> list = sqlSession.selectList("News.selectAll");
		sqlSession.close();
		return list;
	}
	
	//한 건의 데이터 가져오기
	public News select() {
		return null;
	}
	
	//데이터 추가하기
	public void insert(News news) throws NewsException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("News.insert", news);
		if(result < 1) {
			throw new NewsException("글 등록 실패");
		}
		sqlSession.commit();
		sqlSession.close();
	}
	
	
}
