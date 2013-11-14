package testGridTree.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import testGridTree.GridTreeUtil;
import dwz.present.BaseAction;

/**
 * 演示懒加载表格树:第一次显示执行的action,也就是initLazy.
 * 用途是:只查询出来应该显示的第一层的节点.
 * connect me:419723443@qq.com
 */
public class InitTableTreeLazyAction extends BaseAction {
	// 每页行数
	private static int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 执行方法.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	  public String execute() {
		//GridTreeDao dao = new GridTreeDao();
		List list = new ArrayList();
		// 结果行数
		int totalNum = 0;
		int[] rowStartEnd;
		int len = 0;
		try {
			/*
			totalNum = dao.getFirstLevelCount(); 
			// 调用工具类的方法计算起始行和终止行（为前开和后开的）。
			rowStartEnd = GridTreeUtil.getStartAndEndInfo(request, totalNum,
					DEFAULT_PAGE_SIZE);
			list = dao.getList(rowStartEnd[0], rowStartEnd[1]);

			// 调用工具类的方法得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list, request);
			*/
			
			
			StringBuffer buf  =new StringBuffer();
			//第一步:第一层的总行数.
			totalNum = 10;
			//第二步:显示的起始行,和结束行.
			//在点击翻页按钮的时候,会传递gtstart参数到后台.
			rowStartEnd = GridTreeUtil.getStartAndEndInfo(request, totalNum,
					DEFAULT_PAGE_SIZE);	
			//第三步:查询list--demo省略.
			
			//第四步:形成json串.------下面是模拟的json
			int n = 1;
			if (request.getParameter("gtpage") != null)  
				n = Integer.parseInt(request.getParameter("gtpage"));
			String page  ="--来自第"+n+"页";	
			buf.append("{total:"+ request.getAttribute("gtcount") + ",page:"+ request.getAttribute("gtpage")+",");
			buf.append(" data:[{\"disid\":\"10\",\"disparentId\":\"\",\"disname\":\"湖北"+page+"\",\"isLeaf\":\"1\"},"); 
			buf.append(" {\"disid\":\"11\",\"disparentId\":\"\",\"disname\":\"鬼城康巴什"+page+"\",\"isLeaf\":\"0\"},      ");	
			buf.append(" {\"disid\":\"12\",\"disparentId\":\"\",\"disname\":\"天津"+page+"\",\"isLeaf\":\"1\"}]} ");

			String jsonStr = buf.toString();
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("json串:" + jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
}
