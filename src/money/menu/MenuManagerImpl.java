
package money.menu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import money.rolemanager.RoleWithMenuVO;

import common.base.AllSelect;
import common.base.AllSelectContants;
import common.base.ParamSelect;
import common.base.SpringContextUtil;

import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于菜单信息表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class MenuManagerImpl extends AbstractBusinessObjectManager implements
		MenuManager {

	private MenuDao menudao = null;

	/**
	 * 构造函数.
	 */
	public MenuManagerImpl(MenuDao menudao) {
		this.menudao = menudao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchMenuNum(Map<MenuSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.menudao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<Menu> searchMenu(Map<MenuSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Menu> eaList = new ArrayList<Menu>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<MenuVO> voList = this.menudao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
		
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select1 = allSelect
				.getParamsByType(AllSelectContants.MENULEVEL.getName());
		for (MenuVO po : voList) {
			po.setLevel(select1.getName("" + po.getLevel()));
			eaList.add(new  MenuImpl(po));
		}

		return eaList;
	} 
	
	private Object[] createQuery(boolean useCount,
			Map<MenuSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( menu.menuId) "
						: "select  new MenuVO(menu.menuId,menu.target,menu.menuName,menu2.menuName,menu.orderId,menu.url,menu.level,menu.relId) ").append("from MenuVO as menu ,MenuVO as menu2 ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<MenuSearchFields, Object> entry : criterias
					.entrySet()) {
				MenuSearchFields fd = entry.getKey();
				switch (fd) {
					case MENUID:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.menuId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case TARGET:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.target = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case MENUNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.menuName like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case PARENTID:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.parentId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ORDERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.orderId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case URL:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.url = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case LEVEL:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.level = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case RELID:
						sb.append(count == 0 ? " where" : " and").append(
								"  menu.relId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			sb.append(count == 0 ? " where " : " and ").append(
					"  menu.parentId=menu2.menuId ");
			return new Object[] { sb.toString(), argList.toArray() };
		}else{
			sb.append(count == 0 ? " where " : " and").append(
					"  menu.parentId=menu2.menuId ");
		}

		MenuOrderByFields orderBy = MenuOrderByFields.MENUID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = MenuOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case MENUID:
				 sb.append(" order by menu.menuId");
			break;
			case TARGET:
				 sb.append(" order by menu.target");
			break;
			case MENUNAME:
				 sb.append(" order by menu.menuName");
			break;
			case PARENTID:
				 sb.append(" order by menu.parentId");
			break;
			case ORDERID:
				 sb.append(" order by menu.orderId");
			break;
			case URL:
				 sb.append(" order by menu.url");
			break;
			case LEVEL:
				 sb.append(" order by menu.level");
			break;
			case RELID:
				 sb.append(" order by menu.relId");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createMenu(Menu menu) throws ValidateFieldsException {
		MenuImpl menuImpl = (MenuImpl) menu;
		this.menudao.insert(menuImpl.getMenuVO()); 
	}

	public void removeMenus(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			MenuVO vo = this.menudao.findByPrimaryKey(Integer.parseInt(s));
			this.menudao.delete(vo);
		}
	}

	public void updateMenu(Menu menu) throws ValidateFieldsException {
		MenuImpl menuImpl = (MenuImpl) menu;

		MenuVO po = menuImpl.getMenuVO();
		this.menudao.update(po);
	}

	public Menu getMenu(int id) {
		Collection<MenuVO> menus = this.menudao.findRecordById(id);

		if (menus == null || menus.size() < 1)
			return null;

		MenuVO menu = menus.toArray(new MenuVO[menus.size()])[0];

		return new MenuImpl(menu);
	}

}
