/**
 * 
 */
package app;

import java.io.IOException;
import com.wellconn.util.AppConfig;
import com.wellconn.util.AgentInitializer;
import com.wellconn.util.AgentLogger;

import dao.AgentDAO;

/**
 * @author doil
 *
 */
public class AgentApp {
	

	/**
	 * 1. 메소드명 : main
	 * 2. 작성일: 2024. 2. 1.
	 * 3. 작성자: doil
	 * 4. 설명: 
	 * 5. 수정일: doil
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		 try {
	            
			 	AgentInitializer.initialize("config/app.properties", "config/SqlMapConfig.xml", null);

	            AppConfig config = AgentInitializer.config;
	            AgentDAO dao = AgentInitializer.dao;


	            AgentLogger.log("AGENT IS SHUTDOWNED");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}


	

}
