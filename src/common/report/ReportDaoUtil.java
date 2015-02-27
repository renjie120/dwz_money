package common.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.LinkedCaseInsensitiveMap;

import common.base.SpringContextUtil;

/**
 * 报表查询数据库的工具.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class ReportDaoUtil<T> extends HibernateDaoSupport {

	public List<List<String>> getReportData(final String sql,
			final ReportDataGenerate genere) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		List result = new ArrayList();
		List ansList = jdbcTemplate.queryForList(sql);
		if (ansList != null) {
			Iterator it = ansList.iterator();
			while (it.hasNext()) {
				Object[] objects = (Object[]) it.next();
				result.add(genere.change(objects));
			}
		}
		return (List<List<String>>) result;
	}

	public  List<T>  getReportData(final String sql,
			final ReportDataGenerate2<T>  genere) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		List<T> result = new ArrayList<T>();
		List ansList = jdbcTemplate.queryForList(sql);
		if (ansList != null) {
			Iterator it = ansList.iterator();
			while (it.hasNext()) {
				LinkedCaseInsensitiveMap map = (LinkedCaseInsensitiveMap) it
						.next();
				result.add(genere.change(map));
			}
		}
		return (List<T>) result;
	}

	public String getReportStr(final String sql, final ReportStrGenerate genere) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		List ansList = jdbcTemplate.queryForList(sql);
		StringBuilder buil = new StringBuilder();
		if (ansList != null) {
			Iterator it = ansList.iterator();
			buil.append("[");
			while (it.hasNext()) {
				Object[] objects = (Object[]) it.next();
				buil.append(genere.change(objects) + ",");
			}
			if (ansList.size() > 0)
				buil.deleteCharAt(buil.lastIndexOf(","));
			buil.append("]");
		}
		return buil.toString();
	}

	public String getReportStr(final String sql, final ReportStrGenerate2 genere) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		List ansList = jdbcTemplate.queryForList(sql);
		StringBuilder buil = new StringBuilder();
		if (ansList != null) {
			Iterator it = ansList.iterator();
			buil.append("[");
			while (it.hasNext()) {
				LinkedCaseInsensitiveMap map = (LinkedCaseInsensitiveMap) it
						.next();
				buil.append(genere.change(map) + ",");
			}
			if (ansList.size() > 0)
				buil.deleteCharAt(buil.lastIndexOf(","));
			buil.append("]");
		}
		return buil.toString();
	}
}
