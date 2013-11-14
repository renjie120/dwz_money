package querygridtree.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import querygridtree.data.GridTreeVO;
import querygridtree.data.QueryGridTreeVO;
import querygridtree.data.TaskVO;
import brightmoon.jdbc.DBPoolManager;
import brightmoon.jdbc.DataHandler;
import brightmoon.jdbc.DbTool;

import common.dao.GenericDao;
import common.dao.PaginationSupport;
import common.util.CommonUtil;

import dwz.persistence.beans.SysUser;

public class QueryGridTreeDAOImpl extends GenericDao<QueryGridTreeVO, Integer>
		implements QueryGridTreeDAO {

	public void add(QueryGridTreeVO queryGridTreeVO) {
		save(queryGridTreeVO);
	}

	public void del(QueryGridTreeVO queryGridTreeVO) {

	}

	public List query(QueryGridTreeVO queryGridTreeVO) {
		return null;
	}

	public void upt(QueryGridTreeVO queryGridTreeVO) {
		update(queryGridTreeVO);
	}

	public PaginationSupport queryPage(QueryGridTreeVO queryGridTreeVO,
			int pageSize, int startIndex) {
		return null;
	}

	public QueryGridTreeVO queryById(String id) {
		return get(new Integer(id));
	}

	public List queryDapartByAreaId(String areaId) {
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("SELECT Department,DEPART_NAME FROM  DEPARTMENT WHERE AREA_ID = :AREA_ID");
		query.setParameter("AREA_ID", areaId);
		return query.list();
	}

	public List queryProjectsByDepartId(String departId) {
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("SELECT P.PROJ_ID, P.PROJ_NAME FROM PROJECT P WHERE P.Department = :DEPARTID");
		query.setParameter("DEPARTID", departId);
		return query.list();
	}

	public List queryProjectsByUserId(String userId, int way) {
		DbTool db = new DbTool();
		List result = db.queryList("SELECT PROJ_ID,PROJ_NAME FROM PROJECT",
				new DataHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Object[] obj = new Object[2];
						obj[0] = rs.getObject(1);
						obj[1] = rs.getObject(2);
						addRecord(obj);
					}
				});
		result.add(0,new Object[]{"-1","请选择"});
		return result;
		// return query.list();
	}

	public List querySubUserByUserId(String userId) {
		DbTool db = new DbTool();
		List result = db.queryList("SELECT USERID,username FROM USERS",
				new DataHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Object[] obj = new Object[2];
						obj[0] = rs.getObject(1);
						obj[1] = rs.getObject(2);
						addRecord(obj);
					}
				}); 
		result.add(0,new Object[]{"-1","请选择"});
		return result;
	}

	public List queryUsersByDepartAndProject(String departId, String projectId) { 
		DbTool db = new DbTool();
		List arg = new ArrayList();
		arg.add(projectId);
		arg.add(departId);
		List result =null;
		try {
			result = db
					.queryList(
							"SELECT U.USERID, U.USERNAME  FROM USERS U, PROJECT_STAFFS S, PROJECT P WHERE U.USERID = S.USERID  "
						+ " AND S.PROJ_ID = P.PROJ_ID   AND P.PROJ_ID = ?   AND P.Depart_Id = ?",arg,
							new DataHandler() {
								@Override
								public void processRow(ResultSet rs)
										throws SQLException {
									String[] obj = new String[2];
									obj[0] = rs.getString(1);
									obj[1] = rs.getString(2);
									addRecord(obj);
								}
							});
		} catch (SQLException e) { 
			e.printStackTrace();
		}  
		return result;
	}

	public List queryUsersByDepartId(String departId) {
		DbTool db = new DbTool();
		List arg = new ArrayList();
		arg.add(departId);
		List result =null;
		try {
			result = db
					.queryList(
							"SELECT T.USERID,T.USERNAME FROM USERS T WHERE T.DEPARTMENT  = ?",arg,
							new DataHandler() {
								@Override
								public void processRow(ResultSet rs)
										throws SQLException {
									String[] obj = new String[2];
									obj[0] = rs.getString(1);
									obj[1] = rs.getString(2);
									addRecord(obj);
								}
							});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return result;
	}

	public List queryUsersByProject(String projectId) {
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("SELECT U.USERID, U.USERNAME  FROM USERS U, PROJECT_STAFFS S WHERE U.USERID = S.USERID   AND S.PROJ_ID = :PROJECTID");
		query.setParameter("PROJECTID", projectId);
		return query.list();
	}

	public List queryDapartByUserId(String userId) {
		DbTool db = new DbTool();
		List result = db
				.queryList(
						"SELECT D2.Depart_Id, D2.DEPART_NAME  FROM DEPARTMENT D2 order by d2.depart_id",
						new DataHandler() {
							@Override
							public void processRow(ResultSet rs)
									throws SQLException {
								String[] obj = new String[2];
								obj[0] = rs.getString(1);
								obj[1] = rs.getString(2);
								addRecord(obj);
							}
						}); 
		result.add(0,new Object[]{"-1","请选择"});
		return result;
	}

	public List queryStatus() { 
		return null;
	}

	public int countFirstLevel(GridTreeVO gridTreeVO) {
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("SELECT COUNT(1) FROM TASK WHERE PARENT_TASK IS NULL ");
		List ans = query.list();
		if (ans != null && ans.size() > 0)
			return Integer.parseInt(ans.get(0).toString());
		return 0;
	}

	/**
	 * 根据父亲节点id查询子任务的id.
	 */
	public List getSubTaskByParent(String pId) { 
		String sql = "SELECT  TASKID,  DESCRIPTION,  PROJ_NAME,  NODENAME,"
			+ "  encharge,  APPROVED,  WREMARK, planstart, "
			+ "PLANEND,  FACTHOUR,  SUBJOIN_COST,  TASKKEYWORD, "
			+ " PARENT_TASK,   SUB_NAME, PARENTPATH,DEPARTNAME FROM all_task_info t where  T.PARENT_TASK = ?";
		List arg = new ArrayList();
		arg.add(pId);
		DbTool db = new DbTool();
		List result = null;
		try {
			result = db.queryList(sql, arg, new DataHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					Object[] obj = new Object[16];
					obj[0] = rs.getObject(1);
					obj[1] = rs.getObject(2);
					obj[2] = rs.getObject(3);
					obj[3] = rs.getObject(4);
					obj[4] = rs.getObject(5);
					obj[5] = rs.getObject(6);
					obj[6] = rs.getObject(7);
					obj[7] = rs.getObject(8);
					obj[8] = rs.getObject(9);
					obj[9] = rs.getObject(10);
					obj[10] = rs.getObject(11);
					obj[11] = rs.getObject(12);
					obj[12] = rs.getObject(13);
					obj[13] = rs.getObject(14);
					obj[14] = rs.getObject(15);
					obj[15] = rs.getObject(16);
					addRecord(obj);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据任务id查询任务信息.
	 * 
	 * @param id
	 * @return
	 */
	public List getTaskById(String id) {
		String sql = "SELECT  TASKID,  DESCRIPTION,  PROJ_NAME,  NODENAME,"
				+ "  encharge,  Approved,  WREMARK, planstart, "
				+ "PLANEND,  FACTHOUR,  SUBJOIN_COST,  TASKKEYWORD, "
				+ " PARENT_TASK,   SUB_NAME, PARENTPATH,DEPARTNAME FROM all_task_info t where  T.TASKID = ?";
		List arg = new ArrayList();
		arg.add(id);
		DbTool db = new DbTool();
		List result = null;
		try {
			result = db.queryList(sql, arg, new DataHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					Object[] obj = new Object[16];
					obj[0] = rs.getObject(1);
					obj[1] = rs.getObject(2);
					obj[2] = rs.getObject(3);
					obj[3] = rs.getObject(4);
					obj[4] = rs.getObject(5);
					obj[5] = rs.getObject(6);
					obj[6] = rs.getObject(7);
					obj[7] = rs.getObject(8);
					obj[8] = rs.getObject(9);
					obj[9] = rs.getObject(10);
					obj[10] = rs.getObject(11);
					obj[11] = rs.getObject(12);
					obj[12] = rs.getObject(13);
					obj[13] = rs.getObject(14);
					obj[14] = rs.getObject(15);
					obj[15] = rs.getObject(16);
					addRecord(obj);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 节点是否是叶子节点,即没有孩子.
	 * 
	 * @param id
	 * @return
	 */
	public boolean isParent(String id) { 
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("SELECT 1  FROM DUAL T WHERE EXISTS (SELECT 1 FROM TASK T WHERE T.PARENT_TASK = :ID)");
		query.setParameter("ID", id);
		List l = query.list();
		if (l != null && l.size() > 0) {
			return true;
		}
		return false;
	}

	private String getWhereSql(GridTreeVO queryGridTreeVO) {
		StringBuffer buf = new StringBuffer();
		// 部门
		if (!CommonUtil.isBlank(queryGridTreeVO.getDeptIds())
				&& !queryGridTreeVO.getDeptIds().equals("-1")) {
			buf.append(" AND VO.Depart_id  = ? ");
		}
		// 项目
		if (!CommonUtil.isBlank(queryGridTreeVO.getProjects())
				&& !queryGridTreeVO.getProjects().equals("-1")) {
			buf.append(" AND VO.PROJ_ID = ? ");
		}
		// 用户.
		if (!CommonUtil.isBlank(queryGridTreeVO.getUsers())
				&& !queryGridTreeVO.getUsers().equals("-1")) {
			buf.append(" AND VO.USERID  = ? ");
		}
		// 开始时间非空,结束时间为空
		if (!CommonUtil.isBlank(queryGridTreeVO.getStartTime())
				&& CommonUtil.isBlank(queryGridTreeVO.getEndTime())) {
			buf.append(" AND VO.PLANEND >= TO_DATE(?,'YYYY-MM-DD')  ");
		}
		// 结束时间非空,开始时间为空
		if (!CommonUtil.isBlank(queryGridTreeVO.getEndTime())
				&& CommonUtil.isBlank(queryGridTreeVO.getStartTime())) {
			buf.append(" AND VO.PALNSTART < TO_DATE(?,'YYYY-MM-DD') ");
		}
		// 开始结束时间都非空
		if (!CommonUtil.isBlank(queryGridTreeVO.getEndTime())
				&& !CommonUtil.isBlank(queryGridTreeVO.getStartTime())) {
			buf
					.append(" AND ((VO.PALNSTART >= TO_DATE(?,'YYYY-MM-DD') AND VO.PALNSTART<=TO_DATE(?,'YYYY-MM-DD')) "
							+ "OR (VO.PLANEND >= TO_DATE(?,'YYYY-MM-DD') AND VO.PLANEND<=TO_DATE(?,'YYYY-MM-DD'))"
							+ "OR (VO.PALNSTART >= TO_DATE(?,'YYYY-MM-DD') AND VO.PLANEND<=TO_DATE(?,'YYYY-MM-DD'))"
							+ "OR (VO.PALNSTART <= TO_DATE(?,'YYYY-MM-DD') AND VO.PLANEND>=TO_DATE(?,'YYYY-MM-DD'))) ");
		}
		// 状态
		if (!CommonUtil.isBlank(queryGridTreeVO.getStatus())
				&& !queryGridTreeVO.getStatus().equals("-1")) {
			buf.append(" AND VO.TASK_STATUS LIKE ? ");
		}
		return buf.toString();
	}

	private void setParameter(Query query, GridTreeVO queryGridTreeVO) {
		// 部门
		if (!CommonUtil.isBlank(queryGridTreeVO.getDeptIds())
				&& !queryGridTreeVO.getDeptIds().equals("-1")) {
			query.setParameter("DEPTID", queryGridTreeVO.getDeptIds());
		}
		// 项目
		if (!CommonUtil.isBlank(queryGridTreeVO.getProjects())
				&& !queryGridTreeVO.getProjects().equals("-1")) {
			query.setParameter("PRJID", queryGridTreeVO.getProjects());
		}
		// 用户.
		if (!CommonUtil.isBlank(queryGridTreeVO.getUsers())
				&& !queryGridTreeVO.getUsers().equals("-1")) {
			query.setParameter("USER", queryGridTreeVO.getUsers());
		}
		// 开始时间非空,结束时间为空
		if (!CommonUtil.isBlank(queryGridTreeVO.getStartTime())) {
			query.setParameter("STARTTIME", queryGridTreeVO.getStartTime());
		}
		// 结束时间非空,开始时间为空
		if (!CommonUtil.isBlank(queryGridTreeVO.getEndTime())) {
			query.setParameter("ENDTIME", queryGridTreeVO.getEndTime());
		}
		// 状态
		if (!CommonUtil.isBlank(queryGridTreeVO.getStatus())
				&& !queryGridTreeVO.getStatus().equals("-1")) {
			query.setParameter("STATUS", queryGridTreeVO.getStatus());
		}
	}

	private List getArgList(GridTreeVO queryGridTreeVO) {
		List argList = new ArrayList();
		// 部门
		if (!CommonUtil.isBlank(queryGridTreeVO.getDeptIds())
				&& !queryGridTreeVO.getDeptIds().equals("-1")) {
			argList.add(queryGridTreeVO.getDeptIds());
		}
		// 项目
		if (!CommonUtil.isBlank(queryGridTreeVO.getProjects())
				&& !queryGridTreeVO.getProjects().equals("-1")) {
			argList.add(queryGridTreeVO.getProjects());
		}
		// 用户.
		if (!CommonUtil.isBlank(queryGridTreeVO.getUsers())
				&& !queryGridTreeVO.getUsers().equals("-1")) {
			argList.add(queryGridTreeVO.getUsers());
		}
		// 开始时间非空,结束时间为空
		if (!CommonUtil.isBlank(queryGridTreeVO.getStartTime())) {
			argList.add(queryGridTreeVO.getStartTime());
		}
		// 结束时间非空,开始时间为空
		if (!CommonUtil.isBlank(queryGridTreeVO.getEndTime())) {
			argList.add(queryGridTreeVO.getEndTime());
		}
		// 开始结束时间都非空
		if (!CommonUtil.isBlank(queryGridTreeVO.getEndTime())
				&& !CommonUtil.isBlank(queryGridTreeVO.getStartTime())) {
			argList.add(queryGridTreeVO.getStartTime());
			argList.add(queryGridTreeVO.getEndTime());
			argList.add(queryGridTreeVO.getStartTime());
			argList.add(queryGridTreeVO.getEndTime());
			argList.add(queryGridTreeVO.getStartTime());
			argList.add(queryGridTreeVO.getEndTime());
			argList.add(queryGridTreeVO.getStartTime());
			argList.add(queryGridTreeVO.getEndTime());
		}
		// 状态
		if (!CommonUtil.isBlank(queryGridTreeVO.getStatus())
				&& !queryGridTreeVO.getStatus().equals("-1")) {
			argList.add(queryGridTreeVO.getStatus());
		}
		return argList;
	}

	/**
	 * 按照前台的条件查询表格树.
	 */
	public List getFirstLevelParentPath(GridTreeVO queryGridTreeVO) {
		DbTool db = new DbTool();
		StringBuffer getAllNodes = new StringBuffer(
				"SELECT PARENTPATH,TASKID FROM ALL_TASK_VIEW VO WHERE 1=1 ")
				.append(getWhereSql(queryGridTreeVO));
		String sql = getAllNodes.toString();
		List argList = getArgList(queryGridTreeVO);
		List result = null;
		try {
			result = db.queryList(sql, argList, new DataHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					Object[] obj = new Object[2];
					obj[0] = rs.getObject(1);
					obj[1] = rs.getObject(2);
					addRecord(obj);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List getNewFirstLevel(GridTreeVO gridTreeVO, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getSubTaskByParent(String pid, GridTreeVO queryGridTreeVO) {
		// 下面有一个distinct,考虑到可能有一个任务对应多个人的情况就加上这里的唯一.
		// 但是又说没有这种情况.有没有的这种情况的直接效果就是查看ALL_TASK_VIEW是否和task的数目一样,一样的话就下面不要用distinct了.
		StringBuffer getAllNodes = new StringBuffer(
				"SELECT VO.PARENTPATH,VO.TASKID  FROM ALL_TASK_VIEW VO WHERE VO.PARENTPATH LIKE GET_LIKEPARENTPATH (?) ")
				.append(getWhereSql(queryGridTreeVO));
		DbTool db = new DbTool();
		String sql = getAllNodes.toString();
		List argList = new ArrayList();
		argList.add(pid);
		List argList2 = getArgList(queryGridTreeVO);
		argList.addAll(argList2);
		List result = null;
		try {
			result = db.queryList(sql, argList, new DataHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					Object[] obj = new Object[2];
					obj[0] = rs.getObject(1);
					obj[1] = rs.getObject(2);
					addRecord(obj);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Object[] getFacthourAncCost(TaskVO vo, GridTreeVO queryGridTreeVO) {
		StringBuffer getAllNodes = new StringBuffer(
				"SELECT  DECODE(SUM(VO.FACTHOUR), NULL ,GET_HOUR(:ID),SUM(VO.FACTHOUR)) ,"
						+ " DECODE(SUM(VO.SUBJOIN_COST), NULL ,GET_COST(:ID),SUM(VO.SUBJOIN_COST))  FROM ALL_TASK_VIEW VO WHERE VO.parent_task =  :ID  ");

		Session session = this.getSession();
		Query query = session.createSQLQuery(getAllNodes.toString());
		query.setParameter("ID", vo.getTaskId());
		List ans = query.list();
		if (ans == null || ans.size() < 1) {
			return new Object[] { 0, 0 };
		} else {
			return (Object[]) ans.get(0);
		}
	}

	/**
	 * 调用存储过程得到总成本和耗时.
	 * 
	 * @param taskId
	 * @return
	 */
	public String[] getCostAndHour(String taskId) {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String[] ans = new String[2];
		try {
			conn = DBPoolManager.getInstance().getDBConn();
			conn.setAutoCommit(false);
			cstmt = conn.prepareCall("{call count_cost_and_hours(?,?,?)}");
			cstmt.setString(1, taskId);
			cstmt.registerOutParameter(2, java.sql.Types.DOUBLE);
			cstmt.registerOutParameter(3, java.sql.Types.DOUBLE);
			cstmt.executeUpdate();
			conn.commit();
			ans[0] = "" + cstmt.getDouble(2);
			ans[1] = "" + cstmt.getDouble(3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
					cstmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			DBPoolManager.getInstance().freeDBConnection(conn);
		}
		return ans;
	}

	public List getPeopleByProject(String code) {
		DbTool db = new DbTool();
		List arg = new ArrayList();
		arg.add(code);
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT U.USERID, U.USERNAME       ");
		buf.append("  FROM USERS U                    ");
		buf.append(" WHERE EXISTS (SELECT 1           ");
		buf.append("          FROM PROJECT_STAFFS T   ");
		buf.append("         WHERE T.USERID = U.USERID");
		buf.append("           AND T.PROJ_ID = ?)     ");
		List result = null;
		try {
			result = db.queryList(buf.toString(), arg, new DataHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					Object[] obj = new Object[2];
					obj[0] = rs.getObject(1);
					obj[1] = rs.getObject(2);
					addRecord(obj);
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List getProjectByDepart(String code) {
		DbTool db = new DbTool();
		List arg = new ArrayList();
		arg.add(code);
		List result = null;
		try {
			if(!"-1".equals(code)){
			result = db
					.queryList(
							"SELECT DISTINCT T.PROJ_ID,T.PROJ_NAME FROM PROJECT T WHERE T.DEPART_ID = ?",
							arg, new DataHandler() {
								@Override
								public void processRow(ResultSet rs)
										throws SQLException {
									Object[] obj = new Object[2];
									obj[0] = rs.getObject(1);
									obj[1] = rs.getObject(2);
									addRecord(obj);
								}
							});
			}else{
				result = db
				.queryList(
						"SELECT DISTINCT T.PROJ_ID,T.PROJ_NAME FROM PROJECT T ",  new DataHandler() {
							@Override
							public void processRow(ResultSet rs)
									throws SQLException {
								Object[] obj = new Object[2];
								obj[0] = rs.getObject(1);
								obj[1] = rs.getObject(2);
								addRecord(obj);
							}
						});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List getUserByDepart(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public String userValidate(String logingId, String pass) {
		DbTool db = new DbTool();
		List arg = new ArrayList();
		arg.add(logingId);
		arg.add(pass);
		StringBuffer buf = new StringBuffer();
		buf.append("select sysUser.id from Sys_User   sysUser where sysUser.User_Name = ? ");
		buf.append("and sysUser.password = ? and (sysUser.status='ACTIVE' or sysUser.status='TEST')");
		String result = null;
		try {
			result = db.queryForString(buf.toString(), arg );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}

	public SysUser getUser(String userId) {
		DbTool db = new DbTool();
		List arg = new ArrayList();
		arg.add(userId);
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT * FROM SYS_USER  WHERE ID = ?  "); 
		final SysUser user = new SysUser();
		try {
			 db.queryList(buf.toString(), arg, new DataHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					user.setFirstName(rs.getString("FIRST_NAME"));
					user.setUserName(rs.getString("USER_NAME"));
					user.setLastName(rs.getString("last_name"));
					user.setPhone(rs.getString("phone"));
					user.setPostcode(rs.getString("post_code"));
					user.setEmail(rs.getString("email"));
					user.setTitle(rs.getString("title"));
					user.setUserType(rs.getString("user_type"));
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
