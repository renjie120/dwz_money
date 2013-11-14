package common.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import brightmoon.jdbc.DbTool;

public class MyStockUtil {
	private static final String SELL="1";
	private static final String BUY="0";
	public static void main(String[] args) {
		String file = "D:\\我的java工具包!!\\stock工具\\stock.txt";
		String[] content = CommonUtil.readFileToStrArr(file);
		try {
			for (int i = 0; i < content.length; i++) {
				console(content[i]);

			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}

	private static void console(String str) throws SQLException {
		String[] str1 = str.split(":");
		String stockName = str1[0].replace("_", "");
		String manyPrice = str1[1];
		String lastPrice = str1[2].replace("$", "");

		DbTool db = new DbTool();

		String[] allDeals = manyPrice.split(";");
		for (int i = 0; i < allDeals.length; i++) {
			String[] oneDeal = allDeals[i].split(",");
			List l = new ArrayList();
			l.add(stockName);
			l.add(oneDeal[0]);
			
			int operNum = Integer.parseInt(oneDeal[1]);
			//卖出
			if(operNum<0){
				l.add(SELL);
			}else{
				l.add(BUY);
			}
			l.add(DateTool.nowDateStr());
			l.add(oneDeal[2]);
			l.add(Math.abs(operNum));
			db.updateRecords("insert into stock_detail_t(stocksno,price,opertype,opertime,operfeel,opernum) values(?,?,?,?,?,?)",l);
		}
	}
}
