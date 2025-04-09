/**
 * 
 */
package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/**
 * @author doil
 *
 */
public abstract class AbstractDAO {
	
	private final SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	public AbstractDAO(SqlSessionFactory factory, SqlSession sqlSession) {
    	this.sqlSessionFactory = factory;
        this.sqlSession = sqlSession;
    }
	
	private SqlSession getSession() {
		
		if(sqlSession == null) {
			sqlSession = sqlSessionFactory.openSession(false);
			return sqlSession;
		}
		
        try {
            sqlSession.getConnection().isValid(1); // 유효성 검사
        } catch (Exception e) {
            try {
                sqlSession = sqlSessionFactory.openSession(false); // 재생성
                System.out.println("💡 SqlSession was stale. Recreated.");
            } catch (Exception ex) {
                throw new RuntimeException("Failed to reinitialize SqlSession", ex);
            }
        }
        return sqlSession;
    }
	
	
	// 데이터베이스에 작업 내용을 커밋하는 메서드
    public void commit() {
        if (getSession() != null) {
        	getSession().commit();
        }
    }
    
    // 데이터베이스에 작업 내용을 롤백하는 메서드
    public void rollback() {
        if (getSession() != null) {
        	getSession().rollback();
        }
    }
    
    public void clearCache() {
    	getSession().clearCache();
	}
	
	
    
    // default 함수
    public <T> List<T> selectList(String mapperId, Object param) {
    	clearCache();
        return getSession().selectList(mapperId, param);
    }

    public <T> List<T> selectList(String mapperId) {
    	clearCache();
        return getSession().selectList(mapperId);
    }
    
	public Object selectOne(String mapperId, Object param) {
		clearCache();
		return getSession().selectOne(mapperId, param);
	}
	
	public Object selectOne(String mapperId) {
		clearCache();
		return getSession().selectOne(mapperId);
	}
	
	public int insert(String mapperId, Object param) {
		clearCache();
		
		
		int chk = getSession().insert(mapperId, param);
		if(chk>0) commit();
	    return chk;
	}
	
	public int insert(String mapperId) {
		clearCache();
		
		int chk = getSession().insert(mapperId);
		if(chk>0) commit();
		
	    return chk;
	}

	public int update(String mapperId, Object param) {
		clearCache();
		
		int chk = getSession().update(mapperId, param);
		if(chk>0) commit();
	    return chk;
	}
	
	public int update(String mapperId) {
		clearCache();
		
		int chk = getSession().update(mapperId); 
		if(chk>0) commit();
	    return chk;
	}

	public int delete(String mapperId, Object param) {
		clearCache();
		
		int chk = getSession().delete(mapperId, param); 
		if(chk>0) commit();
	    return chk;
	}
	
	public int delete(String mapperId) {
		clearCache();
		
		int chk = getSession().delete(mapperId);
		if(chk>0) commit();
	    return chk;
	}


	


}
