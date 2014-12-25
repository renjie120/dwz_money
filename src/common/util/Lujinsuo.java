package common.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Lujinsuo {
	public static double getCifang(double n, double m) {
		return Math.pow(n, m);
	}

	public static double getDengben(double money, double lilv, int month) {
		double 本金 = money;// 60万
		double 月利率 = CommonUtil.divide(lilv, 12, 3);
		int 期数 = month;// 20年
		double temp1 = getCifang((1 + 月利率), 期数);
		double temp2 = CommonUtil.multiply(本金, 月利率);
		double temp3 = getCifang((1 + 月利率), 期数) - 1;
		double temp4 = CommonUtil.multiply(temp2, temp1);
		return (CommonUtil.divide(temp4, temp3, 3));
	}

	public static void main(String[] aegs) {
		System.out.println(getDengben(150000, 0.084, 36));
		
		try {
			Document doc = Jsoup.connect("https://list.lufax.com/list/charts-gather?lufax_ref=http://www.lufax.com/").get();
			System.out.println(doc.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
