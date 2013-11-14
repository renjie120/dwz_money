package testGridTree.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dwz.present.BaseAction;
 
public class TableTreeLazyPagingAction  extends BaseAction   { 
	 // 每页行数
    private static int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 懒加载的表格树示例代码.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public String execute() {
	  	String parentId = request.getParameter("pId");
    	//GridTreeDao dao = new GridTreeDao();
		List list = new ArrayList();
		int totalNum = 0;
		int[] rowStartEnd;
		try {
			/*totalNum = dao.getListCountByParent(parentId);
			rowStartEnd = GridTreeUtil.getStartAndEndInfo(request, totalNum,
					DEFAULT_PAGE_SIZE);
			list = dao.getListByParent(parentId,rowStartEnd[0],rowStartEnd[1]);
			// 调用工具类的方法得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list,request);
			*/
			
			StringBuffer buf  =new StringBuffer();
			int n = 1;
			 totalNum = 10; 
			 int currentPage = 1;
			if (request.getParameter("gtpage") != null)  
				n = Integer.parseInt(request.getParameter("gtpage"));
			String page  ="--来自第"+n+"页";	
			buf.append("{total:"+ totalNum + ",page:"+ n+",");
			buf.append(" data:[{\"disid\":\"10"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"湖北"+page+"\",\"isLeaf\":\"1\"},"); 
			buf.append(" {\"disid\":\"11"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"鬼城康巴什"+page+"\",\"isLeaf\":\"0\"},      ");	
			buf.append(" {\"disid\":\"12"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"天津"+page+"\",\"isLeaf\":\"1\"}]} "); 
			String jsonStr = buf.toString();
			response.setContentType("text/html; charset=UTF-8");
			System.out.println("懒加载子串:"+jsonStr);
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null; 
	} 
}

