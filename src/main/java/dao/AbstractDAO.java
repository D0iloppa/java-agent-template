/**
 * 
 */
package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
/**
 * @author doil
 *
 */
public abstract class AbstractDAO {
	
	private final SqlSession sqlSession;
	
	// 데이터베이스에 작업 내용을 커밋하는 메서드
    public void commit() {
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }
    
    // 데이터베이스에 작업 내용을 롤백하는 메서드
    public void rollback() {
        if (sqlSession != null) {
            sqlSession.rollback();
        }
    }
    
    public void clearCache() {
		this.sqlSession.clearCache();
	}
	
	
    // 생성자를 통해 SqlSession을 주입받습니다.
    public AbstractDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    // default 함수
    public <T> List<T> selectList(String mapperId, Object param) {
        return sqlSession.selectList(mapperId, param);
    }

    public <T> List<T> selectList(String mapperId) {
        return sqlSession.selectList(mapperId);
    }
    
	public Object selectOne(String mapperId, Object param) {
		return sqlSession.selectOne(mapperId, param);
	}
	
	public Object selectOne(String mapperId) {
		return sqlSession.selectOne(mapperId);
	}
	
	public int insert(String mapperId, Object param) {
	    return sqlSession.insert(mapperId, param);
	}
	
	public int insert(String mapperId) {
	    return sqlSession.insert(mapperId);
	}

	public int update(String mapperId, Object param) {
	    return sqlSession.update(mapperId, param);
	}
	
	public int update(String mapperId) {
	    return sqlSession.update(mapperId);
	}

	public int delete(String mapperId, Object param) {
	    return sqlSession.delete(mapperId, param);
	}
	
	public int delete(String mapperId) {
	    return sqlSession.delete(mapperId);
	}


	


}
