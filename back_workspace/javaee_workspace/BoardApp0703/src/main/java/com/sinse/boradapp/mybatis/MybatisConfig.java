package com.sinse.boradapp.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory sqlSessionFactory;		//DB 연결을 생성하는 팩토리 역할(내부적으로 DB 커넥션풀, 매퍼 관리함)
	
	//초기화 - 설정 파일 로드 및 팩토리 생성
	private MybatisConfig() {
		String resource = "com/sinse/boradapp/mybatis/mybatis-config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//싱글톤으로 instance 객체 생성
	public static MybatisConfig getInstance() {
		if(instance == null) instance = new MybatisConfig();
		return instance;
	}
	

	//새 DB 세션(연결) 생성
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
