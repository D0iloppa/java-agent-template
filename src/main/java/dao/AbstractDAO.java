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
    	clearCache();
        return sqlSession.selectList(mapperId, param);
    }

    public <T> List<T> selectList(String mapperId) {
    	clearCache();
        return sqlSession.selectList(mapperId);
    }
    
	public Object selectOne(String mapperId, Object param) {
		clearCache();
		return sqlSession.selectOne(mapperId, param);
	}
	
	public Object selectOne(String mapperId) {
		clearCache();
		return sqlSession.selectOne(mapperId);
	}
	
	public int insert(String mapperId, Object param) {
		clearCache();
		
		
		int chk = sqlSession.insert(mapperId, param);
		if(chk>0) commit();
	    return chk;
	}
	
	public int insert(String mapperId) {
		clearCache();
		
		int chk = sqlSession.insert(mapperId);
		if(chk>0) commit();
		
	    return chk;
	}

	public int update(String mapperId, Object param) {
		clearCache();
		
		int chk = sqlSession.update(mapperId, param);
		if(chk>0) commit();
	    return chk;
	}
	
	public int update(String mapperId) {
		clearCache();
		
		int chk = sqlSession.update(mapperId); 
		if(chk>0) commit();
	    return chk;
	}

	public int delete(String mapperId, Object param) {
		clearCache();
		
		int chk = sqlSession.delete(mapperId, param); 
		if(chk>0) commit();
	    return chk;
	}
	
	public int delete(String mapperId) {
		clearCache();
		
		int chk = sqlSession.delete(mapperId);
		if(chk>0) commit();
	    return chk;
	}


	


}
