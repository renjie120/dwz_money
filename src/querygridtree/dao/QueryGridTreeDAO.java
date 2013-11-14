package querygridtree.dao;

import java.util.List;

import querygridtree.data.GridTreeVO;
import querygridtree.data.QueryGridTreeVO;
import querygridtree.data.TaskVO;

import common.dao.PaginationSupport;

import dwz.persistence.beans.SysUser;

public interface QueryGridTreeDAO {
	/**
	 * 验证登陆.
	 * @param logingId
	 * @param pass
	 * @return
	 */
	public String userValidate(String logingId,String pass);
	/**
	 * 增加元素.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public void add(QueryGridTreeVO queryGridTreeVO);

	/**
	 * 删除元素,返回受影响行数.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public void del(QueryGridTreeVO queryGridTreeVO);

	/**
	 * 修改元素,返回受影响行数.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public void upt(QueryGridTreeVO queryGridTreeVO);

	/**
	 * 查询全部的状态.
	 * @return
	 */
	public List queryStatus();

	/**
	 * 返回详细信息.
	 * @param id
	 * @return
	 */
	public QueryGridTreeVO queryById(String id);

	/**
	 * 返回查询的结果json串.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public List query(QueryGridTreeVO queryGridTreeVO);

	/**
	 * 根据区域id返回其下面的部门.
	 * @param areaId
	 * @return
	 */
	public List queryDapartByAreaId(String areaId);

	/**
	 * 根据人员所在区域下面的全部部门.
	 * @param areaId
	 * @return
	 */
	public List queryDapartByUserId(String userId);

	/**
	 * 根据人员id查询项目列表.
	 * @param userId
	 * @param way 1:查询该人所在部门下面的项目;2:查询该人所在区域下面的全部部门的项目.
	 * @return
	 */
	public List queryProjectsByUserId(String userId, int way);

	/**
	 * 查询登录人员的下属员工(含本人).
	 * @param userId 
	 * @return
	 */
	public List querySubUserByUserId(String userId);

	/**
	 * 查询部门下面的项目.
	 * @param departId
	 * @return
	 */
	public List queryProjectsByDepartId(String departId);
	
	/**
	 * 得到一个指定任务的成本和耗时.
	 * 第0位置是成本,第1位置是耗时.
	 * @param taskId
	 * @return
	 */
	public String[] getCostAndHour(String taskId);

	/**
	 * 查询部门下面的人员.
	 * @param departId
	 * @return
	 */
	public List queryUsersByDepartId(String departId);

	/**
	 * 查询指定部门下面的指定项目的参与人员.
	 * @param departId
	 * @param projectId
	 * @return
	 */
	public List queryUsersByDepartAndProject(String departId, String projectId);

	/**
	 * 查询指定项目下面的参与人员.
	 * @param projectId
	 * @return
	 */
	public List queryUsersByProject(String projectId);

	/**
	 * 分页查询.
	 * @param queryGridTreeVO
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public PaginationSupport queryPage(QueryGridTreeVO queryGridTreeVO,
			int pageSize, int startIndex);

	/**
	 * 查询懒加载显示的总行数,就是第一层.
	 * @param gridTreeVO
	 * @return
	 */
	public int countFirstLevel(GridTreeVO gridTreeVO);

	/**
	 * 根据父亲任务id查询子任务列表.
	 * @param pId
	 * @return
	 */
	public List getSubTaskByParent(String pId);

	/**
	 * 有条件的查询子任务列表.返回的是parentpath和taskid的匹配对.
	 * @param pId
	 * @param gridTreeVO
	 * @return
	 */
	public List getSubTaskByParent(String pId, GridTreeVO gridTreeVO);

	/**
	 * 判断节点是不是树叶.
	 * @param id
	 * @return
	 */
	public boolean isParent(String id);

	/**
	 * 有条件的查询表格树.到所有的数据里面找到满足条件的数据.列表的内容不是VO,而是所有的满足条件的task的parentPath!
	 * @param gridTreeVO
	 * @return
	 */
	public List getFirstLevelParentPath(GridTreeVO gridTreeVO);

	public List getNewFirstLevel(GridTreeVO gridTreeVO, int start, int end);

	public List getTaskById(String id);

	/**
	 * 查询指定父亲节点下面的满足条件的记录的总工时和总成本.
	 * @param vo
	 * @return
	 */
	public Object[] getFacthourAncCost(TaskVO vo,GridTreeVO gridTreeVO);
	
	/**
	 * 查询部门下面的项目.
	 * @param code
	 * @return
	 */
	public List getProjectByDepart(String code);
	
	/**
	 * 查询部门下面的全部人员.
	 * @param code
	 * @return
	 */
	public List getUserByDepart(String code);
	
	/**
	 * 联动部门带出项目
	 * @param code
	 * @return
	 */
	public List getPeopleByProject(String code);
	
	/**
	 * 返回用户对象.
	 * @param userId
	 * @return
	 */
	public SysUser getUser(String userId);
}
