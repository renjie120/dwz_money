package querygridtree.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import querygridtree.dao.QueryGridTreeDAO;
import querygridtree.data.GridTreeVO;
import querygridtree.data.QueryGridTreeVO;
import querygridtree.data.TaskVO;

import common.dao.PaginationSupport;
import common.util.CommonUtil;
import common.util.GridTreeUtil;

import dwz.framework.user.User;
import dwz.persistence.beans.SysUser;
import dwz.persistence.beans.UserVO;

public class QueryGridTreeBOImpl implements QueryGridTreeBO {
	public String userValidate(String logingId, String pass) {
		return queryGridTreeDAO.userValidate(logingId,pass);
	}

	private QueryGridTreeDAO queryGridTreeDAO; 

	public void add(QueryGridTreeVO queryGridTreeVO) {
		queryGridTreeDAO.add(queryGridTreeVO);
	}

	public QueryGridTreeDAO getQueryGridTreeDAO() {
		return queryGridTreeDAO;
	}

	public void setQueryGridTreeDAO(QueryGridTreeDAO queryGridTreeDAO) {
		this.queryGridTreeDAO = queryGridTreeDAO;
	}

	public void delete(QueryGridTreeVO queryGridTreeVO) {
		queryGridTreeDAO.del(queryGridTreeVO);
	}

	public String query(QueryGridTreeVO queryGridTreeVO) {
		return null;
	}

	public void update(QueryGridTreeVO queryGridTreeVO) {
		queryGridTreeDAO.upt(queryGridTreeVO);
	}

	public String queryPage(QueryGridTreeVO queryGridTreeVO, int pageSize,
			int page) {
		int startIndex = pageSize * (page - 1) + 1;
		PaginationSupport pg = queryGridTreeDAO.queryPage(queryGridTreeVO,
				pageSize, startIndex);
		return CommonUtil.getGridJsonStr(pg.getItems(), pg.getTotalCount(), pg
				.getCurrentPage());
	}

	public QueryGridTreeVO queryById(String id) {
		return queryGridTreeDAO.queryById(id);
	} 

	public List queryDaparts(UserVO loginUser) {
		if(loginUser!=null)
			return queryGridTreeDAO.queryDapartByUserId("" + loginUser.getUserId());
		else
			return queryGridTreeDAO.queryDapartByUserId("");
	}

	public List queryProjects(UserVO loginUser) {
		if(loginUser!=null)
			return queryGridTreeDAO.queryProjectsByUserId(""
					+ loginUser.getUserId(), 2);
		else
			return queryGridTreeDAO.queryProjectsByUserId("",3);
		
	}

	public List querySubUser(UserVO loginUser) {
		if(loginUser!=null)
			return queryGridTreeDAO
			.querySubUserByUserId("" + loginUser.getUserId());
		else
			return queryGridTreeDAO.querySubUserByUserId("");
		
	}

	public String queryDapartByAreaId(String areaId) {
		return changeListToString(queryGridTreeDAO.queryDapartByAreaId(areaId),true);
	}

	public String queryProjectsByDepartId(String departId) {
		return changeListToString(queryGridTreeDAO
				.queryProjectsByDepartId(departId),false);
	}

	public String queryProjectsByUserId(String userId, int way) {
		return changeListToString(queryGridTreeDAO.queryProjectsByUserId(userId,
				way),true);
	}

	public String querySubUserByUserId(String userId) {
		return changeListToString(queryGridTreeDAO.querySubUserByUserId(userId),false);
	}

	public String queryUsersByDepartAndProject(String departId, String projectId) {
		return changeListToString(queryGridTreeDAO.queryUsersByDepartAndProject(
				departId, projectId),true);
	}

	public String queryUsersByDepartId(String departId) {
		return changeListToString(queryGridTreeDAO.queryUsersByDepartId(departId),true);
	}

	public String queryUsersByProject(String projectId) {
		return changeListToString(queryGridTreeDAO.queryUsersByProject(projectId),true);
	}

	public List queryStatus() {
		return queryGridTreeDAO.queryStatus();
	}

	public String changeDepart(String departId, UserVO user) {
		// 如果部门为非空,就查询这个部门下面的项目和人员
		if (!"-1".equals(departId))
			return queryProjectsByDepartId(departId) + ","
					+ queryUsersByDepartId(departId);
		// 如果部门没有选择,就查询默认的用户和项目.
		else {
			return changeListToString(queryProjects(user),false) + ","
					+ changeListToString(querySubUser(user),false);
		}
	}

	public String changeProject(String departId, String projectId, UserVO user) {
		// 如果部门和项目都非空,就用两个条件查询满足条件的人员
		if (!"-1".equals(departId) && !"-1".equals(projectId)) {
			return queryUsersByDepartAndProject(departId, projectId);
		}
		// 如果部门为空,项目非空,就查询项目下面的人员
		else if ("-1".equals(departId) && !"-1".equals(projectId)) {
			return queryUsersByProject(projectId);
		}
		// 如果部门非空,项目为空,就查询部门下面的全部人员
		else if (!"-1".equals(departId) && "-1".equals(projectId)) {
			return queryUsersByDepartId(departId);
		}
		// 如果部门和项目都是空,就查询下级员工.
		else {
			return changeListToString(querySubUser(user),false);
		}
	} 

