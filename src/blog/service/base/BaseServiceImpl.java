package blog.service.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import blog.dao.base.BaseDao;
import blog.model.page.QueryResult;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;

	/**
	 * 需要子类重写
	 */
	protected void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
	}
	
	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}
	
	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}
	
	@Override
	public T findById(Serializable id) {
		return baseDao.findById(id);
	}
	
	@Override
	public Object query(String hql, Object... params) {
		return baseDao.query(hql, params);
	}
	
	@Override
	public List<T> batchQuery(String hql, Object... params) {
		return baseDao.batchQuery(hql, params);
	}
	
	@Override
	public Object batchNativeQuery(String sql, Object... params) {
		return baseDao.batchNativeQuery(sql, params);
	}
	
	@Override
	public Object nativeQuery(String sql, Object... params) {
		return baseDao.nativeQuery(sql, params);
	}
	
	@Override
	public void executeSql(String sql) {
		baseDao.executeSql(sql);
	}
	
	@Override
	public void executeHql(String hql) {
		baseDao.executeHql(hql);
	}
	
	@Override
	public QueryResult<T> getSrollData(String tableName, String wheres,
			List<Object> params, HashMap<String, String> orderbys, int pageCode, int pageSize) {
		return baseDao.getSrollData(tableName, wheres, params, orderbys, pageCode, pageSize);
	}
	
	@Override
	public QueryResult<T> getSrollData(String tableName, HashMap<String, String> orderbys,
			int pageCode, int pageSize) {
		return getSrollData(tableName, null, null, orderbys, pageCode, pageSize);
	}
	
	@Override
	public QueryResult<T> getSrollData(String tableName, String wheres, List<Object> params,
			int pageCode, int pageSize) {
		return getSrollData(tableName, wheres, params, null, pageCode, pageSize);
	}
	
	@Override
	public QueryResult<T> getSrollData(String tableName, int pageCode, int pageSize) {
		return getSrollData(tableName, null, null, null, pageCode, pageSize);
	}
}
