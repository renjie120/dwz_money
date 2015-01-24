
package ido.BusinessShop;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于商铺的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BusinessShopDao extends BaseDao<BusinessShopVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<BusinessShopVO> findRecordById(int sno); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<BusinessShopVO> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<BusinessShopVO> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int sno);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessBusinessShopVO(BusinessShopVO vo, int sno);
}