	public int countNewFirstLevel(GridTreeVO gridTreeVO) {
		List ans = queryGridTreeDAO.getFirstLevelParentPath(gridTreeVO);
		Set hashSet = new HashSet();
		if (ans != null && ans.size() > 0) {
			Iterator it = ans.iterator();
			// 下面得到第一层应该显示的树节点.
			while (it.hasNext()) {
				String str = ((Object[]) it.next())[0].toString();
				// 得到在第二层以及第一层的数据.例如",-1,1,"或者",-1,"这样的数据!根据分隔符的数量得到层次.
				// 但是只保存第一层的前缀到hashSet中,因为分页的根据就是第一层!而不是第二层.
				String[] strs = str.split(",");
				hashSet.add(strs[1]);
			}
		}
		return hashSet.size();
	}

	/**
	 * 得到满足查询条件的全部的parentPath
	 * 
	 * @param gridTreeVO
	 * @return
	 */
	private List getAllParentPath(GridTreeVO gridTreeVO) {
		return queryGridTreeDAO.getFirstLevelParentPath(gridTreeVO);
	}

	/**
	 * 分析parent和taskId的组成的list,得到各个taskId是否是parent 
	 * @param parentPathList
	 * @deprecated
	 */
	private Map getIsParentMap(List parentPathList) {
		// taskId和对应的是否父亲节点的映射关系,返回结果.
		Map ans = new HashMap();
		// 全部的parenPath去掉重复数据
		Set allParentPath = new HashSet();
		Iterator it = parentPathList.iterator();
		while (it.hasNext()) {
			Object[] objs = (Object[]) it.next();
			String _path = (String) objs[0];
			allParentPath.add(_path);
		}
		// 对全部的记录循环得到是否是父亲.
		it = parentPathList.iterator();
		while (it.hasNext()) {
			Object[] objs = (Object[]) it.next();
			String _path = (String) objs[0];
			String _id = ((BigDecimal) objs[1]).toString();
			ans.put(_id, isParentFilter(allParentPath, _path + _id + ","));
		}
		return ans;
	}

