package blog.dao.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import blog.model.page.QueryResult;

public interface BaseDao<T> {

	public void saveOrUpdate(T entity);
	
	public void delete(Serializable id);
	
	public void delete(T entity);
	
	public T findById(Serializable id);
	
	public List<T> findAll();
	
	public T query(String hql, Object...params);
	
	public List<T> batchQuery(String hql, Object...params);
	
	public Object batchNativeQuery(String sql, Object...params);
	
	/**
	 * 本地查询
	 */
	public Object nativeQuery(String sql, Object...params);
	
	/**
	 * 执行hql语句
	 */
	public void executeHql(String hql);
	
	/***
	 * 执行sql语句
	 */
	public void executeSql(String sql);
	
	/**
	 * 分页查询
	 * @param tableName要查询的表名
	 */
	public QueryResult<T> getSrollData(String tableName, String wheres, List<?> params, HashMap<String, String> orderbys, int pageCode, int pageSize);
}
