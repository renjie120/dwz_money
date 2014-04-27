package com.renjie120.stockmanagertest;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import money.stockmanage.NewStock;
import money.stockmanage.StockManagerVO;
import money.stockmanage.StockUtil;
import money.stockmanage.StockVO;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.LinkedCaseInsensitiveMap;

import common.MyJdbcTool;
import common.util.CommonUtil;

public class StockTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	public double parseD(String str) {
		return Double.parseDouble(str);
	}

	public void testCalcStock() {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) applicationContext
				.getBean("jdbcTool");
		List l = jdbcDaoTest
				.queryForList("select * from stock_deal where  stock_group   = 14 order by id ");
		LinkedCaseInsensitiveMap m = (LinkedCaseInsensitiveMap) l.get(0);
		String stock_name = m.get("stock_name") + "";
		StockUtil util = new StockUtil(stock_name);
		System.out.println("l.size()--" + l.size());
		for (int i = 0, j = l.size() - 1; i < j; i++) {
			LinkedCaseInsensitiveMap m2 = (LinkedCaseInsensitiveMap) l.get(i);
			String price = m2.get("price") + "";
			String number = m2.get("number") + "";
			String fee = m2.get("fee") + "";
			String dealType = m.get("deal_type") + "";
			System.out.println(m.get("id") + "," + m.get("stock_name"));
			StockVO s = new StockVO(Double.parseDouble(price),
					Double.parseDouble(number), Double.parseDouble(fee));
			util.addStock(s);
		}
		LinkedCaseInsensitiveMap m2 = (LinkedCaseInsensitiveMap) l.get(l.size() - 1);
		String price = m2.get("price") + "";
		String sellFee = m2.get("fee") + "";
		util.setEndValue(Double.parseDouble(price));
		util.setStockName(stock_name);
		util.setSellCost(Double.parseDouble(sellFee));
		System.out.println("保本价格：" + util.getBaoben());
		System.out.println("总共份额：" + util.getCount());
		System.out.println("实际成本：" + util.getRealChengben());
		System.out.println("股票：" + util.getStockName());
		// System.out.println(getHTMLByStockStr(stock));
	}

	private static String BTR = "<tr>";
	private static String BTABLE = "<html><body><table>";
	private static String ATABLE = "</table></body></html>";
	private static String ATR = "</tr>";
	private static String BTD = "<td>";
	private static String ATD = "</td>";

	private static String getStockInfoByStr(NewStock stock) {
		double endValue = stock.getEndValue();

		double baoben = 0D;

		int num = 20;

		double chengben = 0D;

		double[] bigValues = new double[num];

		double[] nextDayBigValues = new double[num];

		double[] nextDaySmallValues = new double[num];

		double[] smallValues = new double[num];

		double addStepValue = 0.0050000000000000001D;
		double removeStepValue = -0.0050000000000000001D;

		double zhangting = 0D;

		baoben = stock.getBaoben();

		zhangting = stock.getZhangting(baoben);

		// 基于成本价格的涨幅跌幅
		bigValues = getAnyValue(baoben, addStepValue, num);
		smallValues = getAnyValue(baoben, removeStepValue, num);

		// 下一日涨幅表.
		nextDayBigValues = getAnyValue(endValue, addStepValue, num);
		nextDaySmallValues = getAnyValue(endValue, removeStepValue, num);
		chengben = stock.getChengben();

		double addMoney = stock.getAddMoney(endValue);

		StringBuilder buf = new StringBuilder();

		buf.append(BTR).append(BTD);
		buf.append("总投资是：" + chengben);
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("总实际投资(除去交易费用)是：" + stock.getRealChengben());
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("总共购买的份额：" + stock.getCount());
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("总共的面值：" + stock.getRealMoney(endValue));
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("<span style='color:red'>").append("收益：" + addMoney)
				.append("</span>");
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("<span style='color:red'>")
				.append("收益率：" + stock.getAddValue(endValue)).append("</span>");
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("<span style='color:red'>").append("成本股价：" + baoben)
				.append("</span>");
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("当前价格距离10%收益的比率："
				+ CommonUtil.multiply(CommonUtil.subtract(CommonUtil.divide(
						zhangting, endValue, 3, RoundingMode.HALF_EVEN), 1D),
						100.0D) + "%");
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append("当前价格距离保本的比率："
				+ CommonUtil.multiply(CommonUtil.subtract(CommonUtil.divide(
						baoben, endValue, 3, RoundingMode.HALF_EVEN), 1D),
						100.0D) + "%");
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append(anyValuesTable(chengben, bigValues, addStepValue,
				"基于保本价涨幅表", "addValues", 20));

		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append(anyValuesTable(chengben, smallValues, removeStepValue,
				"基于保本价跌幅表", "removeValues", 20));
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append(anyValuesTable(chengben, nextDayBigValues, addStepValue,
				"下一日涨幅表", "nextDayBigValues", 20, addMoney));
		buf.append(ATD).append(ATR);
		buf.append(BTR).append(BTD);
		buf.append(anyValuesTable(chengben, nextDaySmallValues,
				removeStepValue, "下一日跌幅表", "nextDaySmallValues", 20, addMoney));
		buf.append(ATD).append(ATR);
		return buf.toString();
	}

	private static String anyValuesTable(double chengben, double[] values,
			double step, String tableTitle, String tableId, int everyRowTdNum) {
		return anyValuesTable(chengben, values, step, tableTitle, tableId,
				everyRowTdNum, 0D);
	}

	private static String anyValuesTable(double chengben, double[] values,
			double step, String tableTitle, String tableId, int everyRowTdNum,
			double addSomeValue) {
		StringBuffer ans = new StringBuffer();
		int rows = (int) Math.ceil(values.length / everyRowTdNum * 1D);
		ans.append("<table id='").append(tableId)
				.append("' style=' border:1px solid black; font-size:13px;'>");
		ans.append("<tr><td colspan='").append(values.length).append("'>")
				.append(tableTitle).append("</td></tr>");
		int tdNum = values.length;
		int temp = 0;
		for (int ii = 0; ii < rows; ++ii) {
			StringBuffer buf1 = new StringBuffer();
			StringBuffer buf2 = new StringBuffer();
			StringBuffer buf3 = new StringBuffer();
			buf1.append("<tr>");
			buf2.append("<tr>");
			buf3.append("<tr>");
			while ((temp < everyRowTdNum) && (tdNum > 0)) {
				int index = ii * everyRowTdNum + temp;

				double addValue = CommonUtil.multiply(step, index + 1);
				buf1.append(
						"<td style='height:22px;border-top:1px solid black;border-left:1px solid black;border-right:1px solid black;color=red'>")
						.append(CommonUtil.multiply(addValue, 100.0D))
						.append("%").append("</td>");
				buf2.append(
						"<td style='height:22px;border-left:1px solid black;border-right:1px solid black;'>")
						.append(values[index]).append("</td>");
				buf3.append(
						"<td style='height:22px;border-left:1px solid black;border-right:1px solid black;border-bottom:1px solid black;'>")
						.append(CommonUtil.add(
								CommonUtil.multiply(chengben, addValue),
								addSomeValue)).append("</td>");

				++temp;
				--tdNum;
			}

			temp = 0;
			buf1.append("</tr>");
			buf2.append("</tr>");
			buf3.append("</tr>");
			ans.append(buf1).append(buf2).append(buf3);
		}
		ans.append("</table>");
		return ans.toString();
	}

	private static double[] getAnyValue(double baoben, double range, int time) {
		double[] ans = new double[time];

		double addValue = 0D;
		for (int i = 0; i < time; ++i) {
			addValue = CommonUtil.add(1D, CommonUtil.multiply(range, i + 1));
			ans[i] = CommonUtil.multiply(addValue, baoben);
		}
		return ans;
	}

	private static String getHTMLByStockStr(NewStock stock) {
		double endValue = stock.getEndValue();

		double sellCost = stock.getSellCost();

		String stockName = stock.getStockName();

		String ansStr = getStockInfoByStr(stock);
		StringBuffer buf = new StringBuffer();

		buf.append(BTABLE).append(BTR).append(BTD).append("股票名称：" + stockName)
				.append(ATD).append(ATR).append(BTR).append(BTD)
				.append("<span style='color:red'>").append("当前股价：" + endValue)
				.append("</span>").append(ATD).append(ATR).append(BTR)
				.append(BTD).append("赎回费率：" + sellCost).append(ATD).append(ATR)
				.append(BTR).append(BTD).append(ansStr).append(ATD).append(ATR)
				.append(ATABLE);
		return buf.toString();
	}

	String getSumStockHtml(ArrayList stockList) {
		StringBuffer buf = new StringBuffer();

		double allChengben = 0D;

		double allAddMoney = 0D;

		double realAllAddMoney = 0D;

		double allAddMoneyValue = 0D;
		buf.append("<table>");

		int num = 0;
		if ((stockList != null) && (stockList.size() > 0)) {
			Iterator it = stockList.iterator();
			while (it.hasNext()) {
				NewStock stock = (NewStock) it.next();
				double addMoney = stock.getAddMoney(stock.getEndValue());
				double chengben = stock.getChengben();
				if (stock.getStockName().indexOf("_") != -1) {
					realAllAddMoney += addMoney;
					buf.append("<tr >");
					buf.append("<td style='height:22px;border:1px solid blacsk;'>");
					buf.append("<a href='").append(stock.getStockName())
							.append("_" + num).append(".html'>")
							.append(stock.getStockName()).append("(")
							.append(stock.getEndValue()).append(")")
							.append("</a>").append("<br>").append("</td>");
					buf.append("<td style='height:22px;border:1px solid black;'>");
					if (addMoney > 0D)
						buf.append("<span style='color:red'>盈利：")
								.append(addMoney).append("</span><br>");
					else
						buf.append("<span style='color:blue'>盈利：")
								.append(addMoney).append("</span><br>");

					buf.append("盈利率：")
							.append(CommonUtil.multiply(
									stock.getAddValue(stock.getEndValue()),
									100.0D)).append("%<br></td></tr>");
				} else {
					allChengben += chengben;
					allAddMoney += addMoney;

					realAllAddMoney += addMoney;
					buf.append("<tr >");
					buf.append("<td style='height:22px;border:1px solid blacsk;'>");
					buf.append("<a href='").append(stock.getStockName())
							.append("_" + num).append(".html'>")
							.append(stock.getStockName()).append("(")
							.append(stock.getEndValue()).append(")")
							.append("</a>").append("<br>")
							.append("成本：" + stock.getCount()).append("*")
							.append(stock.getBaoben()).append("</td>");
					buf.append("<td style='height:22px;border:1px solid black;'>");
					buf.append("总投资：").append(chengben).append("</br>");
					if (addMoney > 0D)
						buf.append("<span style='color:red'>盈利：")
								.append(addMoney).append("</span><br>");
					else
						buf.append("<span style='color:blue'>盈利：")
								.append(addMoney).append("</span><br>");

					buf.append("总市值：")
							.append(stock.getRealMoney(stock.getEndValue()))
							.append("<br>");
					buf.append("盈利率：")
							.append(CommonUtil.multiply(
									stock.getAddValue(stock.getEndValue()),
									100.0D)).append("%<br></td></tr>");
				}
				++num;
			}
		}
		buf.append("<tr><td colspan='2'>")
				.append("总投资：")
				.append(allChengben)
				.append(";总收益：")
				.append(allAddMoney)
				.append(";总收益率:")
				.append(CommonUtil.multiply(CommonUtil.divide(allAddMoney,
						allChengben, 4, RoundingMode.HALF_EVEN), 100.0D))
				.append("%").append("</td></tr>");
		buf.append("<tr><td colspan='2'>").append("总投资：").append(allChengben)
				.append(";累计总收益：").append(realAllAddMoney);
		if (realAllAddMoney < 0D) {
			buf.append(";赔本率:")
					.append(-CommonUtil.multiply(CommonUtil.divide(
							-realAllAddMoney,
							CommonUtil.add(allChengben, -realAllAddMoney), 4,
							RoundingMode.HALF_EVEN), 100.0D)).append("%");
			buf.append(";距离保本的比率:")
					.append(CommonUtil.multiply(CommonUtil.divide(
							-realAllAddMoney, allChengben, 4,
							RoundingMode.HALF_EVEN), 100.0D)).append("%");
		} else {
			buf.append(";累计总收益率:")
					.append(CommonUtil.multiply(CommonUtil.divide(
							realAllAddMoney, allChengben, 4,
							RoundingMode.HALF_EVEN), 100.0D)).append("%");
		}
		buf.append("</td></tr>");
		buf.append("</table>");
		return buf.toString();
	}

	public void atestImportStock() {
		String str = "600718:13.72,600,0.001;14.37,500,0.001;14.7,-300,0.001;15.17,-300,0.001:15.17,0.001$";
		String[] temp1 = str.split(":");
		// 股票号
		String stockNo = temp1[0];
		boolean hasSold = false;
		if (stockNo.startsWith("_")) {
			hasSold = true;
			stockNo = stockNo.replace("_", "");
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
		for (StockManagerVO v : vos)
			System.out.println(v);

		MyJdbcTool jdbcDaoTest = (MyJdbcTool) applicationContext
				.getBean("jdbcTool");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		def.setTimeout(50);
		String sqlsql = "insert into stock_deal(stock_no,price,number,fee,deal_type ) values(12,12,12,12,12)";

		try {
			jdbcDaoTest.exeSql(sqlsql);
		} catch (Exception ex) {
			System.out.println("出现异常了，回滚了！！");
		}
	}
}
