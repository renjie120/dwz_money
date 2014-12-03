package money.report;

import java.util.Map;

import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportStrGenerate;
import common.report.ReportStrGenerate2;

import dwz.present.BaseAction;

/**
 * 问题报表.
 * @author Administrator
 *
 */
public class QuestionReport extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String report() {
		return "report";
	}

	/**
	 * 根据问题状态进行统计报表
	 * 
	 * @return
	 */
	public String reportQuestionCountByStatus() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("question_v").groupBy("statusname")
				.count().colomns(new String[] { "statusname" }).build()
				.generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("statusname") + "',"
						+ objs.get("count1") + " ]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 根据问题类型统计报表.
	 * 
	 * @return
	 */
	public String reportQuestionCountByType() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("question_v").groupBy("typename")
				.count().colomns(new String[] { "typename" }).build()
				.generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate2() {
			@Override
			public String change(Map objs) {
				return "['" + objs.get("typename") + "'," + objs.get("count1")
						+ " ]";
			}

		});
		writeToPage(response, ans);
		return null;
	}

	/**
	 * 根据问题提问人统计报表.
	 * 
	 * @return
	 */
	public String reportQuestionCountByPerson() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("question_v").groupBy("submit")
				.count().colomns(new String[] { "submit" }).build()
				.generateSql();
		String ans = util.getReportStr(sql, new ReportStrGenerate() {
			@Override
			public String change(Object[] objs) {
				return "['" + objs[1] + "'," + objs[0] + " ]";
			}

		});
		writeToPage(response, ans);
		return null;
	}
}
