package common;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import common.base.SpringContextUtil;

import dwz.present.BaseAction;

/**
 * 直接执行sql的一个Action.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class MyJdbcAction extends BaseAction {
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String init() {
		return "list";
	}

	private String moneyStr;

	public String getMoneyStr() {
		return moneyStr;
	}

	public void setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
	}

	public String importMoney() throws Exception {
		return importPhoneMoney();
	}

	public String importGongguo() throws Exception {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		PlatformTransactionManager transactionManager = (DataSourceTransactionManager) SpringContextUtil
				.getBean("jdbcTm");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(50);
		TransactionStatus status = transactionManager.getTransaction(def);
		if (moneyStr != null) {
			String[] moneys = moneyStr.split("\\$;");
			for (String m : moneys) {
				String[] strs = m.split("\\$,");
				String sqlsql = "insert into gongguo_detail(g_value,gtime,flag)   "
						+ "select p.paramvalue,'"
						+ strs[0]
						+ "','"
						+ ("true".equals(strs[3]) ? "1" : "0")
						+ "' from params p where p.parameterType=9 and p.usevalue = '"
						+ strs[1] + "' ";
				try {
					jdbcDaoTest.exeSql(sqlsql);
				} catch (Exception ex) {
					transactionManager.rollback(status);
					System.out.println("出现异常了，回滚了！！");
					throw ex;
				}
			}
		}
		transactionManager.commit(status);
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String importDiary() throws Exception {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		PlatformTransactionManager transactionManager = (DataSourceTransactionManager) SpringContextUtil
				.getBean("jdbcTm");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(50);
		TransactionStatus status = transactionManager.getTransaction(def);
		String sqlsql = "";
		if (moneyStr != null) {
			String[] diaries = moneyStr.split("\\$;");
			for (String d : diaries) {
 				String[] strs = d.split("\\$,");
				if (strs.length > 5){
					//转换为新的日记类型对应的id
					String tp = "0".equals(strs[5])?"58":("1".equals(strs[5])?"59":"60");
					sqlsql = "insert into diary_detail(content,time,type) values("
							+ "'"
							+ strs[4]
							+ "','"
							+ strs[1]
							+ "','"
							+ tp
							+ "')";
				}
				else
					sqlsql = "insert into diary_detail(content,time,type) values("
							+ "'" + strs[4] + "','" + strs[1] + "',58)";
				try {
					jdbcDaoTest.exeSql(sqlsql);
				} catch (Exception ex) {
					transactionManager.rollback(status);
					System.out.println("出现异常了，回滚了！！");
					throw ex;
				}
			}
		}
		transactionManager.commit(status);
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String importPhoneDiary() throws Exception {
		return importDiary();
	}

	public String importPhoneGongguo() throws Exception {
		return importGongguo();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String importPhoneMoney() throws Exception {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		PlatformTransactionManager transactionManager = (DataSourceTransactionManager) SpringContextUtil
				.getBean("jdbcTm");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(50);
		TransactionStatus status = transactionManager.getTransaction(def);
		System.out.println("moneyStr---" + request.getAttribute("moneyStr"));
		if (moneyStr != null) {
			String[] moneys = moneyStr.split("\\$;");
			for (String m : moneys) {
				String[] strs = m.split("\\$,");
				String sqlsql = "insert into money_detail_t(money_time,money,money_type,money_desc )"
						+ " select '"
						+ strs[0]
						+ "',"
						+ strs[1]
						+ ",te.type_code,'"
						+ ("0".equals(strs[3]) ? "" : strs[3])
						+ "'"
						+ "  from tally_type_t te where te.tally_type_desc= '"
						+ strs[2] + "'";
				try {
					jdbcDaoTest.exeSql(sqlsql);
				} catch (Exception ex) {
					transactionManager.rollback(status);
					System.out.println("出现异常了，回滚了！！");
					throw ex;
				}
			}
		}
		transactionManager.commit(status);
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String exeSqlAction() throws Exception {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		PlatformTransactionManager transactionManager = (DataSourceTransactionManager) SpringContextUtil
				.getBean("jdbcTm");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(5);

		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			jdbcDaoTest.exeSql(sql);
		} catch (Exception ex) {
			transactionManager.rollback(status);
			System.out.println("出现异常了，回滚了！！");
			throw ex;
		}
		transactionManager.commit(status);
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String querySqlAction() throws Exception {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		PlatformTransactionManager transactionManager = (DataSourceTransactionManager) SpringContextUtil
				.getBean("jdbcTm");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(5);

		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			jdbcDaoTest.query(sql);
		} catch (Exception ex) {
			transactionManager.rollback(status);
			System.out.println("出现异常了，回滚了！！");
			throw ex;
		}
		transactionManager.commit(status);
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	private String toJsonArray(Map map) {
		List columnNames = (List) map.get("columnNames");
		List colType = (List) map.get("colType");
		List data = (List) map.get("data");
		StringBuffer buf = new StringBuffer();
		if (data.size() > 0) {
			int colsize = ((Object[]) data.get(0)).length;
			buf.append("[");

			buf.append("[");
			for (int i = 0; i < columnNames.size(); i++) {
				buf.append("'" + columnNames.get(i) + "(" + colType.get(i)
						+ ")'");
				if (i < data.size() - 1)
					buf.append(",");
			}
			buf.append("],");
			for (int i = 0; i < data.size(); i++) {
				Object[] objs = (Object[]) data.get(i);
				buf.append("[");
				for (int j = 0; j < colsize; j++) {
					buf.append("'" + objs[j] + "'");
					if (j < colsize - 1)
						buf.append(",");
				}
				buf.append("]");
				if (i < data.size() - 1)
					buf.append(",");
			}
			buf.append("]");
		}
		return buf.toString();
	}
}
