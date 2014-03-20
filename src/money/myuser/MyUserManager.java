
package money.myuser;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于用户信息表的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface MyUserManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<MyUser> searchMyUser(Map<MyUserSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchMyUserNum(Map<MyUserSearchFields, Object> criterias);

	public MyUser getSimpleMyUser(int id) ;
	/**
	 * 保存实体到数据库.
	 * @param myuser
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createMyUser(MyUser myuser) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param myuser
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateMyUser(MyUser myuser) throws ValidateFieldsException;

	/**
	 * 更新密码.
	 * @param userId
	 * @param pass
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updatePassword(String pass, String userId) ;

	@Transactional
	public void initPassword(String useId) ;

	/**
	 * 删除操作
	 * @param useId
	 */
	@Transactional
	public void removeMyUsers(String useId);

	/**
	 * 根据主键取值.
	 * @param useId
	 * @return
	 */
	public MyUser getMyUser(int useId);
}
