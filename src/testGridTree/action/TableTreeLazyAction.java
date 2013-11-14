package testGridTree.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dwz.present.BaseAction;

/**
 * 懒加载表格树演示:查询指定的父节点下面的子节点.
 * 在点击节点前面的+号的时候,将提交到这个action处理.
 * connect me:419723443@qq.com
 */
public class TableTreeLazyAction  extends BaseAction  { 
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
}
