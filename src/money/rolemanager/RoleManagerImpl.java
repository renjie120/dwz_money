package money.rolemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.util.CommonUtil;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于角色信息的业务操作实现类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class RoleManagerImpl extends AbstractBusinessObjectManager implements
		RoleManager {

	private RoleDao roledao = null;
	private RoleWithMenuDao roleMenuDao = null;
	private UserRoleRightDao userRoleDao = null;

	/**
	 * 构造函数.
	 */
	public RoleManagerImpl(RoleDao roledao, RoleWithMenuDao roleMenuDao,
			UserRoleRightDao userRoleDao) {
		this.roledao = roledao;
		this.roleMenuDao = roleMenuDao;
		this.userRoleDao = userRoleDao;
	}

	/**
	 * 查询总数.
	 * 
	 * @param criterias
	 *            查询条件
	 * @return
	 */
	public Integer searchRoleNum(Map<RoleSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.roledao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * 
	 * @param criterias
	 *            条件
	 * @param orderField
	 *            排序列
	 * @param startIndex
	 *            开始索引
	 * @param count
	 *            总数
	 * @return
	 */
	public Collection<Role> searchRole(Map<RoleSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Role> eaList = new ArrayList<Role>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<RoleVO> voList = this.roledao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (RoleVO po : voList) {
			eaList.add(new RoleImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<RoleSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(useCount ? "select count( role) " : "select  role ").append(
				"from RoleVO as role ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<RoleSearchFields, Object> entry : criterias
					.entrySet()) {
				RoleSearchFields fd = entry.getKey();
				switch (fd) {
				case ROLEID:
					sb.append(count == 0 ? " where" : " and").append(
							"  role.roleId = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ROLEDESC:
					sb.append(count == 0 ? " where" : " and").append(
							"  role.roleDesc = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ROLENAME:
					sb.append(count == 0 ? " where" : " and").append(
							"  role.roleName like ? ");
					argList.add("%" + entry.getValue() + "%");
					count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		RoleOrderByFields orderBy = RoleOrderByFields.ROLEID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = RoleOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case ROLEID:
			sb.append(" order by role.roleId");
			break;
		case ROLEDESC:
			sb.append(" order by role.roleDesc");
			break;
		case ROLENAME:
			sb.append(" order by role.roleName");
			break;
		default:
			break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createRole(Role role) throws ValidateFieldsException {
		RoleImpl roleImpl = (RoleImpl) role;
		this.roledao.insert(roleImpl.getRoleVO());
	}

	public void removeRoles(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			RoleVO vo = this.roledao.findByPrimaryKey(Integer.parseInt(s));
			this.roledao.delete(vo);
		}
	}

	public void updateRole(Role role) throws ValidateFieldsException {
		RoleImpl roleImpl = (RoleImpl) role;

		RoleVO po = roleImpl.getRoleVO();
		this.roledao.update(po);
	}

	public Role getRole(int id) {
		Collection<RoleVO> roles = this.roledao.findRecordById(id);

		if (roles == null || roles.size() < 1)
			return null;

		RoleVO role = roles.toArray(new RoleVO[roles.size()])[0];

		return new RoleImpl(role);
	}

	@Override
	public void createRoleWithMenu(int roleId, String menuIds) {
		roleMenuDao.deleteAllByRoleId(roleId);
		if (!CommonUtil.isEmpty(menuIds)) {
			String[] menuidArr = menuIds.split(",");
			for (String m : menuidArr) {
				RoleWithMenuVO vo = new RoleWithMenuVO();
				vo.setRoleId(roleId);
				vo.setMenuId(Integer.parseInt(m));
				this.roleMenuDao.insert(vo);
			}
		}
	}

	/**
	 * 查询用于拥有的角色总数.
	 * 
	 * @param criterias
	 *            查询条件
	 * @return
	 */
	public Integer searchRoleByUser(int userId) {
		Integer[] param = new Integer[] { userId };
		String hql = "select count(vo) from UserRoleRightVO as vo where userId=?";
		Number totalCount = this.roleMenuDao
				.countByQuery(hql, (Object[]) param);

		return totalCount.intValue();
	}

	@Override
	public Collection<Role> searchRoleByUserId(int userId, int startIndex,
			int count) {
		ArrayList<Role> eaList = new ArrayList<Role>();

		String hql = "select new RoleVO(userVo.id,ov.roleDesc,ov.roleDesc) from RoleVO as ov,UserRoleRightVO as userVo where ov.roleId=userVo.roleId and userVo.userId=?";
		Collection<RoleVO> voList = this.roledao.findByQuery(hql,
				(Object[]) new Integer[] { userId }, startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (RoleVO po : voList) {
			eaList.add(new RoleImpl(po));
		}

		return eaList;
	}

	@Override
	public void createUserWithRole(int userId, String menuIds) {
		String[] menuidArr = menuIds.split(",");
		userRoleDao.deleteAllByUserId(userId);
		if (CommonUtil.isNotEmpty(menuIds))
			for (String m : menuidArr) {
				UserRoleRightVO vo = new UserRoleRightVO();
				vo.setRoleId(Integer.parseInt(m));
				vo.setUserId(userId);
				this.userRoleDao.insert(vo);
			}
	}
}
