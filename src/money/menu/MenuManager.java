package money.menu;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface MenuManager extends BusinessObjectManager {

	public Collection<Menu> searchMenu(Map<MenuSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	public Integer searchMenuNum(Map<MenuSearchFields, Object> criterias);

	@Transactional
	public void createMenu(Menu menu) throws ValidateFieldsException;

	@Transactional
	public void updateMenu(Menu menu) throws ValidateFieldsException;

	@Transactional
	public void removeMenu(String menuId);

	public Menu getMenu(Integer id);
}
