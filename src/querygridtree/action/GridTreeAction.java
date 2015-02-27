package querygridtree.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import querygridtree.bo.QueryGridTreeBO;
import querygridtree.data.GridTreeVO;

import com.opensymphony.xwork2.ModelDriven;
import common.base.SpringContextUtil;
import common.report.MyReport;
import common.report.ReportDaoUtil;
import common.report.ReportDataGenerate2;

import dwz.present.BaseAction;

/**
 * 用来进行报表展示的类.
 * 
 * @author Administrator
 * 
 */
class MoneyReportVo {
	private String type;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String id;
	private String parentType;
	private String money;
	private String isLeaf; 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
}

/**
 * 查询表格树示例.
 * 
 * @author lsq
 * 
 */
public class GridTreeAction extends BaseAction implements
		ModelDriven<GridTreeVO> {

	// 每页行数
	private final Log logger = LogFactory.getLog(getClass());
	private static int DEFAULT_PAGE_SIZE = 1;
	private GridTreeVO gridTreeVO = new GridTreeVO();
	private QueryGridTreeBO queryGridTreeBO;

	public GridTreeVO getModel() {
		return gridTreeVO;
	}

	/**
	 * 查询第二级，根据年份进行下面的按照类别统计.
	 * @param year
	 * @return
	 */
	public List<MoneyReportVo> getOutByBigType(String year) {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("bigtypesno")
				.sum("money").where(" big_money_type='2' and year= "+year)
				.colomns(new String[] { "bigtypesno" }).build().generateSql();
		List<List<String>> result = util.getReportData(sql,
				new ReportDataGenerate2() {
					@Override
					public List<String> change(Map objs) {
						List<String> result = new ArrayList<String>();
						if (objs != null) {
							for (Object o : objs.keySet()) {
								result.add(objs.get(o) + "");
							}
						}
						return result;
					}

				});

		List<MoneyReportVo> r = new ArrayList<MoneyReportVo>();
		if (result != null) {
			for (List<String> s : result) {
				MoneyReportVo v = new MoneyReportVo();
				v.setIsLeaf("1");
				v.setMoney(s.get(0));// 金额
				v.setType(s.get(1));// 类型是年份
				v.setParentType(year);
				v.setId(year+"-"+s.get(1));//设置主键，要唯一
				r.add(v);
			}
		}
		return r;
	}
	/**
	 * 按年度统计支出.
	 * 
	 * @return
	 */
	public List<MoneyReportVo> getOutByYear() {
		ReportDaoUtil util = (ReportDaoUtil) SpringContextUtil
				.getBean("reportUtil");
		String sql = new MyReport.Builder("money_detail_view").groupBy("year")
				.sum("money").where(" big_money_type='2' ")
				.colomns(new String[] { "year" }).build().generateSql();
		List<List<String>> result = util.getReportData(sql,
				new ReportDataGenerate2() {
					@Override
					public List<String> change(Map objs) {
						List<String> result = new ArrayList<String>();
						if (objs != null) {
							for (Object o : objs.keySet()) {
								result.add(objs.get(o) + "");
							}
						}
						return result;
					}

				});

		List<MoneyReportVo> r = new ArrayList<MoneyReportVo>();
		if (result != null) {
			for (List<String> s : result) {
				MoneyReportVo v = new MoneyReportVo();
				v.setIsLeaf("1");
				v.setMoney(s.get(0));// 金额
				v.setType(s.get(1));// 类型是年份
				v.setParentType("");
				v.setId(s.get(1));
				r.add(v);
			}
		}
		return r;
	}

	/**
	 * 根据金额报表数据返回对应的json数据.
	 * 
	 * @param list
	 * @param isRoot
	 * @return
	 */
	public static String getJsonStr(List<MoneyReportVo> list, boolean isRoot) {
		StringBuilder buf = new StringBuilder();
		if (isRoot) {
			buf.append("{total:" + list.size() + ",page:1,");
			buf.append(" data:[");
			for(MoneyReportVo v :list){
				buf.append("{\"disid\":\""+v.getId()+"\",\"showId\":\""+v.getType()+"\",\"disparentId\":\""+v.getParentType()+"\",\"disname\":\""+v.getMoney()+"\",\"isLeaf\":\""+v.getIsLeaf()+"\"},");
			}
			String temp =  buf.substring(0, buf.length()-1);
			return temp+"]} "; 
		}else{
			buf.append(" [");
			for(MoneyReportVo v :list){
				buf.append("{\"disid\":\""+v.getId()+"\",\"showId\":\""+v.getType()+"\",\"disparentId\":\""+v.getParentType()+"\",\"disname\":\""+v.getMoney()+"\",\"isLeaf\":\""+v.getIsLeaf()+"\"},");
			}
			String temp =  buf.substring(0, buf.length()-1);
			return temp+"] "; 
		} 
	}

	/**
	 * 初始化查询菜单栏的数据.
	 * 
	 * @return
	 */
	public String initGridTree() {
		List list = new ArrayList();
		// 结果行数
		int totalNum = 0;
		int[] rowStartEnd;
		int len = 0; 
		try {
			List<MoneyReportVo> datas = getOutByYear();
			totalNum = datas.size();
			
			// 调用工具类的方法得到json字符串。
			String jsonStr =  getJsonStr(datas,true); 
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("json串:" + jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 懒加载显示子节点.
	 * 
	 * @return
	 */
	public String lazyLoad() {
		String parentId = request.getParameter("pId"); 
		List list = new ArrayList();
		try {  
			List<MoneyReportVo>  result = getOutByBigType(parentId);
			String jsonStr = getJsonStr(result,false);
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("懒加载子串:" + jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public QueryGridTreeBO getQueryGridTreeBO() {
		return queryGridTreeBO;
	}

	public void setQueryGridTreeBO(QueryGridTreeBO queryGridTreeBO) {
		this.queryGridTreeBO = queryGridTreeBO;
	}
}
