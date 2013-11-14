package testGridTree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 表格树工具类.
 * connect me:419723443@qq.com
 */
public class GridTreeUtil {
	protected static Log log = LogFactory.getLog("GridTreeUtil");

	/**
	 * 取得起始行和终止行.
	 * @param request 请求
	 * @param allCount 总行数
	 * @param pageSize 每页数目
	 * @return 整型数组
	 */
	public static int[] getStartAndEndInfo(HttpServletRequest request,
			int allCount, int pageSize) {
		//默认当前的页数是1
		int page = 1;
		//默认的分页行数就是传入的每页行数
		int limit = pageSize;
		//新的起始位置
		int newStart = 0;
		int newEnd = 0;
		//在点击翻页按钮的时候,会传递gtstart参数到后台.
		if (request.getParameter("gtpage") != null) {
			limit = Integer.parseInt(request.getParameter("gtlimit"));
			page = Integer.parseInt(request.getParameter("gtpage"));
		}
		//否则就说明是第一次进入页面进行的查询
		else {
			if (request.getParameter("gtlimit") != null)
				limit = Integer.parseInt(request.getParameter("gtlimit"));
		}
		request.setAttribute("gtlimit", limit);
		request.setAttribute("gtpage", page);
		request.setAttribute("gtcount", allCount);

		//如果是第一次显示,而且要显示分页栏,就说明只显示第一页之间的数据
		if (limit != -1) {
			return new int[] {
					(page - 1) * limit + 1,
					(page * limit + 1) > allCount + 1 ? allCount + 1 : (page
							* limit + 1) };
		}
		//说明是第一次显示页面,而且不用显示分页栏,就是要返回全部的数据.
		else {
			return new int[] { 1, allCount + 1 };
		}
	}

	public static void main(String[] a) {
		List list = new ArrayList();
		getJsonStr(list);
	}

	public static String getJsonStr(List list) {
		JSONArray jsonArr = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			jsonArr.add(list.get(i));
		}
		String ans = jsonArr.toString();
		return ans;
	}

	/**
	 * 形成json字符串,返回到前台即可.
	 * @param list
	 * @param rowCount
	 * @param pageSize
	 */
	public static String getJsonStr(List list, HttpServletRequest request) {
		StringBuffer buf = new StringBuffer();
		String idColumn = request.getParameter("idColumn");
		String parentColumn = request.getParameter("parentColumn");
		String analyzeAtServer = request.getParameter("analyze");
		if ("false".equals(analyzeAtServer)) {
			return "{total:" + request.getAttribute("gtcount") + ",page:"
					+ request.getAttribute("gtpage") + ",data:"
					+ getJsonStr(list) + "}";
		}
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			JSONObject idToNodeMap = new JSONObject();
			//节点id到节点的映射
			//Map idToNodeMap = new HashMap();
			//节点的父亲节点映射
			JSONObject idToParent = new JSONObject();
			//父亲节点到孩子节点的映射
			JSONObject parentToChildMap = new JSONObject();
			JSONArray parents = new JSONArray();
			//存储应该显示在第一级节点的节点id的集合
			JSONArray firstLevelNodes = new JSONArray();
			//用来存储那些不存在的父亲节点的id的集合
			JSONArray notExistsParent = new JSONArray();
			//循环对每个节点进行处理
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				jsonArr.add(obj);
				String nodeStr = JsonUtil.object2json(obj);
				String id;
				id = "_node" + BeanUtils.getProperty(obj, idColumn);
				String parent = "_node"
						+ BeanUtils.getProperty(obj, parentColumn);
				//添加id到节点json串的映射
				//idToNodeMap.put(id, nodeStr);
				idToNodeMap.put(id, nodeStr);
				idToParent.put(id, parent);
				if (!parents.contains(parent)) {
					parents.add(parent);
				}

				if (parentToChildMap.keySet().contains(parent)) {
					JSONArray childs = (JSONArray) parentToChildMap.get(parent);
					childs.add(id);
					parentToChildMap.put(parent, childs);
				} else {
					JSONArray childs = new JSONArray();
					childs.add(id);
					parentToChildMap.put(parent, childs);
				}
			}
			for (int i = 0; i < parents.size(); i++) {
				Object aParent = parents.get(i);
				//如果父亲节点不是在id映射中存在的节点就说明应该显示在第一层位置的节点的父亲们！！
				if (!idToNodeMap.keySet().contains(aParent)) {
					JSONArray childs = (JSONArray) parentToChildMap
							.get(aParent);
					firstLevelNodes.addAll(childs);
					//如果不在节点中，就说明这个父亲节点是不存在的，应该从parents中删除
					notExistsParent.add(aParent);
				}
			}
			//在父亲节点集合中删除不存在的那些节点
			for (int i = 0; i < notExistsParent.size(); i++) {
				parents.remove(notExistsParent.get(i));
			}

			long a = System.currentTimeMillis();
			jsonObj.put("allCount", request.getAttribute("gtcount"));
			jsonObj.put("pageSize", request.getAttribute("gtlimit"));
			jsonObj.put("currentPage", request.getAttribute("gtpage"));
			jsonObj.put("parents", parents);
			jsonObj.put("idToParent", idToParent);
			jsonObj.put("idToNodeMap", idToNodeMap);
			jsonObj.put("parentToChildMap", parentToChildMap);
			jsonObj.put("firstLevelNodes", firstLevelNodes);
			jsonObj.put("data", jsonArr);
			buf.append(jsonObj.toString());

			long b = System.currentTimeMillis();
		} catch (IllegalAccessException e) {
			log.error("GridTreeUtil--getJsonStr--1:", e);
		} catch (InvocationTargetException e) {
			log.error("GridTreeUtil--getJsonStr--1:", e);
		} catch (Exception e) {
			log.error("GridTreeUtil--getJsonStr--1:", e);
		}

		return buf.toString();
	}
}
