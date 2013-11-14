package money.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public class MenuManagerImpl extends AbstractBusinessObjectManager implements
		MenuManager {

	private MenuDao menuDao = null;

	public MenuManagerImpl(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public Integer searchMenuNum(Map<MenuSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.menuDao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	public Collection<Menu> searchMenu(Map<MenuSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Menu> eaList = new ArrayList<Menu>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<MenuVO> voList = this.menuDao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		AllSelect allSelect = (AllSelect)SpringContextUtil.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select1 = allSelect.getParamsByType(AllSelectContants.MENU_LEVEL.getName());
		ParamSelect select2 = allSelect.getParamsByType(AllSelectContants.MENU_TARGET.getName());
		for (MenuVO po : voList) {
			po.setLevel(select1.getName(""+po.getLevel()));
			po.setTarget(select2.getName(""+po.getTarget()));
			eaList.add(new MenuImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<MenuSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct menu) "
						: "select distinct menu ").append(
				"from MenuVO as menu ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<MenuSearchFields, Object> entry : criterias
					.entrySet()) {
				MenuSearchFields fd = entry.getKey();
				switch (fd) {
				case MENUID:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.menuId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case MENUNAME:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.menuName like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case URL:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.url like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case TARGET:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.target like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case PARENTID:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.parentId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case LEVEL:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.level like ? ");
					argList.add(entry.getValue());
					count++;
					break;
				case ORDERID:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.orderId =? ");
					argList.add(entry.getValue());
					count++;
					break;
				case RELID:
					sb.append(count == 0 ? " where" : " and").append(
							" menu.relId like ? ");
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

		MenuOrderByFields orderBy = MenuOrderByFields.MENUID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = MenuOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
		case MENUID:
			sb.append(" order by menu.menuId");
			break;
		case MENUID_DESC:
			sb.append(" order by menu.menuId desc");
			break;
		case MENUNAME:
			sb.append(" order by menu.menuName");
			break;
		case MENUNAME_DESC:
			sb.append(" order by menu.menuName desc");
			break;
		case URL:
			sb.append(" order by menu.url");
			break;
		case URL_DESC:
			sb.append(" order by menu.url desc");
			break;
		case TARGET:
			sb.append(" order by menu.target");
			break;
		case TARGET_DESC:
			sb.append(" order by menu.target desc");
			break;
		case PARENTID:
			sb.append(" order by menu.parentId");
			break;
		case PARENTID_DESC:
			sb.append(" order by menu.parentId desc");
			break;
		case LEVEL:
			sb.append(" order by menu.level");
			break;
		case LEVEL_DESC:
			sb.append(" order by menu.level desc");
			break;
		case ORDERID:
			sb.append(" order by menu.orderId");
			break;
		case ORDERID_DESC:
			sb.append(" order by menu.orderId desc");
			break;
		case RELID:
			sb.append(" order by menu.relId");
			break;
		case RELID_DESC:
			sb.append(" order by menu.relId desc");
			break;

		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createMenu(Menu menu) throws ValidateFieldsException {
		MenuImpl menuImpl = (MenuImpl) menu;
		this.menuDao.insert(menuImpl.getMenuVO());
	}

	public void removeMenu(String menuIds) {
		String[] idArr = menuIds.split(",");
		for (String s : idArr) {
			MenuVO vo = this.menuDao.findByPrimaryKey(Integer.parseInt(s));
			this.menuDao.delete(vo);
		}
	}

	public void updateMenu(Menu menu) throws ValidateFieldsException {
		MenuImpl menuImpl = (MenuImpl) menu;

		MenuVO po = menuImpl.getMenuVO();
		this.menuDao.update(po);
	}

	public Menu getMenu(Integer id) {
		Collection<MenuVO> menus = this.menuDao.findRecordById(id);

		if (menus == null || menus.size() < 1)
			return null;

		MenuVO menu = menus.toArray(new MenuVO[menus.size()])[0];

		return new MenuImpl(menu);
	}

}
