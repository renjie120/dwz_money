package dwz.dal;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImpl<T, PK extends java.io.Serializable> extends
		HibernateDaoSupport implements BaseDao<T, PK> {

	// private static Log log = LogFactory.getLog(BaseDaoImpl.class);

	private Class<?> clazz;

	public BaseDaoImpl(Class<T> clazz) {
		this.clazz = clazz;

		// System.out.println(getClass().getGenericInterfaces()[0].toString());
		// Type genType = getClass().getGenericInterfaces()[0];
		// if (!(genType instanceof ParameterizedType)) {
		// this.clazz = Object.class;
		// }
		// Type[] params = ((ParameterizedType)
		// genType).getActualTypeArguments();
		//
		// if (!(params[0] instanceof Class)) {
		// this.clazz = Object.class;
		// }
		// System.out.println(params[0].getClass());
		// this.clazz = params[0].getClass();
	}
 
	public void insert(T model) {
//		Session session = this.getHibernateTemplate().getSessionFactory()
//				.getCurrentSession();// openSession
//		Transaction t = null;
//		try {
//			t = session.beginTransaction();
//			session.save(model);
//			t.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (t != null)
//				t.rollback();
//		} finally {
//			//session.close();
//		}

		 this.getHibernateTemplate().save(model);

	}

	public void insertBatch(final List<T> modelList) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				for (int i = 0; i < modelList.size(); i++) {
					session.save(modelList.get(i));
					if (i % DaoConstant.INSERT_SIZE == 0) {
						session.flush();
					}
				}
				return null;
			}
		});
	}

	public void update(T model) {
		// this.getHibernateTemplate().update(model);
		this.getHibernateTemplate().merge(model);
	}

	public void delete(T model) {
		this.getHibernateTemplate().delete(model);
	}

	@SuppressWarnings("unchecked")
	public java.util.Collection<T> findAll() {

		Package thePackage = clazz.getPackage();
		String packageName = "";
		if (thePackage != null) {
			packageName = thePackage.getName() + ".";
		} else {
			packageName = "";
		}

		String clazzName = clazz.getSimpleName();
		clazzName = packageName + clazzName;
		final String listHql = "from " + clazzName;
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createQuery(listHql);
				return query.list();
			}

		});
		return (List) o;
	}

	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(Serializable modelPK) throws LookupException {

		try {
			return (T) this.getHibernateTemplate().load(clazz, modelPK);
		} catch (RuntimeException e) {
			throw new LookupException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected java.util.Collection<T> findCmd(final String queryName,
			final Object[] params) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.getNamedQuery(queryName);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
		});
		return (List) o;
	}

	@SuppressWarnings("unchecked")
	protected java.util.Collection<T> findCmd(final String queryName,
			final Object[] args, final int startIndex, final int count) {

		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.getNamedQuery(queryName);
				if (args != null && args.length > 0) {
					for (int i = 0; i < args.length; i++) {
						query.setParameter(i, args[i]);
					}
				}
				if (startIndex >= 0 && count > 0) {
					query.setFirstResult(startIndex).setMaxResults(count);
				}
				return query.list();

			}
		});
		return (List) o;
	}

	protected Integer executeCmd(final String queryName, final Object[] params) {

		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.getNamedQuery(queryName);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.executeUpdate();
			}
		});
		return (Integer) o;
	}

	@SuppressWarnings("unchecked")
	public java.util.Collection<T> findByQuery(final String queryStr,
			final Object[] params, final int startIndex, final int count) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createQuery(queryStr);

				if (startIndex >= 0 && count > 0) {
					query.setFirstResult(startIndex).setMaxResults(count);
				}
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();

			}
		});
		return (List) o;
	}

	public java.util.Collection<T> findByQuery(final String queryStr,
			final int startIndex, final int count) {
		return findByQuery(queryStr, null, startIndex, count);
	}

	@SuppressWarnings("unchecked")
	public int countByQuery(final String queryStr, final Object[] params) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createQuery(queryStr);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();

			}
		});
		List<Number> nums = (List<Number>) o;
		if (nums != null && nums.size() > 0)
			return nums.iterator().next().intValue();
		return 0;
	}

	@SuppressWarnings("unchecked")
	public int countByQuery(final String queryStr) {
		return countByQuery(queryStr, null);
	}

	@SuppressWarnings("deprecation")
	public void callProcedure(String procedure) {

		Connection con = getSession().connection();

		CallableStatement cstmt;
		try {
			cstmt = con.prepareCall(procedure);
			cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Collection findByAdvancedQuery(String queryStr, int startIndex,
			int count) {
		return findByQuery(queryStr, null, startIndex, count);
	}

	public java.util.Collection findBySQLQuery(final String queryStr,
			final Object[] params, final int startIndex, final int count) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createSQLQuery(queryStr);

				if (startIndex >= 0 && count > 0) {
					query.setFirstResult(startIndex).setMaxResults(count);
				}
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();

			}
		});
		return (List) o;
	}

	public int countBySQLQuery(final String queryStr, final Object[] params) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createSQLQuery(queryStr);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();

			}
		});
		List<Number> nums = (List<Number>) o;
		if (nums != null && nums.size() > 0)
			return nums.iterator().next().intValue();
		return 0;
	}

	// @Override
	public int countAll() {
		final String countHql = "select count(id) from "
				+ clazz.getSimpleName();

		return countByQuery(countHql);
	}

	@Override
	public Collection<T> findBySqlQuery(final String queryStr,
			final Object[] params) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createSQLQuery(queryStr);

				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();

			}
		});
		return (List) o;
	}

	@Override
	public Collection<T> findByQuery(final String queryStr,
			final Object[] params) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createQuery(queryStr);

				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.list();

			}
		});
		return (List) o;
	}
 

	@Override
	public void exeBySqlQuery(final String queryStr,final Object[] params) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.createSQLQuery(queryStr);

				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.executeUpdate();

			}
		}); 
	} 

}
