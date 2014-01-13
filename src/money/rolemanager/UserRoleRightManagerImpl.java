
package money.rolemanager;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于用户角色权限的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserRoleRightManagerImpl extends AbstractBusinessObjectManager implements
		UserRoleRightManager {

	private UserRoleRightDao userrolerightdao = null;

	/**
	 * 构造函数.
	 */
	public UserRoleRightManagerImpl(UserRoleRightDao userrolerightdao) {
		this.userrolerightdao = userrolerightdao;
	} 
 
	public void createUserRoleRight(UserRoleRight userroleright) throws ValidateFieldsException {
		UserRoleRightImpl userrolerightImpl = (UserRoleRightImpl) userroleright;
		this.userrolerightdao.insert(userrolerightImpl.getUserRoleRightVO());
	}

	public void removeUserRoleRights(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			UserRoleRightVO vo = this.userrolerightdao.findByPrimaryKey(Integer.parseInt(s));
			this.userrolerightdao.delete(vo);
		}
	} 
}
