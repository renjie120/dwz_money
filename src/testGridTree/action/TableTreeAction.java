package testGridTree.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import testGridTree.GridTreeUtil;
import dwz.present.BaseAction;

/**
 * 普通表格树:简化了处理的步骤,必须有两处要调用GridTreeUtil中的方法.
 * connect me:419723443@qq.com
 */
public class TableTreeAction extends BaseAction {
	// 每页行数
	private static int DEFAULT_PAGE_SIZE = 10; 
	public String execute() {
		//GridTreeDao dao = new GridTreeDao();
		List list = new ArrayList();
		// 结果行数
		int totalNum = 0;
		int[] rowStartEnd;
		int len = 0;
		try {
			/*
			//你的业务必须要做的事情:查询第一层的节点的总数!!
			totalNum = dao.getFirstLevelCount();			
			
			// 必须调用工具类的方法:计算起始行和终止行.
			rowStartEnd = GridTreeUtil.getStartAndEndInfo(request, totalNum,
					DEFAULT_PAGE_SIZE);			
			
			//你的业务必须要做的事情:查询显示第一页显示出来的节点以及子节点!
			list = dao.getCommonList(rowStartEnd[0], rowStartEnd[1]);
			
			// 必须调用工具类的方法:得到json字符串。
			String jsonStr = GridTreeUtil.getJsonStr(list, request);	
			*/
			 	
			
			StringBuffer buf  =new StringBuffer();
			//第一步:第一层的总行数.
			totalNum = 5;
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
			buf.append(" data:[{\"disid\":\"10\",\"disparentId\":\"\",\"disname\":\"湖北"+page+"\"},");
			buf.append(" {\"disid\":\"11\",\"disparentId\":\"\",\"disname\":\"北京"+page+"\"},      "); 
			buf.append(" {\"disid\":\"12\",\"disparentId\":\"\",\"disname\":\"天津"+page+"\"},      ");
			buf.append(" {\"disid\":\"4211\",\"disparentId\":\"10\",\"disname\":\"武汉"+page+"\"},"); 
			buf.append(" {\"disid\":\"4212\",\"disparentId\":\"10\",\"disname\":\"荆州"+page+"\"},");
			buf.append(" {\"disid\":\"4213\",\"disparentId\":\"10\",\"disname\":\"荆门"+page+"\"},  ");		
			buf.append(" {\"disid\":\"4214\",\"disparentId\":\"10\",\"disname\":\"黄冈"+page+"\"},  ");
			buf.append(" {\"disid\":\"4215\",\"disparentId\":\"10\",\"disname\":\"鄂州"+page+"\"},");
			buf.append(" {\"disid\":\"4216\",\"disparentId\":\"10\",\"disname\":\"宜昌"+page+"\"},  ");		
			buf.append(" {\"disid\":\"4217\",\"disparentId\":\"10\",\"disname\":\"襄樊"+page+"\"},  ");
			buf.append(" {\"disid\":\"4218\",\"disparentId\":\"10\",\"disname\":\"黄石"+page+"\"},");
			buf.append(" {\"disid\":\"4219\",\"disparentId\":\"10\",\"disname\":\"十堰"+page+"\"},  ");		
			buf.append(" {\"disid\":\"4220\",\"disparentId\":\"10\",\"disname\":\"孝感"+page+"\"},  ");
			buf.append(" {\"disid\":\"4211\",\"disparentId\":\"10\",\"disname\":\"湖北"+page+"\"},");
			buf.append(" {\"disid\":\"1101\",\"disparentId\":\"11\",\"disname\":\"北京"+page+"\"},  ");		
			buf.append(" {\"disid\":\"1201\",\"disparentId\":\"12\",\"disname\":\"天津"+page+"\"},  ");
			buf.append(" {\"disid\":\"1102\",\"disparentId\":\"11\",\"disname\":\"中关村"+page+"\"},  ");		
			buf.append(" {\"disid\":\"1202\",\"disparentId\":\"12\",\"disname\":\"天津开发区"+page+"\"}  ");
			buf.append("]}  ");

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