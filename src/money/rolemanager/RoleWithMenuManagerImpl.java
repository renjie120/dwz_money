
package money.rolemanager;
import java.util.Collection;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于角色拥有的菜单权限的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class RoleWithMenuManagerImpl extends AbstractBusinessObjectManager implements
		RoleWithMenuManager {

	private RoleWithMenuDao rolewithmenudao = null;

	/**
	 * 构造函数.
	 */
	public RoleWithMenuManagerImpl(RoleWithMenuDao rolewithmenudao) {
		this.rolewithmenudao = rolewithmenudao;
	}
 

	public void createRoleWithMenu(RoleWithMenu rolewithmenu) throws ValidateFieldsException {
		RoleWithMenuImpl rolewithmenuImpl = (RoleWithMenuImpl) rolewithmenu;
		this.rolewithmenudao.insert(rolewithmenuImpl.getRoleWithMenuVO());
	}

	public void removeRoleWithMenus(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			RoleWithMenuVO vo = this.rolewithmenudao.findByPrimaryKey(Integer.parseInt(s));
			this.rolewithmenudao.delete(vo);
		}
	}


	@Override
	public Collection<Role> searchRolesByUserId(int userId, int startIndex,
			int count) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer searchRoleNumByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}   
}
