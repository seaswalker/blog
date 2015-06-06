package blog.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import blog.model.page.QueryResult;
import blog.util.DataUtil;
import blog.util.StringUtil;

@SuppressWarnings("unchecked")
public abstract class DaoSupport<T> implements BaseDao<T> {
	
	@Resource
	protected SessionFactory sessionFactory;
	//泛型的类对象
	private Class<T> clazz;
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(T entity) {
		Session session = getSession();
		session.saveOrUpdate(entity);
	}

	@Override
	public void delete(Serializable id) {
		T entity = findById(id);
		getSession().delete(entity);
	}
	
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public T findById(Serializable id) {
		T entity = (T) getSession().get(clazz, id);
		return entity;
	}
	
	@Override
	public List<T> findAll() {
		Query query = getSession().createQuery("from " + clazz.getSimpleName());
		return query.setCacheable(true).list();
	}
	
	@Override
	public T query(String hql, Object... params) {
		Query query = getSession().createQuery(hql).setCacheable(true);
		for(int i = 0;i < params.length;i ++) {
			query.setParameter(i, params[i]);
		}
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}
	
	@Override
	public List<T> batchQuery(String hql, Object... params) {
		Query query = getSession().createQuery(hql).setCacheable(true);
		for(int i = 0;i < params.length;i ++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	@Override
	public Object batchNativeQuery(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		for(int i = 0;i < params.length;i ++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	@Override
	public Object nativeQuery(String sql, Object... params) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for(int i = 0;i < params.length;i ++) {
			sqlQuery.setParameter(i, params[i]);
		}
		return sqlQuery.uniqueResult();
	}
	
	@Override
	public void executeHql(String hql) {
		getSession().createSQLQuery(hql).executeUpdate();
	}
	
	@Override
	public void executeSql(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * sql查询实现
	 */
	@Override
	public QueryResult<T> getSrollData(String tableName, String wheres,
			List<?> params, HashMap<String, String> orderbys, int pageCode, int pageSize) {
		String whereSql = (StringUtil.isEmpty(wheres)) ? "" : wheres;
		String sql = "select * from " + tableName + " " + whereSql;
		String countSql = "select count(*) from " + tableName + whereSql;
		QueryResult<T> queryResult = new QueryResult<T>();
		if(DataUtil.isValid(orderbys)) {
			//设置排序
			StringBuilder sb = new StringBuilder(" order by ");
			for(String key : orderbys.keySet()) {
				sb.append(key).append(" ").append(orderbys.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sql += sb.toString();
		}
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setCacheable(true);
		//设置自动封装
		query.addEntity(clazz);
		SQLQuery countQuery = getSession().createSQLQuery(countSql);
		//设置参数
		if(DataUtil.isValid(params)) {
			for(int i = 0;i < params.size();i ++) {
				query.setParameter(i, params.get(i));
				countQuery.setParameter(i, params.get(i));
			}
		}
		query.setFirstResult((pageCode - 1) * pageSize);
		query.setMaxResults(pageSize);
		queryResult.setRecords(query.list());
		queryResult.setRecordCount(((BigInteger) countQuery.uniqueResult()).intValue());
		return queryResult;
	}
	
	/**
	 * 获取泛型类对象
	 */
	public DaoSupport() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

}
