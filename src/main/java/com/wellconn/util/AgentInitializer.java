/**
 * 
 */
package com.wellconn.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.AgentDAO;


/**
 * @author doil
 *
 */
public class AgentInitializer {
	
	 private AgentInitializer() {} // 생성 금지

	    public static AppConfig config;
	    public static AgentDAO dao;

	   
	    
	    
	    static class DBObject {
			SqlSession sqlSession;
			SqlSessionFactory sqlSessionFactory;
			
			public DBObject() {
				super();
			}
			
			public SqlSession getSqlSession() {
				return sqlSession;
			}
			public void setSqlSession(SqlSession sqlSession) {
				this.sqlSession = sqlSession;
			}
			public SqlSessionFactory getSqlSessionFactory() {
				return sqlSessionFactory;
			}
			public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
				this.sqlSessionFactory = sqlSessionFactory;
			}
		}
	    
	    
	    
	    public static void initialize(String configPath, String dbConfigPath, String envId) throws Exception {
	        // 설정 파일 로딩
	        config = new AppConfig(configPath);

	        // 로거 초기화
	        String agentName = config.getProperty("agent.name", "AGENT-TEMPLATE");
	        AgentLogger.init(agentName);
	        AgentLogger.log("AGENT IS STARTED");

	        // DB 초기화
	        DBObject db = getDB(dbConfigPath, envId);
	        dao = new AgentDAO(db.getSqlSessionFactory(), db.getSqlSession());
	    }
		
		/**
		 * 
		 * 1. 메소드명 : getDB
		 * 2. 작성일: 2025. 4. 9.
		 * 3. 작성자: doil
		 * 4. 설명: 세션팩토토리를 함께 전달
		 * 5. 수정일: doil
		 */
		private static DBObject getDB(String rssPath, String envId) throws IOException {

			DBObject result = new DBObject();
			
			try (Reader reader = Resources.getResourceAsReader(rssPath)) {
				
				SqlSessionFactory sqlSessionFactory = (envId == null) ?
						new SqlSessionFactoryBuilder().build(reader) : 
						new SqlSessionFactoryBuilder().build(reader, envId);
				
				result.setSqlSessionFactory(sqlSessionFactory);

				SqlSession sqlSession = sqlSessionFactory.openSession(false);
				
				result.setSqlSession(sqlSession);
				
				return result;
		
			} catch (IOException e) {
				throw e;
			}
		
		}

}
