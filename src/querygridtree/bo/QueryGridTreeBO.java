package  querygridtree.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import querygridtree.data.GridTreeVO;
import querygridtree.data.QueryGridTreeVO;
import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.user.User;
import dwz.persistence.beans.UserVO;

public interface QueryGridTreeBO extends BusinessObjectManager{ 
	/**
	 * 增加元素.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public void add(QueryGridTreeVO queryGridTreeVO);
	/**
	 * 删除元素.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public void delete(QueryGridTreeVO queryGridTreeVO);
	
	/**
	 * 修改元素.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public void update(QueryGridTreeVO queryGridTreeVO);
	
	/**
	 * 返回查询的结果json串.
	 * @param QueryGridTreeVO
	 * @return
	 */
	public String query(QueryGridTreeVO queryGridTreeVO);
	
	/**
	 * 返回详细信息.
	 * @param id
	 * @return
	 */
	public QueryGridTreeVO queryById(String id);
	
	/**
	 * 返回分页查询sql.
	 * @param QueryGridTreeVO 查询条件
	 * @param pageSize 每页数
	 * @param page 页码
	 * @return
	 */
	public String queryPage(QueryGridTreeVO queryGridTreeVO,int pageSize,int page);
	
	/**
	 * 根据人员id查询项目列表.
	 * @param userId
	 * @param way 1:查询该人所在部门下面的项目;2:查询该人所在区域下面的全部部门的项目.
	 * @return
	 */
	public String queryProjectsByUserId(String userId,int way); 
	
	/**
	 * 查询登录人员的下属员工(含本人).
	 * @param userId 
	 * @return
	 */
	public String querySubUserByUserId(String userId); 
	
	/**
	 * 根据区域id返回其下面的部门.
	 * @param areaId
	 * @return
	 */
	public List queryDaparts(UserVO loginUser); 
	
	/**
	 * 根据人员id查询项目列表.
	 * @param userId
	 * @param way 1:查询该人所在部门下面的项目;2:查询该人所在区域下面的全部部门的项目.
	 * @return
	 */
	public List queryProjects(UserVO loginUser); 
	
	/**
	 * 查询全部的状态.
	 * @return
	 */
	public List queryStatus(); 
	
	/**
	 * 查询登录人员的下属员工(含本人).
	 * @param userId 
	 * @return
	 */
	public List querySubUser(UserVO loginUser); 
	 
	/**
	 * 查询部门下面的项目和人员.
	 * @param departId
	 * @return
	 */
	public String getProjectAndUserByDepart(String departId);   
	
	/**
	 * 查询部门下面的人员.
	 * @param departId
	 * @return
	 */
	public String queryUsersByDepartId(String departId); 
	
	/**
	 * 查询指定部门下面的指定项目的参与人员.
	 * @param departId
	 * @param projectId
	 * @return
	 */
	public String queryUsersByDepartAndProject(String departId,String projectId); 
	
	 /**
	 * 部门联动的方法.
	 * @param deaprtId
	 * @param user
	 * @return
	 */
	public String changeDepart(String departId,UserVO user) ;
	
	/**
	 * 项目联动的方法.
	 * @param deaprtId
	 * @param projectId
	 * @param user
	 * @return
	 */
	public String changeProject(String departId,String projectId,UserVO user) ;
 
	public String getFirstLevelFinal(GridTreeVO gridTreeVO ,HttpServletRequest request,int pageSize) ;
	 
	
	/**
	 * 有条件的查询表格树的第一层树节点.
	 * @param gridTreeVO
	 * @return
	 */
	public int countNewFirstLevel(GridTreeVO gridTreeVO) ;
	public String getSubTaskByParent(GridTreeVO gridTreeVO,String pId);
	
	/**
	 * 联动项目带出人员
	 * @param code
	 * @return
	 */
	public String getProjectByDepart(String code);
	
	/**
	 * 联动部门带出项目
	 * @param code
	 * @return
	 */
	public String getPeopleByProject(String code);
	
	/**
	 * 验证登陆,返回用户id.
	 * @param logingId
	 * @param pass
	 * @return
	 */
	public String userValidate(String logingId,String pass);
	
	/**
	 * 返回用户对象.
	 * @param userId
	 * @return
	 */
	public User getUser(String userId);
}
