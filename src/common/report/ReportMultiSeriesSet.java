package common.report;

/**
 * 用于形成三列形式的报表的基础数据对象.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class ReportMultiSeriesSet {
	private String sumColumn;
	private String groupColumn;
	private String thirdColumn;

	public String getSumColumn() {
		return sumColumn;
	}

	public void setSumColumn(String sumColumn) {
		this.sumColumn = sumColumn;
	}

	public String getGroupColumn() {
		return groupColumn;
	}

	public void setGroupColumn(String groupColumn) {
		this.groupColumn = groupColumn;
	}

	public String getThirdColumn() {
		return thirdColumn;
	}

	public void setThirdColumn(String thirdColumn) {
		this.thirdColumn = thirdColumn;
	}
}
