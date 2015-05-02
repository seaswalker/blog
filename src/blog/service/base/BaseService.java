package blog.service.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import blog.model.page.QueryResult;

public interface BaseService<T> {

	public void saveOrUpdate(T entity);
	
	public void delete(Serializable id);
	
	public void delete(T entity);
	
	public T findById(Serializable id);
	
	public List<T> findAll();
	
	/**
	 * 查询
	 */
	public Object query(String hql, Object...params);
	
	public List<T> batchQuery(String hql, Object...params);
	
	public Object batchNativeQuery(String sql, Object...params);
	
	/**
	 * 本地查询
	 */
	public Object nativeQuery(String sql, Object...params);
	
	public void executeSql(String sql);
	
	public void executeHql(String hql);
	
	/**
	 * 分页查询
	 * @param tableName TODO
	 */
	public QueryResult<T> getSrollData(String tableName, String wheres, List<Object> params, HashMap<String, String> orderbys, int pageCode, int pageSize);
	public QueryResult<T> getSrollData(String tableName, String wheres, List<Object> params, int pageCode, int pageSize);
	public QueryResult<T> getSrollData(String tableName, HashMap<String, String> orderbys, int pageCode, int pageSize);
	public QueryResult<T> getSrollData(String tableName, int pageCode, int pageSize);
	
}
