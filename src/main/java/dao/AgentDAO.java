/**
 * 
 */
package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author doil
 *
 */
public class AgentDAO extends AbstractDAO{

	/**
	 * @param sqlSession
	 */
	public AgentDAO(SqlSessionFactory factory, SqlSession sqlSession) {
		super(factory, sqlSession);
	}

		/**
		 * 1. 메소드명 : test
		 * 2. 작성일: 2024. 9. 25.
		 * 3. 작성자: doil
		 * 4. 설명: 
		 * 5. 수정일: doil
		 */
	public List<Map<String, Object>> test() {
		// TODO Auto-generated method stub
		return selectList("agentSql.test");
	}

}
