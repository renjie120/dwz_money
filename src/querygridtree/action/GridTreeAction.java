package querygridtree.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import querygridtree.bo.QueryGridTreeBO;
import querygridtree.data.GridTreeVO;
import testGridTree.GridTreeUtil;

import com.opensymphony.xwork2.ModelDriven;

import dwz.present.BaseAction;

/**
 * 查询表格树示例.
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
	 * 初始化查询菜单栏的数据.
	 * @return
	 */
	public String initGridTree() {
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

	/**
	 * 懒加载显示子节点.
	 * @return
	 */
	public String lazyLoad() {   
		String parentId = request.getParameter("pId");
    	//GridTreeDao dao = new GridTreeDao();
		List list = new ArrayList();		
		try {
			/*
			//查询子节点集合
			list = dao.getListByParent(parentId);
			// 调用工具类的方法得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list);
			*/
			StringBuffer buf  =new StringBuffer();
			String page  ="--是"+parentId+"的孩子...";	
			buf.append("[");
			buf.append("{\"disid\":\"10"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"孩子10"+page+"\",\"isLeaf\":\"1\"},");
			buf.append(" {\"disid\":\"12"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"孩子12"+page+"\",\"isLeaf\":\"0\"},  ");	
			buf.append(" {\"disid\":\"13"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"孩子13"+page+"\",\"isLeaf\":\"0\"}, ");
			buf.append("{\"disid\":\"14"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"孩子14"+page+"\",\"isLeaf\":\"1\"},");
			buf.append(" {\"disid\":\"15"+parentId+"\",\"disparentId\":\""+parentId+"\",\"disname\":\"孩子15"+page+"\",\"isLeaf\":\"0\"} ");	
			buf.append("]");
			
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

	public QueryGridTreeBO getQueryGridTreeBO() {
		return queryGridTreeBO;
	}

	public void setQueryGridTreeBO(QueryGridTreeBO queryGridTreeBO) {
		this.queryGridTreeBO = queryGridTreeBO;
	}
}
