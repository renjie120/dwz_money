
package money.menu;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface MenuDao extends BaseDao<MenuVO, Integer> {

	public Collection<MenuVO> findRecordById(int menuId); 
	
	public Collection<MenuVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessMenuVO(MenuVO vo, String id);
}

