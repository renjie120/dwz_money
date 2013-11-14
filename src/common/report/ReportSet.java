package common.report;

/**
 * 用于形成最简单的两列报表的基础数据对象.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class ReportSet {
	private String name;
	private String sum;
	private String count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
