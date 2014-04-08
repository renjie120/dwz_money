package common.report;

import java.util.List;

/**
 * 生成一个报表sql语句.
 * 
 * @author renjie120
 * 
 */
public interface IReport {

	public Table getTable();

	public Group getGroup();

	public List<IStatis> getStatises();

	public String getHaving();

	public List<Column> getColumns();

	public String getWhere();
	
	public List<String> getOrders();
}
