package dwz.dal;

import java.util.List;

public interface BaseDao<T, PK extends java.io.Serializable> {

	public void insert(T model);

	public void insertBatch(List<T> modelList);

	public void update(T model);

	public void delete(T model);

	public T findByPrimaryKey(PK modelPK) throws LookupException;

	public java.util.Collection<T> findAll();

	public java.util.Collection<T> findByQuery(final String queryStr,
			final int startIndex, final int count);

	@SuppressWarnings("unchecked")
	public java.util.Collection findByAdvancedQuery(final String queryStr,
			final int startIndex, final int count);

	public java.util.Collection<T> findByQuery(final String queryStr,
			final Object[] params, final int startIndex, final int count);

	/**
	 * 添加新的查询sql的语句.
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 */
	public java.util.Collection<T> findBySqlQuery(final String queryStr,
			final Object[] params);

	/**
	 * 执行原始s'q'l
	 * @param queryStr
	 * @param params
	 */
	public void exeBySqlQuery(final String queryStr,
			final Object[] params);

	
	/**
	 * 自己写hql进行查询的语句.
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 */
	public java.util.Collection<T> findByQuery(final String queryStr,
			final Object[] params);

	public int countAll();

	public int countByQuery(final String queryStr);

	public int countByQuery(final String queryStr, final Object[] params);

	public void callProcedure(String procedure);

	public java.util.Collection findBySQLQuery(final String queryStr,
			final Object[] params, final int startIndex, final int count);

	public int countBySQLQuery(final String queryStr, final Object[] params);

}
