package money.report;

import java.util.Map;

import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;
import common.report.ReportStrGenerate2;
import dwz.present.BaseAction;

/**
 * 收支报表.
 * @author Administrator
 *
 */
public class MoneyReport extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String report() {
		return "report";
	}

	/**
	 * 按照类别，年份统计金额总数.
	 * 
	 * @return
	 */
	public String reportSumByTypeAndYear() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "year", "bigtype" }).sum("money")
				.colomns(new String[] { "year", "bigtype" })
				.where("big_money_type='2'").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("year") + "','" + objs.get("bigtype")
						+ "'," + objs.get("sum_MONEY") + " ]";
			}
		});
		System.out.println("reportSumByTypeAndYear==" + ans);
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 按照类别统计数量.
	 * 
	 * @return
	 */
	public String reportCountByType() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy("bigtype").count().colomns(new String[] { "bigtype" })
				.build().generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map strs) {
				return "['" + strs.get("bigtype") + "'," + strs.get("count1")
						+ " ]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	public String reportYingli() {
		// ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
		// .getBean("reportUtil");
		// String sql = new MyReport.Builder("money_detail_view")
		// .groupBy(new String[] { "year", "bigtype" }).sum("money")
		// .colomns(new String[] { "year", "bigtype" })
		// .where("big_money_type='2'").build().generateSql();
		// System.out.println("查询sql:" + sql);
		String ans = "[['盈利','08/01',10000],['盈利','08/02',14000],['盈利','08/03',20000],['盈利','08/04',15900]"
				+ ",['盈利','08/05',10000],['盈利','08/06',10000],['盈利','08/07',10000],['盈利','08/08',20000]"
				+ ",['盈利','08/09',10900],['盈利','08/10',40000],['亏损','08/01',-10500],['亏损','08/02',-12000]"
				+ ",['亏损','08/03',-14000],['亏损','08/04',-24000],['亏损','08/05',-10900],['亏损','08/06',-19000],"
				+ "['亏损','08/07',-16000],['亏损','08/08',-19000],['亏损','08/09',-20000],['亏损','08/10',-9000]]";
		System.out.println("reportSumByTypeAndYear==" + ans);
		writeToPage(response, ans);
		return null;
	}

	private String year;
	private String month;
	
	
	/**
	 * 按照类别，年份，月份统计金额总数.
	 * 
	 * @return
	 */
	public String reportSumByTypeAndYearAndMonth() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy(new String[] { "month", "bigtype" }).sum("money")
				.colomns(new String[] { "month", "bigtype" })
				.where("year='" + year + "' and big_money_type='2' ").build()
				.generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("month") +"'," + objs.get("tallytype")+"'," + objs.get("SUM_money")+ "]";
			}
		});
		writeToPage(response, ans);
		return null;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * 按照类别统计各个的总金额.
	 * 
	 * @return
	 */
	public String reportSumByType() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view")
				.groupBy("bigtype").sum("money").where(" big_money_type='2' ")
				.colomns(new String[] { "bigtype" }).build().generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("SUM_money") +"'," + objs.get("bigtype")+ "]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 统计功过记录的数据报表图.
	 * 
	 * @return
	 */
	public String reportGongguoStatis() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("gongguo_view")
				.groupBy(new String[] { "g_value" })
				.colomns(new String[] { "pname" }).sum("flag").count()
				.order("g_value").build().generateSql();
		System.out.println("查询sql:" + sql);
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['"
						+ objs.get("pname")
						+ "',"
						+ (Double.parseDouble(objs.get("SUM_flag") + "") - Double
								.parseDouble(objs.get("COUNT1") + "")) + ","
						+ (Double.parseDouble(objs.get("SUM_flag")  + "")) + "]";
			}
		});
		writeToPage(response, ans);
		return null;
	}
}
