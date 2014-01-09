package money.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于用户菜单权限信息的业务操作实现类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class UserMenuRightManagerImpl extends AbstractBusinessObjectManager
		implements UserMenuRightManager {

	private UserMenuRightDao usermenurightdao = null;

	/**
	 * 构造函数.
	 */
	public UserMenuRightManagerImpl(UserMenuRightDao usermenurightdao) {
		this.usermenurightdao = usermenurightdao;
	}

	/**
	 * 查询总数.
	 * 
	 * @param criterias
	 *            查询条件
	 * @return
	 */
	public Integer searchUserMenuRightNum(
			Map<UserMenuRightSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.usermenurightdao.countByQuery(hql,
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
	public Collection<UserMenuRight> searchUserMenuRight(
			Map<UserMenuRightSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<UserMenuRight> eaList = new ArrayList<UserMenuRight>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<UserMenuRightVO> voList = this.usermenurightdao.findByQuery(
				hql, (Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (UserMenuRightVO po : voList) {
			eaList.add(new UserMenuRightImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<UserMenuRightSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct usermenuright) "
						: "select distinct usermenuright ").append(
				"from UserMenuRightVO as usermenuright ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<UserMenuRightSearchFields, Object> entry : criterias
					.entrySet()) {
				UserMenuRightSearchFields fd = entry.getKey();
				switch (fd) {
				case MENUID:
					sb.append(count == 0 ? " where" : " and").append(
							"  usermenuright.menuId = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case USERID:
					sb.append(count == 0 ? " where" : " and").append(
							"  usermenuright.userId = ? ");
					argList.add(entry.getValue());
					count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		UserMenuRightOrderByFields orderBy = UserMenuRightOrderByFields.MENUID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = UserMenuRightOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case MENUID:
			sb.append(" order by usermenuright.menuId");
			break;
		case USERID:
			sb.append(" order by usermenuright.userId");
			break;
		default:
			break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createUserMenuRight(UserMenuRight usermenuright)
			throws ValidateFieldsException {
		UserMenuRightImpl usermenurightImpl = (UserMenuRightImpl) usermenuright;
		this.usermenurightdao.insert(usermenurightImpl.getUserMenuRightVO());
	}

	public void addUserMenuRights(String ids,String user) {
		String[] idArr = ids.split(",");
		int userId = Integer.parseInt(user);
		this.usermenurightdao.deleteAllByUserId(userId);
		for (String s : idArr) {
			int menuId = Integer.parseInt(s);
			UserMenuRightVO right = new UserMenuRightVO(menuId,userId); 
			this.usermenurightdao.insert(right);
		}
	}

	public void updateUserMenuRight(UserMenuRight usermenuright)
			throws ValidateFieldsException {
		UserMenuRightImpl usermenurightImpl = (UserMenuRightImpl) usermenuright;

		UserMenuRightVO po = usermenurightImpl.getUserMenuRightVO();
		this.usermenurightdao.update(po);
	}

	public Set<Integer> getMenuIdsByUserId(int id) {
		Set<Integer> eaList = new HashSet<Integer>();

		Collection<UserMenuRightVO> voList = this.usermenurightdao
				.findRecordByUserId(id);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (UserMenuRightVO po : voList) {
			eaList.add(po.getMenuId());
		}

		return eaList;
	}

}
