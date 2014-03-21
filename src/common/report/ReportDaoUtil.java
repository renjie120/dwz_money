package common.report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 报表查询数据库的工具.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class ReportDaoUtil extends HibernateDaoSupport {

	public List<ReportSet> getTwoColumnReport(final String sql,
			final ReportSetGenerate genere) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				List ansList = query.list();
				List result = new ArrayList();
				if (ansList != null) {
					Iterator it = ansList.iterator();
					while (it.hasNext()) {
						Object[] objects = (Object[]) it.next();
						result.add(genere.change(objects));
					}
				}
				return result;
			}
		});
		return (List<ReportSet>) o;
	}
	
	public List<ReportMultiSeriesSet> getThreeColumnReport(final String sql,
			final ReportMultiSeriesSetGenerate genere) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				List ansList = query.list();
				List result = new ArrayList();
				if (ansList != null) {
					Iterator it = ansList.iterator();
					while (it.hasNext()) {
						Object[] objects = (Object[]) it.next();
						result.add(genere.change(objects));
					}
				}
				return result;
			}
		});
		return (List<ReportMultiSeriesSet>) o;
	}
 
	/**
	 * 查询三列，同时附加条件. 例如按照(年度)统计各个(类别)的(金额)总数.
	 * 
	 * @param table
	 * @param sumColumn
	 *            (金额)
	 * @param groupColumn
	 *            (类别)
	 * @param thirdColumn
	 *            (年度)
	 * @return
	 */
	public List<ReportMultiSeriesSet> getTribleColumn(String table,
			String sumColumn, String groupColumn, String thirdColumn) {
		final String tableString = table;
		final String sumColumnStr = sumColumn;
		final String groupColumnStr = groupColumn;
		final String thirdColumnStr = thirdColumn;
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery("select " + sumColumnStr
						+ "," + groupColumnStr + "," + thirdColumnStr
						+ " from " + tableString);
				List ansList = query.list();
				List result = new ArrayList();
				if (ansList != null) {
					Iterator it = ansList.iterator();
					while (it.hasNext()) {
						Object[] objects = (Object[]) it.next();
						ReportMultiSeriesSet set = new ReportMultiSeriesSet();
						set.setSumColumn(objects[0].toString());
						set.setGroupColumn(objects[1].toString());
						set.setThirdColumn(objects[2].toString());
						result.add(set);
					}
				}
				return result;
			}
		});
		return (List<ReportMultiSeriesSet>) o;
	}

	/**
	 * 查询三列，同时附加条件. 例如按照(年度)统计各个(类别)的(金额)总数.
	 * 
	 * @param table
	 * @param sumColumn
	 *            (金额)
	 * @param groupColumn
	 *            (类别)
	 * @param thirdColumn
	 *            (年度)
	 * @param whereCause
	 *            以where开头的字符串,例如“where year='2011'”
	 * @return
	 */
	public List<ReportMultiSeriesSet> getTribleColumn(String table,
			String sumColumn, String groupColumn, String thirdColumn,
			String whereCause) {
		final String tableString = table;
		final String sumColumnStr = sumColumn;
		final String whereCauseStr = whereCause;
		final String groupColumnStr = groupColumn;
		final String thirdColumnStr = thirdColumn;
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery("select " + sumColumnStr
						+ "," + groupColumnStr + "," + thirdColumnStr
						+ " from " + tableString + " " + whereCauseStr);
				List ansList = query.list();
				List result = new ArrayList();
				if (ansList != null) {
					Iterator it = ansList.iterator();
					while (it.hasNext()) {
						Object[] objects = (Object[]) it.next();
						ReportMultiSeriesSet set = new ReportMultiSeriesSet();
						set.setSumColumn(objects[0].toString());
						set.setGroupColumn(objects[1].toString());
						set.setThirdColumn(objects[2].toString());
						result.add(set);
					}
				}
				return result;
			}
		});
		return (List<ReportMultiSeriesSet>) o;
	}
}
