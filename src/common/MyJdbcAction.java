package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import money.stockmanage.StockManagerVO;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import common.base.SpringContextUtil;
import common.util.CommonUtil;

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
				if (strs.length > 5) {
					// 转换为新的日记类型对应的id
					String tp = "0".equals(strs[5]) ? "58" : ("1"
							.equals(strs[5]) ? "59" : "60");
					sqlsql = "insert into diary_detail(content,time,type) values("
							+ "'"
							+ strs[4]
							+ "','"
							+ strs[1]
							+ "','"
							+ tp
							+ "')";
				} else
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

	private double parseD(String str) {
		return Double.parseDouble(str);
	}

	/**
	 * 解析一行数据得到股票交易记录列表.
	 * 
	 * @param str
	 * @return
	 */
	public List<StockManagerVO> parseStr(String str) {
		String[] temp1 = str.split(":");
		// 股票号
		String stockNo = temp1[0];
		boolean hasSold = false;
		stockNo = stockNo.replace("\n", "");
		if (stockNo.startsWith("_")) {
			hasSold = true;
			stockNo = stockNo.substring(1);
		}
		// 交易记录
		String str2 = temp1[1];
		// 最后价格
		String str3 = temp1[2];
		String[] dealDetails = str2.split(";");
		List<StockManagerVO> vos = new ArrayList<StockManagerVO>();
		Double stockSum = 0.0;
		for (String s : dealDetails) {
			StockManagerVO v = new StockManagerVO();
			v.setStockNo(stockNo);
			String[] ss = s.split(",");
			v.setPrice(parseD(ss[0]));
			double d = parseD(ss[1]);
			v.setDealNumber(Math.abs(d));
			if (d > 0)
				v.setDealType(StockManagerVO.BUY);
			else
				v.setDealType(StockManagerVO.SELL);
			v.setFee(parseD(ss[2]));
			stockSum = CommonUtil.add(stockSum, d);
			vos.add(v);
		}
		if (hasSold) {
			StockManagerVO v = new StockManagerVO();
			v.setStockNo(stockNo);
			str3 = str3.replace("$", "");
			String[] s = str3.split(",");
			v.setPrice(parseD(s[0]));
			v.setDealNumber(stockSum);
			v.setFee(parseD(s[1]));
			v.setDealType(StockManagerVO.SELL);
			vos.add(v);
		}
		return vos;
	}

	public String importStockDeals() throws Exception {
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
			String[] stockStrs = moneyStr.split("\\$");
			List<StockManagerVO> allVo = new ArrayList<StockManagerVO>();
			for (String m : stockStrs) {
				allVo.addAll(parseStr(m));
			}
			System.out.println("解析得到交易数据："+allVo.size());
			for (StockManagerVO v : allVo) {
				String sqlsql = "insert into stock_deal(stock_no,price,number,fee,deal_type ) values("
						+ "  '"
						+ v.getStockNo()
						+ "',"
						+ v.getPrice()
						+ ","
						+ v.getDealNumber()
						+ ","
						+ v.getFee()
						+ ","
						+ v.getDealType() + ")";
				try {
					jdbcDaoTest.exeSql(sqlsql);
				} catch (Exception ex) {
					ex.printStackTrace();
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
