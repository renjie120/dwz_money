package common.dao;

/**
 * 功能：hibernate  操作 模板基类设计
 *
 * @author programming
 * @version 1.0  2009-3-10下午04:35:34
 * @JDK 5 
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import common.taglib.Option;

public interface IGenericDao<T, ID extends Serializable> {

	public T load(ID id) throws DataAccessException;

	public T get(ID id) throws DataAccessException;

	public boolean contains(T t) throws DataAccessException;

	public void refresh(T t, LockMode lockMode) throws DataAccessException;

	public void refresh(T t) throws DataAccessException;

	public Serializable save(T t) throws DataAccessException;

	public void saveOrUpdate(T t) throws DataAccessException;

	public void saveOrUpdateAll(Collection<T> entities) throws DataAccessException;

	public void update(T t, LockMode lockMode) throws DataAccessException;

	public void update(T t) throws DataAccessException;

	public void delete(T t, LockMode lockMode) throws DataAccessException;

	public void delete(T t) throws DataAccessException;

	public void deleteAll(Collection<T> entities) throws DataAccessException;

	public List<T> find(String queryString, Object value) throws DataAccessException;

	public List<T> find(String queryString, Object[] values) throws DataAccessException;

	public List<T> find(String queryString) throws DataAccessException;

	public List<T> list() throws DataAccessException;

	public List<T> findByNamedQuery(String queryName) throws DataAccessException;

	public List<T> findByNamedQuery(String queryName, Object value) throws DataAccessException;

	public List<T> findByNamedQuery(String queryName, Object[] values) throws DataAccessException;

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);

	public PaginationSupport findPageByQuery(final String hql, final String countHql, final int pageSize,
			final int startIndex);
	/**
	 * 执行普通的sql语句查询,返回集合.
	 * @param sql
	 * @return
	 */
	public PaginationSupport commonQuery(String sql,String countSql,int pageSize,int startIndex,Class c) ;
	
	/**
	 * 调用普通的sql查询语句,返回集合类型.
	 * @param sql 
	 * @return
	 */
	public List commonQuery(String sql) ;
}
