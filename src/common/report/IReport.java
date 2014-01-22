package common.report;

/**
 * 生成一个报表sql语句.
 * 
 * @author renjie120
 * 
 */
public interface IReport {

	public Table getTable();

	public Group getGroup();

	public IStatis[] getStatises();

	public String getHaving();

	public Column[] getColumns();

	public String getWhere();
}