	/**
	 * 得到是否父亲.
	 * 
	 * @param allParentPath
	 * @param subParent
	 * @deprecated
	 * @return
	 */
	private String isParentFilter(Set allParentPath, String subParent) {
		Iterator it = allParentPath.iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			if (str.indexOf(subParent) != -1)
				return "1";
		}
		return "0";
	}

	public String getFirstLevelFinal(GridTreeVO gridTreeVO,
			HttpServletRequest request, int pageSize) {
		// 得到满足条件的结果集合的全部的parentPath!!很重要!数据不会很多.
		List allParentPath = getAllParentPath(gridTreeVO);
		// 全部的满足要求的taskVO
		List<TaskVO> allTask = new ArrayList<TaskVO>();
		Set firstLevelIds = new LinkedHashSet();
		if (allParentPath != null && allParentPath.size() > 0) {
			Iterator it = allParentPath.iterator();
			// 下面得到第一层应该显示的树节点.主要对parentPath进行处理.
			while (it.hasNext()) {
				Object[] temp = (Object[]) it.next();
				String parentPath = (String) temp[0];
				// 得到在第二层以及第一层的数据.例如",-1,1,"或者",-1,"这样的数据!根据分隔符的数量得到层次.
				// 但是只保存第一层的前缀到hashSet中,因为分页的根据就是第一层!而不是第二层.
				String[] strs = parentPath.split(",");
				firstLevelIds.add(strs[1]);
			}
		}
		Object[] firstLevelTaskIds = (Object[]) firstLevelIds.toArray();
		// 第一层节点的数目就是总行数.
		int totalNum = firstLevelTaskIds.length;

		// 调用工具类的方法计算起始行和终止行（为前开和后开的）。
		int[] rowStartEnd = GridTreeUtil.getStartAndEndInfo(request, totalNum,
				pageSize); 
		// 下面计算循环第一层的各个节点,得到子节点中符合要求的节点.
		for (int i = rowStartEnd[0]-1; i < rowStartEnd[1]-1; i++) {
			String taskId = (String) firstLevelTaskIds[i];
			// 得到当前节点
			List theNode = queryGridTreeDAO.getTaskById(taskId);
			TaskVO vo = new TaskVO((Object[]) theNode.get(0));
			String parentPath = vo.getTaskParentPath();
			String childPath = parentPath + taskId + ",";

			// 下面得到成本和工时
			String[] costAndHour = queryGridTreeDAO.getCostAndHour(taskId);
			// 工时
			vo.setAttention(costAndHour[1]);
			// 成本
			vo.setSubCost(costAndHour[0]);
			// 第一层这里的节点肯定是父亲节点!!因为是从allParentPath中经过处理了的.
			if (isParent(allParentPath, childPath))
				vo.setIsParent("1");
			else
				vo.setIsParent("0");
			// 添加当前节点.
			allTask.add(vo);
			// 得到第二层孩子节点.
			allTask.addAll(getSubTaskByParentFinal(taskId, gridTreeVO));
		}
		return "{total:" + request.getAttribute("gtcount") + ",page:"
				+ request.getAttribute("gtpage") + ",data:"
				+ GridTreeUtil.getTaskJsonStr(allTask) + "}";
	}

	/**
	 * 查询子节点的vo集合
	 * 
	 * @param pId
	 * @param gridTreeVO
	 * @return
	 */
	private List<TaskVO> getSubTaskByParentFinal(String pId,
			GridTreeVO gridTreeVO) {
		List<TaskVO> ans = new ArrayList<TaskVO>();
		// 得到儿子节点,以及孙子节点等等.包括独立的孙子节点的parentPath.list的成员是Object,不是Obejct数组!
		// list里面的对象是一个parentPath和taskId的组成的object[]
		List allSubParentPath = queryGridTreeDAO.getSubTaskByParent(pId,
				gridTreeVO);
		// 得到全部的儿子节点.注意没有按照查询条件查询!!
		List allSubTaskVOList = queryGridTreeDAO.getSubTaskByParent(pId);
		//Map isParentMap = getIsParentMap(allSubParentPath);
		Iterator it = allSubTaskVOList.iterator();
		while (it.hasNext()) {
			TaskVO vo = new TaskVO((Object[]) it.next());
			String taskId = vo.getTaskId();
			// 下面判断这个儿子节点是否应该是返回的对象.
			String parentPath = vo.getTaskParentPath();
			String childPath = parentPath + taskId + ",";
			// 下面通过存储过程得到成本和工时
			String[] costAndHour = queryGridTreeDAO.getCostAndHour(taskId);
			// 工时
			vo.setAttention(costAndHour[1]);
			// 成本
			vo.setSubCost(costAndHour[0]);
			// 判断是不是应该出现在儿子节点中!
			if (shouldReturn(allSubParentPath, vo)) {
				if (isParent(allSubParentPath, childPath)) {
					vo.setIsParent("1");
				} else {
					vo.setIsParent("0");
				}
				ans.add(vo);
			}
		}
		return ans;
	}

	private boolean isParent(List allSubParentPath, String parentPath) {
		Iterator it = allSubParentPath.iterator();
		// 循环找到了只要有满足条件的parentPath就返回true!
		while (it.hasNext()) {
			Object[] temp = (Object[]) it.next();
			String _path = (String) temp[0];
			// 如果含有路径在其中,返回true.
			if (_path.indexOf(parentPath) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 得到是否应该返回.
	 * 
	 * @param allSubParentPath
	 * @param parentPath
	 * @return
	 */
	private boolean shouldReturn(List allSubParentPath, TaskVO vo) {
		Iterator it = allSubParentPath.iterator();
		String taskId = vo.getTaskId();
		String parentPath = vo.getTaskParentPath();
		String childInPath = parentPath + taskId + ",";
		// 循环找到了只要有满足条件的parentPath就返回true!
		while (it.hasNext()) {
			Object[] temp = (Object[]) it.next();
			String _path = (String) temp[0];
			String _id = ((BigDecimal) temp[1]).toString();
			// 如果taskId相等,就肯定返回true
			if (taskId.equals(_id)) {
				return true;
			} else {
				// 如果含有路径在其中,也肯定返回true.
				if (_path.indexOf(childInPath) != -1) {
					return true;
				}
			}
		}
		return false;
	}

	public String getSubTaskByParent(GridTreeVO gridTreeVO, String id) {
		List<TaskVO> ans = getSubTaskByParentFinal(id, gridTreeVO);
		return GridTreeUtil.getTaskJsonStr(ans);
	}
	
	/**
	 * 
	 * @param l 要转换的集合.
	 * @param selectOption  是否有请选择.
	 * @return
	 */
	private String changeListToString(List l,boolean selectOption){ 
		StringBuffer buf = new StringBuffer(100); 
		buf.append("[");
		if(selectOption){
			buf.append("[\"-1\",\"请选择\"],");
		}
		if(l!=null){
			Iterator it  = l.iterator();
			while(it.hasNext()){
				Object[] o = (Object[])it.next();
				buf.append("[\"").append(o[0]).append("\",\"").append(o[1]).append("\"],");
			}
		}
		if(buf.lastIndexOf(",")!=-1)
		buf = buf.deleteCharAt(buf.lastIndexOf(","));
		buf.append("]"); 
		return buf.toString();
	}

	public String getPeopleByProject(String code) {
		List list = queryGridTreeDAO.getPeopleByProject(code); 
		return changeListToString(list,true);
	}

	public String getProjectByDepart(String code) {
		List list = queryGridTreeDAO.getProjectByDepart(code);
		return changeListToString(list,true);
	}

	public String getProjectAndUserByDepart(String departId) { 
		return "["+getProjectByDepart(departId)+","+queryUsersByDepartId(departId)+"]";
	}

	public User getUser(String userId) {
		SysUser user =  queryGridTreeDAO.getUser(userId);
		return null;
	}
}
