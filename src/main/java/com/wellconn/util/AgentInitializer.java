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

	    public static void initialize(String configPath, String dbConfigPath, String envId) throws Exception {
	        // 설정 파일 로딩
	        config = new AppConfig(configPath);

	        // 로거 초기화
	        String agentName = config.getProperty("agent.name", "AGENT-TEMPLATE");
	        AgentLogger.init(agentName);
	        AgentLogger.log("AGENT IS STARTED");

	        // DB 초기화
	        SqlSession dbSession = getDB(dbConfigPath, envId);
	        dao = new AgentDAO(dbSession);
	    }

	    private static SqlSession getDB(String rssPath, String envId) throws IOException {
	        try (Reader reader = Resources.getResourceAsReader(rssPath)) {
	            SqlSessionFactory factory = (envId == null)
	                ? new SqlSessionFactoryBuilder().build(reader)
	                : new SqlSessionFactoryBuilder().build(reader, envId);

	            return factory.openSession();
	        }
	    }
}
