package common.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.util.CommonUtil;

public class ReportTool {
	/**
	 * 返回关于统计数量的满足报表的xml语句.
	 * @param dataList
	 * @return
	 */
	public static String getSimpleCountXML(List dataList,String title) {
		StringBuffer buffer = new StringBuffer(
				"<graph showNamesalPrecision='0'  showNames='1' caption='"+title+"'>");
		Iterator it = dataList.iterator();
		while (it.hasNext()) {
			ReportSet set = (ReportSet) it.next();
			buffer.append("<set name='" + set.getName() + "' value='"
					+ set.getCount() + "'/>");
		}
		buffer.append("</graph>");
		System.out.print(buffer);
		return buffer.toString();
	}
	
	/**
	 * 得到三维报表的xml串。
	 * 返回的对象是一个字符串数组.
	 * 第一个位置：统计group列的全部数据内容
	 * 第二个位置：统计的第三维度的全部数据内容
	 * 第三个位置：xml字符串.
	 * @param dataList
	 * @param title
	 * @return
	 */
	public static String[] getMultiSeriesReportXML(List<ReportMultiSeriesSet> dataList,String title) {
		StringBuffer buffer = new StringBuffer(
				"<graph showNamesalPrecision='0'  showNames='1' caption='"+title+"'>");
		Iterator<ReportMultiSeriesSet> it = dataList.iterator();
		List allGroupColumn = new ArrayList();
		List allThirdColumn = new ArrayList();
		Map allSumValue = new HashMap();
		//分析得到三列报表的数据内容，得到第一列，第二列的具体数目和内容,以及记录数据对应的第一列和第二列的位置.
		while (it.hasNext()) {
			ReportMultiSeriesSet set =   it.next();
			if(allGroupColumn.indexOf(set.getGroupColumn())==-1)
				allGroupColumn.add(set.getGroupColumn());
			
			if(allThirdColumn.indexOf(set.getThirdColumn())==-1)
				allThirdColumn.add(set.getThirdColumn());
			
			allSumValue.put(allGroupColumn.indexOf(set.getGroupColumn()) + ","
					+ allThirdColumn.indexOf(set.getThirdColumn()), set
					.getSumColumn());
		}
		//定义数组来存储查询订单的三列报表的全部数据.
		String[][] dataes = new String[allThirdColumn.size()][allGroupColumn.size()]; 
	 
		buffer.append("<categories>");
		for(int j=0;j<allGroupColumn.size();j++){
			buffer.append("<category name='"+allGroupColumn.get(j)+"'/>");
		}
		buffer.append("</categories>");
		ReportColor[] colors = ReportColor.values();
		for(int i=0;i<allThirdColumn.size();i++){
			buffer.append("<dataset seriesname='"+allThirdColumn.get(i)+"' showValue='1' color='"+colors[i*4].getColor()+"'>");
			for(int j=0;j<allGroupColumn.size();j++){
				buffer.append("<set value='"+CommonUtil.notBlank((String)allSumValue.get(j+","+i),"0")+"'/>");
			}
			buffer.append("</dataset>");
		}
		buffer.append("</graph>"); 
		String[] ans = new String[]{allGroupColumn.toString(),allThirdColumn.toString(),buffer.toString()};
		return ans;
	}
	
	/**
	 * 返回关于统计总数的满足报表的xml语句.
	 * @param dataList
	 * @return
	 */
	public static String getSimpleSumXML(List dataList,String title) {
		StringBuffer buffer = new StringBuffer(
				"<graph showNamesalPrecision='0'  showNames='1' caption='"+title+"'>");
		Iterator it = dataList.iterator();
		while (it.hasNext()) {
			ReportSet set = (ReportSet) it.next();
			buffer.append("<set name='" + set.getName() + "' value='"
					+ set.getSum() + "'/>");
		}
		buffer.append("</graph>");
		System.out.print(buffer);
		return buffer.toString();
	}
}
