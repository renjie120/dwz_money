package money.role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import dwz.framework.core.business.AbstractBusinessObjectManager;

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
  
	public void addUserMenuRights(String ids,String user) {
		int userId = Integer.parseInt(user);
		this.usermenurightdao.deleteAllByUserId(userId);
		if(!"".equals(ids)){
			String[] idArr = ids.split(",");
			for (String s : idArr) {
				int menuId = Integer.parseInt(s);
				UserMenuRightVO right = new UserMenuRightVO(menuId,userId); 
				this.usermenurightdao.insert(right);
			}
		}
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
