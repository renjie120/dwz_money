
package money.menu;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于菜单信息表的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface MenuManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<Menu> searchMenu(Map<MenuSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchMenuNum(Map<MenuSearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param menu
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createMenu(Menu menu) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param menu
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateMenu(Menu menu) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param menuId
	 */
	@Transactional
	public void removeMenus(String menuId);

	/**
	 * 根据主键取值.
	 * @param menuId
	 * @return
	 */
	public Menu getMenu(int menuId);
}
