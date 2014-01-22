package common.report;

/**
 * 生成一个报表sql语句.
 * 
 * @author renjie120
 * 
 */
public class ReportBuilderFactory {
	private static class SingletonHolder {
		private static ReportBuilderFactory instance = new ReportBuilderFactory();
	}

	public static ReportBuilderFactory getInstance() {
		return SingletonHolder.instance;
	}

	private ReportBuilderFactory() {

	}

	/**
	 * 返回对一个表的指定列进行count的报表建造器.
	 * @param tableName
	 * @param column
	 * @return
	 */
	public ReportBuilder countByColumn(final String tableName,
			final String column) {
		return new ReportBuilder() {
			@Override
			public Table getTable() {
				return new Table(tableName);
			}

			@Override
			public Group getGroup() {
				return new Group(new Column(column));
			}

			@Override
			public IStatis[] getStatises() {
				IStatis[] cs = new IStatis[1];
				cs[0] = new Count1();
				return cs;
			}

			public Column[] getColumns() {
				Column[] cs = new Column[1];
				cs[0] = new Column(column);
				return cs;
			}
		};
	}
}