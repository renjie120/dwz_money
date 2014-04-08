package common.report;

import java.util.ArrayList;
import java.util.List;

import common.util.CommonUtil;

/**
 * 生成一个报表sql语句.
 * 
 * @author renjie120
 * 
 */
public class MyReport implements IReport {
	private Table table;
	private List<Column> columns;
	private String where;
	private Group group;
	private String having;
	private List<IStatis> statises;
	private List<String> orders;

	private MyReport(Builder build) {
		this.table = build.table;
		this.columns = build.columns;
		this.where = build.where;
		this.group = build.group;
		this.having = build.having;
		this.statises = build.statises;
		this.orders = build.orders;
	}

	public static class Builder {
		private Table table;
		private List<Column> columns;
		private String where;
		private Group group;
		private String having;
		private List<IStatis> statises;
		private List<String> orders;

		public Builder(String table) {
			super();
			this.table = new Table(table);
		}

		public Builder groupBy(String... group) {
			if (group.length == 1) {
				Column c = new Column(group[0]);
				this.group = new Group(c);
			} else if (group.length > 1) {
				Column c = new Column(group[0]);
				this.group = new Group(c);
				for (int i = 1, j = group.length; i < j; i++) {
					this.group.addColumn(new Column(group[i]));
				}
			}
			return this;
		}

		public Builder count() {
			if (this.statises == null) {
				statises = new ArrayList<IStatis>();
			}
			statises.add(new Count1());
			return this;
		}

		public Builder order(String column) {
			if (this.orders == null) {
				orders = new ArrayList<String>();
			}
			orders.add(column);
			return this;
		}

		public Builder where(String where) {
			this.where = where;
			return this;
		}

		public Builder sum(String str) {
			if (this.statises == null) {
				statises = new ArrayList<IStatis>();
			}
			Sum s = new Sum();
			s.setColumn(new Column(str));
			statises.add(s);
			return this;
		}

		public Builder colomns(String... strs) {
			int len = strs.length;
			this.columns = new ArrayList<Column>();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					columns.add(new Column(strs[i]));
				}
			}
			return this;
		}

		// 构造器入口
		public MyReport build() {
			return new MyReport(this);
		}
	}

	public String generateSql() {
		StringBuffer buf = new StringBuffer();

		table = getTable();

		columns = getColumns();

		where = getWhere();

		group = getGroup();

		having = getHaving();

		statises = getStatises();

		if (CommonUtil.isBlank(table))
			throw new RuntimeException("必须要有表名！");

		if (CommonUtil.isBlank(group))
			throw new RuntimeException("必须要有分组名！");

		if (CommonUtil.isBlank(statises) || statises.size() < 1)
			throw new RuntimeException("必须要统计列！");

		buf.append(" SELECT ");
		for (IStatis s : statises) {
			buf.append(s.statis());
			buf.append(",");
		}
		buf.deleteCharAt(buf.lastIndexOf(","));

		if (columns != null && columns.size() > 0) {
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
			buf.append(" where "+where+" ");
		buf.append(group);
		if (CommonUtil.isNotEmpty(having))
			buf.append(having);
		
		if (CommonUtil.isNotBlank(orders)){
			buf.append(" order by ");
			for(String s:orders){
				buf.append(s+",");
			}
			buf.deleteCharAt(buf.lastIndexOf(","));
		}
			 
		return buf.toString();
	}

	public Table getTable() {
		return table;
	}

	public String getWhere() {
		return where;
	}

	public Group getGroup() {
		return group;
	}

	public String getHaving() {
		return having;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<IStatis> getStatises() {
		return statises;
	}

	@Override
	public List<String> getOrders() {
		return orders;
	}

}
