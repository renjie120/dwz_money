
package ido.BusinessMan;

import java.util.Collection;

import dwz.dal.BaseDao; 

/**
 * 关于商家的数据库操作接口
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BusinessManDao extends BaseDao<BusinessManVO, Integer> {
	/**
	 * 根据主键查询全部集合.
	 */
	public Collection<BusinessManVO> findRecordById(int sno); 
	
	/**
	 * 查询全部集合.
	 */
	public Collection<BusinessManVO> findAll(); 
	
	/**
	 * 根据条件查询全部集合.
	 */
	public Collection<BusinessManVO> findParmByType(int paramType);  

	/**
	 * 根据主键删除数据.
	 */
	public void deleteAllById(int sno);

	/**
	 * 根据主键更新数据.
	 */
	public void updateAllaccessBusinessManVO(BusinessManVO vo, int sno);
}
