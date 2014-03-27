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
 
	
	public List<List<String>> getReportData(final String sql,
			final ReportDataGenerate genere) {
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
		return (List<List<String>>) o;
	}
	
	public String getReportStr(final String sql,
			final ReportStrGenerate genere) {
		Object o = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				List ansList = query.list();  
				StringBuilder buil = new StringBuilder(); 
				if (ansList != null) {
					Iterator it = ansList.iterator();
					buil.append("[");
					while (it.hasNext()) {
						Object[] objects = (Object[]) it.next();
						buil.append(genere.change(objects)+",");
					}
					if(ansList.size()>0)
					buil.deleteCharAt(buil.lastIndexOf(","));
					buil.append("]");
				}
				return buil.toString();
			}
		});
		return (String) o;
	}
	 
	 

	 
}
