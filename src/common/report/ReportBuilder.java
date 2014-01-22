package common.report;

import common.util.CommonUtil;

/**
 * 生成一个报表sql语句.
 * 
 * @author renjie120
 * 
 */
public abstract class ReportBuilder implements IReport {
	private Table table;
	private Column[] columns;
	private String where;
	private Group group;
	private String having;
	private IStatis[] statis;

	public String generateSql() {
		StringBuffer buf = new StringBuffer();

		table = getTable();

		columns = getColumns();

		where = getWhere();

		group = getGroup();

		having = getHaving();

		statis = getStatises();

		if (CommonUtil.isBlank(table))
			throw new RuntimeException("必须要有表名！");

		if (CommonUtil.isBlank(group))
			throw new RuntimeException("必须要有分组名！");

		if (CommonUtil.isBlank(statis) || statis.length < 1)
			throw new RuntimeException("必须要统计列！");

		buf.append(" SELECT ");
		for (IStatis s : statis) {
			buf.append(s.statis());
			buf.append(",");
		}
		buf.deleteCharAt(buf.lastIndexOf(","));

		if (columns != null && columns.length > 0) {
			buf.append(",");
			for (Column c : columns) {
				buf.append(c.getName());
				buf.append(",");
			}
			buf.deleteCharAt(buf.lastIndexOf(","));
		}

		buf.append(" from ");
		buf.append(table);
		if (CommonUtil.isNotEmpty(where))
			buf.append(where);
		buf.append(group);
		if (CommonUtil.isNotEmpty(having))
			buf.append(having);
		return buf.toString();
	}

	public abstract Table getTable();

	public abstract Group getGroup();

	public abstract IStatis[] getStatises();

	public String getHaving() {
		return null;
	}

	public Column[] getColumns() {
		return null;
	}

	public String getWhere() {
		return null;
	}

}
